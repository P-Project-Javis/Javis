/*
새 사용자 등록 액티비티.

해당 앱을 사용한 새 사용자를 등록할 수 있는 액티비티. 새 사용자 등록 시 권한 설정도 가능.
 */
package pproject.teamjavis.javis.ui.activity

import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_adduser.*
import kotlinx.android.synthetic.main.layout_topbar.*
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.util.DatabaseHelper
import pproject.teamjavis.javis.util.VoiceRecorder


class AdduserActivity: BaseActivity() {
    private var isNameChecked = false
    private var isVoiceChecked = false
    private var isRecording = false

    private var name: String = "이름미지정"
    private var recorder = VoiceRecorder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adduser)
        topbar_activityTitle.text = resources.getString(R.string.title_new)

        topbar_backButton.setOnClickListener { finish() }

        adduser_voiceWrap.visibility = View.GONE

        adduser_nextButton.setOnClickListener {
            name = adduser_inputName.text.toString()
            //이름 공백 여부 확인
            if(name != "") {
                val db = DatabaseHelper(applicationContext)
                db.openReadable()
                //이름 중복 여부 확인
                if(db.checkName(name)) {
                    adduser_nameWrap.visibility = View.GONE
                    adduser_voiceWrap.visibility = View.VISIBLE
                    isNameChecked = true
                    adduser_nameChecker.setImageResource(R.drawable.ic_check_box_black_24dp)
                }
                else
                    makeToast("이미 존재하는 사용자 이름입니다")
                db.close()
            }
            else
                makeToast("한 글자 이상 입력하세요")
        }

        adduser_recordButton.setOnClickListener {
            if(!isRecording) {
                startRecording(name)
                autoStopRecording()
            }
            else
                stopRecording()
        }

        adduser_confirmButton.visibility = View.GONE
        adduser_confirmButton.setOnClickListener {
            if(isNameChecked && isVoiceChecked) {
                addUser()
                finish()
            }
        }
    }

    private fun startRecording(name: String) {
        recorder!!.setupRecorder(name)
        recorder!!.startRecorder()
        adduser_recordButton.setImageResource(R.drawable.ic_mic_black_48dp)
        isRecording = true
    }

    private fun stopRecording() {
        recorder!!.stopRecorder()
        adduser_recordButton.setImageResource(R.drawable.ic_mic_none_black_48dp)
        isVoiceChecked = true
        adduser_voiceChecker.setImageResource(R.drawable.ic_check_box_black_24dp)
        isRecording = false
        adduser_confirmButton.visibility = View.VISIBLE
    }

    private fun autoStopRecording() {
        Handler().postDelayed(Runnable {
            stopRecording()
        }, 19000)
    }

    private fun addUser() {
        val db = DatabaseHelper(applicationContext)
        val fileName = recorder!!.fileName

        val api = CallApi()
        api.callSetVoiceApi(name)

        db.openWritable()
        db.insert(name, fileName, 1, 1, 0, 0)
        db.close()
    }
}
}