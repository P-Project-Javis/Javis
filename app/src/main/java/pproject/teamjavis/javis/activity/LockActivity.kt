/*
권한 설정 액티비티 클래스.

등록된 사용자의 권한 설정을 담당하는 클래스. 권한이 없는 행동은 명령 불가.
 */
package pproject.teamjavis.javis.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.layout_topbar.*
import pproject.teamjavis.javis.R

class LockActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock)
        activityTitle.text = resources.getString(R.string.title_lock)

        backButton.setOnClickListener {
            finish()
        }
    }
}