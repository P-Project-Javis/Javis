package pproject.teamjavis.javis.ui.popup

import android.content.Context
import android.graphics.Point
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import kotlinx.android.synthetic.main.layout_topbar.view.*
import kotlinx.android.synthetic.main.layout_user_list.view.*
import pproject.teamjavis.javis.R

class UserListPopup(context: Context) {
    companion object {
        const val MODE_LOOKUP = 0
        const val MODE_DELETE = 1
        const val MODE_EDIT = 2

        const val RETURN_WIDTH = 10
        const val RETURN_HEIGHT = 11
    }

    private val context by lazy { context }
    private val inflater by lazy { context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater }
    private val popupView by lazy { inflater.inflate(R.layout.layout_user_list, null) }
    private val popupWindow by lazy { PopupWindow(popupView, getDisplaySize(RETURN_WIDTH), getDisplaySize(RETURN_HEIGHT)) }

    init {
        popupView.isFocusable = false
        popupView.user_list_topbar.topbar_backButton.setOnClickListener { popupWindow.dismiss() }
    }

    fun setMode(mode: Int) {
        when(mode) {
            MODE_LOOKUP -> {

            }
            MODE_DELETE -> {

            }
            MODE_EDIT -> {

            }
        }
    }

    fun pop(parent: View) {
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0)
    }

    private fun getDisplaySize(mode: Int): Int {
        val display = ((context.getSystemService(Context.WINDOW_SERVICE)) as WindowManager).defaultDisplay
        val point = Point()
        display.getSize(point)

        val width = (point.x * 0.8).toInt()
        val height = (point.y * 0.8).toInt()

        return when (mode) {
            RETURN_WIDTH -> {
                width
            }
            RETURN_HEIGHT -> {
                height
            }
            else -> {
                LinearLayout.LayoutParams.MATCH_PARENT
            }
        }
    }
}