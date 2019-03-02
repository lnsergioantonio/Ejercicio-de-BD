package com.example.ejemplobasededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ejemplobasededatos.POJO.Plant;

import java.util.ArrayList;
import java.util.List;

// origen de los datos
public class PlantsDataSource {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase database;

    private static final String[] AllColumns= {
            PlantDBOpenHelper.COLUMN_ID,
            PlantDBOpenHelper.COLUMN_BOTANICAL,
            PlantDBOpenHelper.COLUMN_PRICE
    };

    public PlantsDataSource(Context context){
        openHelper = new PlantDBOpenHelper(context);
    }

    public void open(){
        database = openHelper.getWritableDatabase();
    }
    public void close(){
        openHelper.close();
    }

    public Plant create(Plant plant){
        ContentValues values = new ContentValues();
        values.put(PlantDBOpenHelper.COLUMN_BOTANICAL,plant.getBotanical());
        values.put(PlantDBOpenHelper.COLUMN_PRICE,plant.getPrice());

        long insertId = database.insert(PlantDBOpenHelper.TABLE_PLANTS,null,values);
        plant.setId(insertId);

        return plant;
    }

    public List<Plant> findAll(){
        Cursor cursor = database.query(PlantDBOpenHelper.TABLE_PLANTS,AllColumns,null,null,null,null,null);
        List<Plant> plants = cursorToList(cursor);
        return plants;
    }

    public List<Plant> findBy(String selection, String orderBy){
        Cursor cursor =database.query(PlantDBOpenHelper.TABLE_PLANTS,AllColumns,selection,null,null,null,orderBy);
        List<Plant> plants = cursorToList(cursor);
        return plants;
    }

    public Boolean deleteById(String plantsId){
        String[] selectionArgs = {plantsId};
        return database.delete(PlantDBOpenHelper.TABLE_PLANTS,
                            PlantDBOpenHelper.COLUMN_ID+" = ?",
                                        selectionArgs) > 0;
    }

    public List<Plant> cursorToList(Cursor cursor){
        List<Plant> plantList = new ArrayList<Plant>();

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                Plant plant = new Plant();
                plant.setId(cursor.getLong(cursor.getColumnIndex(PlantDBOpenHelper.COLUMN_ID)));
                plant.setBotanical(cursor.getString(cursor.getColumnIndex(PlantDBOpenHelper.COLUMN_BOTANICAL)));
                plant.setPrice(cursor.getLong(cursor.getColumnIndex(PlantDBOpenHelper.COLUMN_PRICE)));
                plantList.add(plant);
            }
        }
        return plantList;
    }
}
