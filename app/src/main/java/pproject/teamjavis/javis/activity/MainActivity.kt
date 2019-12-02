/*
메인 액티비티 클래스

1. 메뉴 버튼 클릭시 메뉴 아이템 버튼들이 아래로 나옴.(이동 및 회전 애니메이션 적용 예정)
2. 열린 메뉴버튼을 다시 클릭시 메뉴 아이템들이 들어감.
3 .마이크 이미지 클릭시 녹음 활성화.
4. 메뉴의 각 아이템 클릭시 각 액티비티로 이동.
 */
package pproject.teamjavis.javis.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import pproject.teamjavis.javis.R
import pproject.teamjavis.javis.animation.AnimationManager

class MainActivity: BaseActivity() {
    private var isMenuOpen = false
    private val anim = AnimationManager(applicationContext)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuButton.setOnClickListener {
            if(!isMenuOpen) {
                it.startAnimation(anim.animMenuButton)
                settingButton.startAnimation(anim.animSettingButton)
                newButton.startAnimation(anim.animNewButton)
                lockButton.startAnimation(anim.animLockButton)
            }
            else {
                it.startAnimation(anim.animMenuButtonRewind)
                settingButton.startAnimation(anim.animSettingButtonRewind)
                newButton.startAnimation(anim.animNewButtonRewind)
                lockButton.startAnimation(anim.animLockButtonRewind)
            }
            isMenuOpen = !isMenuOpen
        }
    }
}