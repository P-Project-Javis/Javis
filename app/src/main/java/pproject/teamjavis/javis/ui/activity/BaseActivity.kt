/*
베이스 액티비티 클래스

이 앱에서 사용할 액티비티 클래스들의 부모 클래스. 아래 기능을 사용하려면 이 클래스를 상속해야만 함.

1. 액티비티 생명주기에 따라 로그를 찍음.(디버깅의 용이함을 위해 사용)
2. 화면을 세로로 고정.
3. log() 메소드를 통한 간편한 로깅 가능.
4. makeToast() 메소드를 통한 간편한 토스트 생성.
 */
package pproject.teamjavis.javis.ui.activity

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

open class BaseActivity: AppCompatActivity() {
    private var Tag: String = this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //액티비티 화면을 세로로 고정
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        log("onCreate() 실행")
    }

    override fun onStart() {
        super.onStart()
        log("onStart() 실행")
    }

    override fun onResume() {
        super.onResume()
        log("onResume() 실행")
    }

    override fun onRestart() {
        super.onRestart()
        log("onRestart() 실행")
    }

    override fun onPause() {
        super.onPause()
        log("onPause() 실행")
    }

    override fun onStop() {
        super.onStop()
        log("onStop() 실행")
    }

    override fun onDestroy() {
        super.onDestroy()
        log( "onDestroy() 실행")
    }

    fun log(text: String) {
        Log.v(Tag, text)
    }

    fun makeToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    fun checkPermission(): Int {
        val micPermission = ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.RECORD_AUDIO)
        val writeStoragePermission = ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(micPermission == PackageManager.PERMISSION_GRANTED && writeStoragePermission == PackageManager.PERMISSION_GRANTED) {
            return PackageManager.PERMISSION_GRANTED
        }
        else {
            return PackageManager.PERMISSION_DENIED
        }
    }
}