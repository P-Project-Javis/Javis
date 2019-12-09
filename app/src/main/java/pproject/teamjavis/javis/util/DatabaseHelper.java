package pproject.teamjavis.javis.util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE USERINFO (name TEXT NOT NULL, voice INTEGER, tv INTEGER, light INTEGER, gas INTEGER, buy INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void insert(String name, int voice, int tv, int light, int gas, int buy){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO USERINFO VALUES(" + "'" + name +"', " + voice + ", '" + tv + ", '" + light + ", '" + gas + ", '" + buy +"');");
        db.close();
    }

    public void update(String name, int voice, int tv, int light, int gas, int buy){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE USERINFO SET voice = " + voice + ", tv = " + tv + ", light = " + light + ", gas = " + gas + ", buy = "+ buy + "WHERE name = '" + name + ";");
        db.close();
    }

    public void delete(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM USERINFO WHERE name='" + name + "';");
        db.close();
    }
}