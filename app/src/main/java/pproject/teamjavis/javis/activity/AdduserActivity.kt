/*
새 사용자 등록 액티비티.

해당 앱을 사용한 새 사용자를 등록할 수 있는 액티비티. 새 사용자 등록 시 권한 설정도 가능.
 */
package pproject.teamjavis.javis.activity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_adduser.*
import kotlinx.android.synthetic.main.layout_topbar.*
import pproject.teamjavis.javis.R

class AdduserActivity: BaseActivity() {
    private var isNameChecked = false
    private var isVoiceChecked = false

    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adduser)
        topbar_activityTitle.text = resources.getString(R.string.title_new)

        topbar_backButton.setOnClickListener { finish() }

        adduser_voiceWrap.visibility = View.GONE

        adduser_nextButton.setOnClickListener {
            adduser_nameWrap.visibility = View.GONE
            adduser_voiceWrap.visibility = View.VISIBLE
            isNameChecked = true
            adduser_nameChecker.setImageDrawable(resources.getDrawable(R.drawable.ic_check_box_black_24dp))
        }

        adduser_recordButton.setOnClickListener {
            if(!isRecording)
                startRecording()
            else
                stopRecording()
        }
    }

    private fun startRecording() {
        adduser_recordButton.setImageDrawable(resources.getDrawable(R.drawable.ic_mic_black_48dp))
        isRecording = true
    }

    private fun stopRecording() {
        adduser_recordButton.setImageDrawable(resources.getDrawable(R.drawable.ic_mic_none_black_48dp))
        isVoiceChecked = true
        adduser_voiceChecker.setImageDrawable(resources.getDrawable(R.drawable.ic_check_box_black_24dp))
        isRecording = false
    }
}