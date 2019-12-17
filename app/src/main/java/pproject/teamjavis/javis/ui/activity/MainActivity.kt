/*
메인 액티비티 클래스

앱 시작화면 액티비티. 다른 액티비티로 이동하는 버튼과

1. 메뉴 버튼 클릭시 메뉴 아이템 버튼들이 아래로 나옴.(이동 및 회전 애니메이션 적용 예정)
2. 열린 메뉴버튼을 다시 클릭시 메뉴 아이템들이 들어감.
3 .마이크 이미지 클릭시 녹음 활성화.
4. 메뉴의 각 아이템 클릭시 각 액티비티로 이동.
 */
package pproject.teamjavis.javis.ui.activity

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main_close.*
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.util.api.RecogVoiceApi
import pproject.teamjavis.javis.util.manager.OrderManager
import pproject.teamjavis.javis.util.manager.PlayManager
import pproject.teamjavis.javis.util.manager.RecordManager
import pproject.teamjavis.javis.util.api.STTApi
import pproject.teamjavis.javis.util.api.TTSApi
import pproject.teamjavis.javis.util.manager.DatabaseManager

class MainActivity: BaseActivity() {
    private var isMenuOpen = false
    private var isRecording = false
    private var recorder = RecordManager()

    private val permissions = ArrayList<String>()
    private val permissionParam = 1
    private val bluetoothParam = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_close)

        recorder = RecordManager()

        main_menuButton.setOnClickListener {
            if(!isMenuOpen) {
                updateView(R.layout.activity_main_open)
            }
            else {
                updateView(R.layout.activity_main_close)
            }
            isMenuOpen = !isMenuOpen
        }

        main_settingButton.setOnClickListener {
            if(isRecording)
                stopRecording()
            val intent = Intent(applicationContext, SettingActivity::class.java)
            startActivity(intent)
        }

        main_adduserButton.setOnClickListener {
            if(isRecording)
                stopRecording()
            val intent = Intent(applicationContext, AdduserActivity::class.java)
            startActivity(intent)
        }

        main_lockButton.setOnClickListener {
            if(isRecording)
                stopRecording()
            val intent = Intent(applicationContext, AuthorityActivity::class.java)
            startActivity(intent)
        }

        main_mic.setOnClickListener {
            if(!isRecording)
                startRecording()
            else
                stopRecording()
        }

        val permissionList = listOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if(Build.VERSION.SDK_INT >= 23) {
            checkPermission(permissionList)
        }

        checkBluetooth()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            bluetoothParam -> {
                if(requestCode == Activity.RESULT_OK) {

                }
                else if(requestCode == Activity.RESULT_CANCELED)
                    finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            permissionParam -> {
                if(grantResults.isNotEmpty()) {
                    for (i in 0 until permissions.size) {
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            makeToast("권한이 없으면 앱을 정상적으로 이용할 수 없습니다.")
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun checkPermission(permissionList: List<String>): Boolean {
        for(permission in permissionList) {
            if(ContextCompat.checkSelfPermission(applicationContext, permission) == PackageManager.PERMISSION_DENIED)
                permissions.add(permission)
        }

        return if(permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissions.toArray(arrayOfNulls<String>(permissions.size)), permissionParam)
            true
        } else
            false
    }

    private fun checkBluetooth() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(bluetoothAdapter == null) {
            makeToast("블루투스가 지원되지 않는 장비에서는 사용할 수 없습니다")
            finish()
        }

        if(!bluetoothAdapter.isEnabled) {
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent, bluetoothParam)
        }

        val pairedDevices = bluetoothAdapter.bondedDevices
        if(pairedDevices.isEmpty()) {
            makeToast("블루투스 페어링 된 장치가 없습니다 먼저 페어링을 해 주세요")
            finish()
        }
    }

    private fun updateView(id: Int) {
        var targetConstSet = ConstraintSet()
        targetConstSet.clone(this, id)
        targetConstSet.applyTo(root)

        var trans = ChangeBounds()
        trans.interpolator = AccelerateInterpolator()
        TransitionManager.beginDelayedTransition(root, trans)
    }

    private fun startRecording() {
        recorder!!.setupRecorder("order")
        recorder!!.startRecorder()
        main_mic.setImageResource(R.drawable.ic_mic_black_48dp)
        main_message.text = resources.getText(R.string.message_recording)
        isRecording = true
    }

    private fun stopRecording() {
        recorder!!.stopRecorder()
        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
        main_message.text = resources.getText(R.string.message_notrecording)
        isRecording = false
        getResponse()
    }

    private fun getResponse() {
        //stt와 화자인증 api 실행
        val stt = STTApi(applicationContext, "order")
        stt.connect()
        main_mic.setImageResource(R.drawable.ic_refresh_black_24dp)
        main_message.text = "결과 받아오는 중..."
        val recog = RecogVoiceApi(applicationContext, "order")
        recog.connect()

        Handler().postDelayed( {
            //1.5초 후 stt 결과를 가져온 후 성공시 다음으로 진행, 아니면 실패 메세지
            val voiceId = recog.voiceId
            if(recog.isSuccess) {
                if(voiceId != "__no__match__") {
                    //voiceId가 no match가 아니면
                    makeToast(voiceId)
                    log("보이스 ID:$voiceId")
                    val orderManager = OrderManager()
                    val order = orderManager.understandOrder(stt.result)
                    val db = DatabaseManager(applicationContext)
                    db.openReadable()
                    //db에서 voiceId와 일치하는 이름이 있는지 확인
                    if(!db.checkName(voiceId)) {
                        //db에 이름이 있는 경우
                        val kind = orderManager.kind
                        when(kind) {
                            //db에 해당 사용자의 권한이 있는지 체크
                            "티비" -> {
                                if(db.checkTv(voiceId)) {
                                    //권한이 있는 경우 명령 실행
                                    val tts = TTSApi(applicationContext, order)
                                    tts.connect()
                                    Handler().postDelayed( {
                                        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                                        main_message.text = "$order\n\n계속하려면 이미지를 누른 후 말해주세요"
                                        val player = PlayManager(applicationContext, "response")
                                        player.play()

                                        controlTv(orderManager.control)
                                    }, 1500)
                                }
                                else {
                                    //권한이 없는 경우 메세지 출력
                                    val tts = TTSApi(applicationContext, "권한이 없습니다")
                                    tts.connect()
                                    Handler().postDelayed( {
                                        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                                        main_message.text = "권한이 없습니다\n\n계속하려면 이미지를 누른 후 말해주세요"
                                        val player = PlayManager(applicationContext, "response")
                                        player.play()
                                    }, 1500)
                                }
                            }
                            "조명" -> {
                                if(db.checkLight(voiceId)) {
                                    val tts = TTSApi(applicationContext, order)
                                    tts.connect()
                                    Handler().postDelayed( {
                                        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                                        main_message.text = "$order\n\n계속하려면 이미지를 누른 후 말해주세요"
                                        val player = PlayManager(applicationContext, "response")
                                        player.play()

                                        controlLight(orderManager.control)
                                    }, 1500)
                                }
                                else {
                                    val tts = TTSApi(applicationContext, "권한이 없습니다")
                                    tts.connect()
                                    Handler().postDelayed( {
                                        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                                        main_message.text = "권한이 없습니다\n\n계속하려면 이미지를 누른 후 말해주세요"
                                        val player = PlayManager(applicationContext, "response")
                                        player.play()
                                    }, 1500)
                                }
                            }
                            "가스레인지" -> {
                                if(db.checkGas(voiceId)) {
                                    val tts = TTSApi(applicationContext, order)
                                    tts.connect()
                                    Handler().postDelayed( {
                                        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                                        main_message.text = "$order\n\n계속하려면 이미지를 누른 후 말해주세요"
                                        val player = PlayManager(applicationContext, "response")
                                        player.play()

                                        controlGas(orderManager.control)
                                    }, 1500)
                                }
                                else {
                                    val tts = TTSApi(applicationContext, "권한이 없습니다")
                                    tts.connect()
                                    Handler().postDelayed( {
                                        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                                        main_message.text = "권한이 없습니다\n\n계속하려면 이미지를 누른 후 말해주세요"
                                        val player = PlayManager(applicationContext, "response")
                                        player.play()
                                    }, 1500)
                                }
                            }
                            "주문" -> {
                                if(db.checkBuy(voiceId)) {
                                    val tts = TTSApi(applicationContext, order)
                                    tts.connect()
                                    Handler().postDelayed( {
                                        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                                        main_message.text = "$order\n\n계속하려면 이미지를 누른 후 말해주세요"
                                        val player = PlayManager(applicationContext, "response")
                                        player.play()

                                        controlBuy()
                                    }, 1500)
                                }
                                else {
                                    val tts = TTSApi(applicationContext, "권한이 없습니다")
                                    tts.connect()
                                    Handler().postDelayed( {
                                        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                                        main_message.text = "권한이 없습니다\n\n계속하려면 이미지를 누른 후 말해주세요"
                                        val player = PlayManager(applicationContext, "response")
                                        player.play()
                                    }, 1500)
                                }
                            }
                        }
                    }
                    else {
                        //db에 이름이 없는 경우
                        val tts = TTSApi(applicationContext, "등록된 사용자가 아닙니다.")
                        tts.connect()
                        Handler().postDelayed( {
                            //등록된 사용자가 아니라는 메세지 출력
                            main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                            main_message.text = "등록된 사용자가 아닙니다\n\n계속하려면 이미지를 누른 후 말해주세요"
                            val player = PlayManager(applicationContext, "response")
                            player.play()
                        }, 1500)
                    }
                    db.close()
                }
                else {
                    //voiceId가 no match인 경우
                    val tts = TTSApi(applicationContext, "등록된 사용자가 아닙니다.")
                    tts.connect()
                    Handler().postDelayed( {
                        //등록된 사용자가 아니라는 메세지 출력
                        main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                        main_message.text = "등록된 사용자가 아닙니다\n\n계속하려면 이미지를 누른 후 말해주세요"
                        val player = PlayManager(applicationContext, "response")
                        player.play()
                    }, 1500)
                }
            }
            else {
                //화자인증 실패시 오류 메세지 출력
                val tts = TTSApi(applicationContext, recog.voiceId)
                tts.connect()
                Handler().postDelayed( {
                    main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                    main_message.text = "${recog.voiceId}\n\n계속하려면 이미지를 누른 후 말해주세요"
                    val player = PlayManager(applicationContext, "response")
                    player.play()
                }, 1500)

            }
        }, 1500)
    }

    private fun controlTv(value: Boolean) {
        if(value) {

        }
        else {

        }
    }

    private fun controlLight(value: Boolean) {
        if(value) {

        }
        else {

        }
    }

    private fun controlGas(value: Boolean) {
        if(value) {

        }
        else {

        }
    }

    private fun controlBuy() {
    }
}