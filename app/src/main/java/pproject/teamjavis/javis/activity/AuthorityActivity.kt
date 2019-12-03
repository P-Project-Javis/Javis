/*
권한 설정 액티비티 클래스.

등록된 사용자의 권한 설정을 담당하는 클래스. 권한이 없는 행동은 명령 불가.
 */
package pproject.teamjavis.javis.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_authority.*
import kotlinx.android.synthetic.main.layout_topbar.*
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.adapter.AuthorityListAdapter
import pproject.teamjavis.javis.item.AuthorityChildItem
import pproject.teamjavis.javis.item.AuthorityParentItem

class AuthorityActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authority)
        topbar_activityTitle.text = resources.getString(R.string.title_lock)

        topbar_backButton.setOnClickListener { finish() }

        val adapter = AuthorityListAdapter()
        authority_list.setAdapter(adapter)
        adapter.add(makeData())
    }

    private fun makeData(): ArrayList<AuthorityParentItem> {
        val data = ArrayList<AuthorityParentItem>()

        val names = ArrayList<String>()
        names.add("엄마")
        names.add("아빠")
        names.add("첫째")
        names.add("둘째")

        val authorityList = ArrayList<AuthorityChildItem>()
        authorityList.add(AuthorityChildItem(resources.getDrawable(R.drawable.ic_tv_black_24dp), "TV", true))
        authorityList.add(AuthorityChildItem(resources.getDrawable(R.drawable.ic_lightbulb_outline_black_24dp), "조명", true))
        authorityList.add(AuthorityChildItem(resources.getDrawable(R.drawable.ic_burner_black_24dp), "가스레인지", false))
        authorityList.add(AuthorityChildItem(resources.getDrawable(R.drawable.ic_shopping_cart_black_24dp), "온라인 주문", false))

        for(name in names) {
            data.add(AuthorityParentItem(name, authorityList))
        }

        return data
    }
}