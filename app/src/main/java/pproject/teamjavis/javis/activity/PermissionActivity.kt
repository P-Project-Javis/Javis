package pproject.teamjavis.javis.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_permission.*
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.adapter.PermissionListAdapter

class PermissionActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        val adapter = PermissionListAdapter()
        permission_list.adapter = adapter

        adapter.add(resources.getDrawable(R.drawable.ic_mic_black_48dp), "마이크", "음성 명령 기능에 사용합니다.")

        permission_confirmButton.setOnClickListener {

        }
    }
}