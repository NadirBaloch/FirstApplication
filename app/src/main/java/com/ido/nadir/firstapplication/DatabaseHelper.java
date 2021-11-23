package com.ido.nadir.firstapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "currencyDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CURRENCY_DETAIL = "currency_details";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CURRENCY = "currency";
    public static final String CREATE_TABLE_CURRENCY = "CREATE TABLE "+TABLE_CURRENCY_DETAIL+
            " ("+ID+" integer primary key AUTOINCREMENT, "+NAME+" varchar(20), "+
            CURRENCY+" varchar(20))";


    public DatabaseHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    public DatabaseHelper(Context paramContext) {
        super(paramContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CURRENCY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertIntoTable(Country country){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,country.name);
        values.put(CURRENCY,country.currency);
        db.insert(TABLE_CURRENCY_DETAIL,null,values);
        db.close();
    }

    public  Cursor getCurrencyDetails(){
        String query = "select * from "+TABLE_CURRENCY_DETAIL;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query,null);
    }
}
