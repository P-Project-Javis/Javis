/*
설정 액티비티 클래스

앱의 전반적인 설정 액티비티. 앱의 정보와 간단한 설정 가능.
 */
package pproject.teamjavis.javis.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.layout_topbar.*
import pproject.teamjavis.javis.R

class SettingActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        topbar_activityTitle.text = resources.getString(R.string.title_setting)

        topbar_backButton.setOnClickListener {
            finish()
        }

        setting_appVersion.text = applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0).versionName

        setting_lookup.setOnClickListener { makeToast("조회") }

        setting_newUser.setOnClickListener { makeToast("추가") }

        setting_editUser.setOnClickListener { makeToast("수정") }

        setting_removeUser.setOnClickListener { makeToast("삭제") }

        setting_clearUser.setOnClickListener { makeToast("초기화") }

        setting_volume.setOnClickListener { makeToast("볼륨") }
    }
}