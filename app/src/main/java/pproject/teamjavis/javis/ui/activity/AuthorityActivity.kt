/*
권한 설정 액티비티 클래스.

등록된 사용자의 권한 설정을 담당하는 클래스. 권한이 없는 행동은 명령 불가.
 */
package pproject.teamjavis.javis.ui.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_authority.*
import kotlinx.android.synthetic.main.layout_topbar.*
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.ui.adapter.AuthorityListAdapter
import pproject.teamjavis.javis.util.DatabaseHelper

class AuthorityActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authority)
        topbar_activityTitle.text = resources.getString(R.string.title_lock)

        topbar_backButton.setOnClickListener { finish() }

        val adapter = AuthorityListAdapter()
        authority_list.setAdapter(adapter)

        val db = DatabaseHelper(applicationContext)
        db.openReadable()
        adapter.add(db.selectAll())
        db.close()
    }
}