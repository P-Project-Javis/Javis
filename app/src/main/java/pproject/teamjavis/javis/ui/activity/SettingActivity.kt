/*
설정 액티비티 클래스

앱의 전반적인 설정 액티비티. 앱의 정보와 간단한 설정 가능.
 */
package pproject.teamjavis.javis.ui.activity

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.layout_topbar.*
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.ui.popup.UserListPopup

class SettingActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        topbar_activityTitle.text = resources.getString(R.string.title_setting)

        topbar_backButton.setOnClickListener { finish() }

        setting_lookup.setOnClickListener {
            val popup = UserListPopup(applicationContext)
            popup.setMode(UserListPopup.MODE_LOOKUP)
            popup.pop(setting_parent)
        }
        setting_adduser.setOnClickListener {
            val intent = Intent(applicationContext, AdduserActivity::class.java)
            startActivity(intent)
        }
        setting_removeUser.setOnClickListener {
            val popup = UserListPopup(applicationContext)
            popup.setMode(UserListPopup.MODE_DELETE)
            popup.pop(setting_parent)
        }

        setting_appVersion.text = "Javis 버전: " + applicationContext.packageManager.getPackageInfo(applicationContext.packageName, 0).versionName
        setting_developer.setOnClickListener { makeToast("개발자") }
        setting_openSource.setOnClickListener { makeToast("오픈소스 라이브러리") }
    }
}