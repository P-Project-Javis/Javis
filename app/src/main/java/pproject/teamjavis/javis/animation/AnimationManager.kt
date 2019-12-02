package pproject.teamjavis.javis.animation

import android.content.Context
import android.view.animation.AnimationUtils
import pproject.teamjavis.javis.R

class AnimationManager(context: Context) {
    val animMenuButton = AnimationUtils.loadAnimation(context, R.anim.animation_menu_button)!!
    val animMenuButtonRewind = AnimationUtils.loadAnimation(context, R.anim.animation_menu_button_rewind)!!
    val animSettingButton = AnimationUtils.loadAnimation(context, R.anim.animation_setting_button)!!
    val animSettingButtonRewind = AnimationUtils.loadAnimation(context, R.anim.animation_setting_button_rewind)!!
    val animNewButton = AnimationUtils.loadAnimation(context, R.anim.animation_new_button)!!
    val animNewButtonRewind = AnimationUtils.loadAnimation(context, R.anim.animation_new_button_rewind)!!
    val animLockButton = AnimationUtils.loadAnimation(context, R.anim.animation_lock_button)!!
    val animLockButtonRewind = AnimationUtils.loadAnimation(context, R.anim.animation_lock_button_rewind)!!

    init {
        animMenuButton.fillAfter = true
        animMenuButtonRewind.fillAfter = true
        animSettingButton.fillAfter = true
        animSettingButtonRewind.fillAfter = true
        animNewButton.fillAfter = true
        animNewButtonRewind.fillAfter = true
        animLockButton.fillAfter = true
        animLockButtonRewind.fillAfter = true
    }
}