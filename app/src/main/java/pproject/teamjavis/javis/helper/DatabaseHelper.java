package pproject.teamjavis.javis.helper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import pproject.teamjavis.javis.R;

public class DatabaseHelper extends Activity {
    EditText adduser_input;
    DBHelper dbHelper;

    final static String dbName = "user1.db";
    final static int dbVersion = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);


        adduser_input = (EditText) findViewById(R.id.adduser_input);
        dbHelper = new DBHelper(this, dbName, null, dbVersion);

    }
    public void mOnClick(View v) {
        SQLiteDatabase db;
        String sql;

        switch (v.getId()) {
            case R.id.adduser_confirmButton: //추가 버튼(insert)
                ImageButton ib = (ImageButton) findViewById(R.id.ImageButton);
                String name = adduser_input.getText().toString();

                db = dbHelper.getWritableDatabase();
                sql = String.format("INSERT INTO t3 VALUES('" + name  + "',0);");

                db.execSQL(sql);

                Intent intent = new Intent(
                        getApplicationContext(),
                        taskview.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_LONG).show();

                break;
        }
        dbHelper.close();
    }
    static class DBHelper extends SQLiteOpenHelper {

        //생성자 - database 파일을 생성한다.
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //DB 처음 만들때 호출. - 테이블 생성 등의 초기 처리.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE user1 (name TEXT, finish INTEGER);");
            //result.append("\n user1 테이블 생성 완료.");
        }

        //DB 업그레이드 필요 시 호출. (version값에 따라 반응)
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS user1");
            onCreate(db);
        }

    }
}