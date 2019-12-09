package pproject.teamjavis.javis.util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String dbName;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, "javis", null, 1);
        dbName = "javis";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + dbName + " (name TEXT NOT NULL, voice TEXT, tv INTEGER, light INTEGER, gas INTEGER, buy INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + dbName);
        onCreate(db);
    }

    public void openWritable() {
        db = getWritableDatabase();
    }

    public void openReadable() {
        db = getReadableDatabase();
    }

    public void insert(String name, String voice, int tv, int light, int gas, int buy) {
        db.execSQL("INSERT INTO " + dbName + " VALUES(" + "'" + name +"', '" + voice + "', '" + tv + "', '" + light + "', '" + gas + "', '" + buy +"');");
    }

    public void update(String name, String voice, int tv, int light, int gas, int buy) {
        db.execSQL("UPDATE " + dbName + " SET voice = " + voice + ", tv = " + tv + ", light = " + light + ", gas = " + gas + ", buy = "+ buy + "WHERE name = '" + name + ";");
    }

    public void delete(String name) {
        db.execSQL("DELETE FROM " + dbName + " WHERE name='" + name + "';");
    }
}