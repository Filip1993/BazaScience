package com.kesteli.filip.bazascience;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Filip on 8.9.2016..
 */
public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "stranicaDB.db";
    private static final String TABLE_STRANICE = "stranice";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SITE = "_site";
    public static final String COLUMN_HISTORY = "_history";
    public static final String COLUMN_FAVORITE = "_favorite";
    public static final String COLUMN_EUREKA = "_eureka";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE =
                "CREATE TABLE " + TABLE_STRANICE
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY, "
                        + COLUMN_SITE + " TEXT, "
                        + COLUMN_HISTORY + " INTEGER, "
                        + COLUMN_FAVORITE + " INTEGER, "
                        + COLUMN_EUREKA + " INTEGER"
                        + ");";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STRANICE);
        onCreate(db);
    }

    /**
     * Dodaj produkt metoda -> Dodaje se vrijednost unutar baze podataka
     */
    public void addStranica(Stranica stranica) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SITE, stranica.get_site());
        contentValues.put(COLUMN_HISTORY, stranica.get_history());
        contentValues.put(COLUMN_FAVORITE, stranica.get_favorite());
        contentValues.put(COLUMN_EUREKA, stranica.get_eureka());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_STRANICE, null, contentValues);
        sqLiteDatabase.close();
    }

    /**
     * Vrsta QUERY-ja -> Pronađi produkt -> Jedan od SELECT statementa -> Trazenje preko imena osobe
     */
    public Stranica findStranicaZaHistory(int history) {
        String query = "SELECT * FROM " + TABLE_STRANICE
                + " WHERE " + COLUMN_SITE + " = " + history;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Stranica stranica = new Stranica(); //Instanciramo novi objekt osoba i za to koristimo defaultni konstruktor
        if (cursor.moveToFirst() == true) {
            cursor.moveToFirst();
            stranica.set_id(Integer.parseInt(cursor.getString(0)));
            stranica.set_site(cursor.getString(1));
            stranica.set_history(cursor.getInt(2));
            stranica.set_favorite(cursor.getInt(3));
            stranica.set_eureka(cursor.getInt(4));
            cursor.close();
        } else {
            stranica = null;
        }
        sqLiteDatabase.close();
        return stranica;
    }

    public Stranica findStranicaZaSite(String site) {
        String query = "SELECT * FROM " + TABLE_STRANICE
                + " WHERE " + COLUMN_SITE + " = " + site;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Stranica stranica = new Stranica(); //Instanciramo novi objekt osoba i za to koristimo defaultni konstruktor
        if (cursor.moveToFirst() == true) {
            cursor.moveToFirst();
            stranica.set_id(Integer.parseInt(cursor.getString(0)));
            stranica.set_site(cursor.getString(1));
            stranica.set_history(cursor.getInt(2));
            stranica.set_favorite(cursor.getInt(3));
            stranica.set_eureka(cursor.getInt(4));
            cursor.close();
        } else {
            stranica = null;
        }
        sqLiteDatabase.close();
        return stranica;
    }

    /**
     * Vrsta QUERY-ja -> Izbrisi product
     */
    public boolean deleteOsoba(String site) {
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_STRANICE
                + " WHERE " + COLUMN_SITE + " = " + site;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Stranica stranica = new Stranica();
        if (cursor.moveToFirst()) {
            stranica.set_id(Integer.parseInt(cursor.getString(0)));
            sqLiteDatabase.delete(TABLE_STRANICE, COLUMN_ID + " = ?", new String[]{String.valueOf(stranica.get_id())});
            cursor.close();
            result = true;
        }
        sqLiteDatabase.close();
        return result;
    }
}


