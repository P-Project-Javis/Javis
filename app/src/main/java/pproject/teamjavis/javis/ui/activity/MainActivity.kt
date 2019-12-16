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
import android.animation.TimeInterpolator
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

class MainActivity: BaseActivity() {
    private var isMenuOpen = false
    private var isRecording = false
    private var recorder = RecordManager()

    private val permissions = ArrayList<String>()
    private val permissionParam = 1

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
        val stt = STTApi(applicationContext, "order")
        stt.connect()
        main_mic.setImageResource(R.drawable.ic_refresh_black_24dp)
        main_message.text = "결과 받아오는 중..."
        val recog = RecogVoiceApi(applicationContext, "order")
        recog.connect()
        Handler().postDelayed( {
            if(recog.isSuccess) {
                makeToast("성공")
                val orderManager = OrderManager()
                val order = orderManager.understandOrder(stt.result)
                val tts =  TTSApi(applicationContext, order)
                tts.connect()
                Handler().postDelayed( {
                    makeToast(recog.voiceId)
                    main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                    main_message.text = "${stt.result}\n$order\n계속하려면 이미지를 누른 후 말해주세요"
                    val player = PlayManager(
                        applicationContext,
                        "response"
                    )
                    player.play()
                }, 1500)
            }
            else {
                main_mic.setImageResource(R.drawable.ic_mic_none_black_48dp)
                main_message.text = "결과를 불러오는 데 실패했습니다\n\n계속하려면 이미지를 누른 후 말해주세요"
                makeToast("실패")
            }
        }, 1500)

    }
}