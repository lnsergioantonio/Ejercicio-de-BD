package com.example.ejemplobasededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlantDBOpenHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG= "LOGTAG";

    private static final String DATABASE_NAME = "catalog_plants.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PLANTS = "plants";
    public static final String COLUMN_ID = "plantsId";
    public static final String COLUMN_BOTANICAL = "botanical";
    public static final String COLUMN_PRICE = "price";

    public static final String TABLE_CREATE =
            "CREATE TABLE "+TABLE_PLANTS+" ("+
                    COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BOTANICAL+" TEXT, " +
                    COLUMN_PRICE+" NUMERIC " +
                    ")";

    public PlantDBOpenHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(LOG_TAG,"Table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //VERIFICA LA BASE DE DATOS Y VER QUE VERSION SE TIENE Y A PARTIR DE ESA VERSION SE ACTUALIZA
        db.execSQL("DROP TABLE IF EXIST "+TABLE_PLANTS);
        onCreate(db);
    }



}
