package pproject.teamjavis.javis.util;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import pproject.teamjavis.javis.R;
import pproject.teamjavis.javis.ui.item.AuthorityChildItem;
import pproject.teamjavis.javis.ui.item.AuthorityParentItem;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private String dbName;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, "javis", null, 1);
        this.context = context;
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

    public void update(String name, String voice, boolean tv, boolean light, boolean gas, boolean buy) {
        int valTv = boolean2Int(tv);
        int valLight = boolean2Int(light);
        int valGas = boolean2Int(gas);
        int valBuy = boolean2Int(buy);

        db.execSQL("UPDATE " + dbName + " SET name = '" + name + "', voice = '" + voice + "', tv = '" + valTv + "', light = '" + valLight + "', gas = '" + valGas + "', buy = '"+ valBuy + "' WHERE name = '" + name + "';");
    }

    public void update(String name, String voice, int tv, int light, int gas, int buy) {
        db.execSQL("UPDATE " + dbName + " SET name = '" + name + "', voice = '" + voice + "', tv = '" + tv + "', light = '" + light + "', gas = '" + gas + "', buy = '"+ buy + "' WHERE name = '" + name + "';");
    }

    public void update(AuthorityParentItem item) {
        String name = item.getName();
        String voice = item.getVoicePath();
        ArrayList<AuthorityChildItem> child = item.getAuthorityList();
        int tv = boolean2Int(child.get(0).isChecked());
        int light = boolean2Int(child.get(1).isChecked());
        int gas = boolean2Int(child.get(2).isChecked());
        int buy = boolean2Int(child.get(3).isChecked());

        db.execSQL("UPDATE " + dbName + " SET name = '" + name + "', voice = '" + voice + "', tv = '" + tv + "', light = '" + light + "', gas = '" + gas + "', buy = '"+ buy + "' WHERE name = '" + name + "';");
    }

    public AuthorityParentItem select(String name) {
        AuthorityParentItem item;

        Cursor cursor = db.rawQuery("SELECT * FROM " + dbName + " WHERE name='" + name + "';", null);
        if(cursor.getCount() > 0) {
            cursor.moveToNext();
            String valName = cursor.getString(0);
            String voice = cursor.getString(1);

            AuthorityChildItem tv = new AuthorityChildItem(
                    context.getDrawable(R.drawable.ic_tv_black_24dp),
                    "TV",
                    int2Boolean(cursor.getInt(2)));
            AuthorityChildItem light = new AuthorityChildItem(
                    context.getDrawable(R.drawable.ic_lightbulb_outline_black_24dp),
                    "조명",
                    int2Boolean(cursor.getInt(3)));
            AuthorityChildItem gas = new AuthorityChildItem(
                    context.getDrawable(R.drawable.ic_burner_black_24dp),
                    "가스레인지",
                    int2Boolean(cursor.getInt(4)));
            AuthorityChildItem buy = new AuthorityChildItem(
                    context.getDrawable(R.drawable.ic_shopping_cart_black_24dp),
                    "인터넷 쇼핑",
                    int2Boolean(cursor.getInt(5)));

            ArrayList<AuthorityChildItem> child = new ArrayList<>();
            child.add(tv);
            child.add(light);
            child.add(gas);
            child.add(buy);

            item = new AuthorityParentItem(valName, voice, child);
            return item;
        }

        return null;
    }

    public AuthorityParentItem select(int position) {
        AuthorityParentItem item = selectAll().get(position);

        if(item == null)
            return null;
        else
            return item;
    }

    public ArrayList<AuthorityParentItem> selectAll() {
        ArrayList<AuthorityParentItem> items = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + dbName + ";", null);
        if(cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                String name = cursor.getString(0);
                String voice = cursor.getString(1);

                AuthorityChildItem tv = new AuthorityChildItem(
                        context.getDrawable(R.drawable.ic_tv_black_24dp),
                        "TV",
                        int2Boolean(cursor.getInt(2)));
                AuthorityChildItem light = new AuthorityChildItem(
                        context.getDrawable(R.drawable.ic_lightbulb_outline_black_24dp),
                        "조명",
                        int2Boolean(cursor.getInt(3)));
                AuthorityChildItem gas = new AuthorityChildItem(
                        context.getDrawable(R.drawable.ic_burner_black_24dp),
                        "가스레인지",
                        int2Boolean(cursor.getInt(4)));
                AuthorityChildItem buy = new AuthorityChildItem(
                        context.getDrawable(R.drawable.ic_shopping_cart_black_24dp),
                        "인터넷 쇼핑",
                        int2Boolean(cursor.getInt(5)));

                ArrayList<AuthorityChildItem> child = new ArrayList<>();
                child.add(tv);
                child.add(light);
                child.add(gas);
                child.add(buy);
                AuthorityParentItem parent = new AuthorityParentItem(name, voice, child);

                items.add(parent);
            }
        }
        else
            return null;

        cursor.close();

        return items;
    }

    public boolean checkName(String name) {
        AuthorityParentItem item = select(name);
        if(item != null) {
            String valName = item.getName();
            if(name.equals(valName))
                return false;
            else
                return true;
        }
        else
            return true;
    }

    public void delete(String name) {
        db.execSQL("DELETE FROM " + dbName + " WHERE name='" + name + "';");
    }

    private boolean int2Boolean(int value) {
        if(value > 0)
            return true;
        else
            return false;
    }

    private int boolean2Int(boolean value) {
        if(value)
            return 1;
        else
            return 0;
    }
}