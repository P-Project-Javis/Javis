/*
베이스 액티비티 클래스

1. 액티비티 생명주기에 따라 로그를 찍음.(디버깅의 용이함을 위해 사용)
2. 화면을 세로로 고정.
3. sysout() 메소드를 통한 간편한 System.out.println() 가능.
4. makeToast() 메소드를 통한 간편한 토스트 생성.
5. makeSnackbar() 메소드를 통한 간편한 스낵바 생성.(확인 버튼이 있는 스낵바 생성 가능)

위 기능을 사용하기 위해서는 액티비티에서 이 클래스를 상속해야 함.
 */
package pproject.teamjavis.javis.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

open class BaseActivity: AppCompatActivity() {
    private var Tag: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //액티비티 화면을 세로로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Log.v(Tag, "onCreate() 실행")
    }

    override fun onStart() {
        super.onStart()
        Log.v(Tag, "onStart() 실행")
    }

    override fun onResume() {
        super.onResume()
        Log.v(Tag, "onResume() 실행")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(Tag, "onRestart() 실행")
    }

    override fun onPause() {
        super.onPause()
        Log.v(Tag, "onPause() 실행")
    }

    override fun onStop() {
        super.onStop()
        Log.v(Tag, "onStop() 실행")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(Tag, "onDestroy() 실행")
    }

    //System.out.println()을 간편하게 띄우는 메소드
    fun sysout(text: String) {
        println(text)
    }

    //토스트를 간편하게 띄우는 메소드
    fun makeToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    //스낵바를 간편하게 띄우는 메소드
    fun makeSnackbar(view: View, message: String, useButton: Boolean) {
        var duration = Snackbar.LENGTH_LONG
        if(useButton)
            duration = Snackbar.LENGTH_INDEFINITE
        val snackbar = Snackbar.make(view, message, duration)
        if(useButton) {
            snackbar.setAction("확인") {
                snackbar.dismiss()
            }
        }
        snackbar.show()
    }
}