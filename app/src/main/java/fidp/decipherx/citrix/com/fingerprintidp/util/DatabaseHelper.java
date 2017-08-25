package fidp.decipherx.citrix.com.fingerprintidp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.icu.text.DateFormat;

/**
 * Created by sanketmishra on 8/19/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "FIDP.db";
    public static final String TABLE_NAME = "CLIENT";
    public static final String COL1 = "ID";
    public static final String COL2 = "FQDN";
    public static final String COL3 = "CLIENT_ID";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createClientTable = String.format("CREATE TABLE %s ( %s  INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE NOT NULL, %s TEXT UNIQUE NOT NULL)", TABLE_NAME,COL1,COL2,COL3) ;
        db.execSQL(createClientTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME );

    }

    public Boolean addClient(String fqdn, String clientId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, fqdn);
        contentValues.put(COL3, clientId);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery( String.format("SELECT * FROM %s ", TABLE_NAME) , null);
        return data;

    }
}
