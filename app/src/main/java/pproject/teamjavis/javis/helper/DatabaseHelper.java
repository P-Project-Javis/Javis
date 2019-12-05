package pproject.teamjavis.javis.helper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import pproject.teamjavis.javis.R;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE USERINFO (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+"name TEXT NOT NULL, voice INTEGER, tv INTEGER, light INTEGER, gas INTEGER, buy INTEGER DEFAULT 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void insert(String name, int voice, int tv, int light, int gas, int buy){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO USERINFO VALUES(null, " + "'" + name +"', " + voice + ", '" + tv + ", '" + light + ", '" + gas + ", '" + buy +"');");
        db.close();
    }
}