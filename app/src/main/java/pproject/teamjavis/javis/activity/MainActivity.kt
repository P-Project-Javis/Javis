/*
메인 액티비티 클래스

1. 메뉴 버튼 클릭시 메뉴 아이템 버튼들이 아래로 나옴.(이동 및 회전 애니메이션 적용 예정)
2. 열린 메뉴버튼을 다시 클릭시 메뉴 아이템들이 들어감.
3 .마이크 이미지 클릭시 녹음 활성화.
4. 메뉴의 각 아이템 클릭시 각 액티비티로 이동.
 */
package pproject.teamjavis.javis.activity

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main_close.*
import pproject.teamjavis.javis.R

class MainActivity: BaseActivity() {
    private var isMenuOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_close)

        menuButton.setOnClickListener {
            if(!isMenuOpen) {
                updateView(R.layout.activity_main_open)
            }
            else {
                updateView(R.layout.activity_main_close)
            }
            isMenuOpen = !isMenuOpen
        }

        settingButton.setOnClickListener {
            makeToast("설정")
        }

        newButton.setOnClickListener {
            makeToast("추가")
        }

        lockButton.setOnClickListener {
            makeToast("권한")
        }
    }

    private fun updateView(id: Int) {
        var targetConstSet = ConstraintSet()
        targetConstSet.clone(this, id)
        targetConstSet.applyTo(root)

        var trans = ChangeBounds()
        trans.interpolator = AccelerateInterpolator()
        TransitionManager.beginDelayedTransition(root, trans)
    }
}