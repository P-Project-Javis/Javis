/*
메인 액티비티 클래스

앱 시작화면 액티비티. 다른 액티비티로 이동하는 버튼과

1. 메뉴 버튼 클릭시 메뉴 아이템 버튼들이 아래로 나옴.(이동 및 회전 애니메이션 적용 예정)
2. 열린 메뉴버튼을 다시 클릭시 메뉴 아이템들이 들어감.
3 .마이크 이미지 클릭시 녹음 활성화.
4. 메뉴의 각 아이템 클릭시 각 액티비티로 이동.
 */
package pproject.teamjavis.javis.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main_close.*
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.util.VoiceRecorder

class MainActivity: BaseActivity() {
    companion object {
        val PERMISSION_REQUEST_RECORD_AUDIO = 1
    }

    private var isMenuOpen = false
    private var isRecording = false
    private var recorder: VoiceRecorder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_close)

        recorder = VoiceRecorder(applicationContext, "order")

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
            stopRecording()
            val intent = Intent(applicationContext, SettingActivity::class.java)
            startActivity(intent)
        }

        main_adduserButton.setOnClickListener {
            stopRecording()
            val intent = Intent(applicationContext, AdduserActivity::class.java)
            startActivity(intent)
        }

        main_lockButton.setOnClickListener {
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

        if(checkPermission() == PackageManager.PERMISSION_DENIED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), PERMISSION_REQUEST_RECORD_AUDIO)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_RECORD_AUDIO -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    makeToast("승인했습니다.")
                } else {
                    makeToast("거부하셨습니다. 앱을 정상적으로 이용할 수 없습니다.")
                    finish()
                }
                return
            }
            else -> {
                makeToast("거부하셨습니다. 앱을 정상적으로 이용할 수 없습니다.")
                finish()
            }
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
        recorder!!.startRecord()
        main_mic.setImageDrawable(resources.getDrawable(R.drawable.ic_mic_black_48dp))
        main_message.text = resources.getText(R.string.message_recording)
        isRecording = true
    }

    private fun stopRecording() {
        recorder!!.stopRecord()
        main_mic.setImageDrawable(resources.getDrawable(R.drawable.ic_mic_none_black_48dp))
        main_message.text = resources.getText(R.string.message_notrecording)
        isRecording = false
    }
}