/*
새 사용자 등록 액티비티.

해당 앱을 사용한 새 사용자를 등록할 수 있는 액티비티. 새 사용자 등록 시 권한 설정도 가능.
 */
package pproject.teamjavis.javis.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.layout_topbar.*
import pproject.teamjavis.javis.R

class NewActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        topbar_activityTitle.text = resources.getString(R.string.title_new)

        topbar_backButton.setOnClickListener {
            finish()
        }
    }
}