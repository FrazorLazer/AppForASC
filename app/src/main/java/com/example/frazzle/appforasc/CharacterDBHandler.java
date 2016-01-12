package com.example.frazzle.appforasc;

/**
 * Created by Frazzle on 07/01/2016.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;


public class CharacterDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "characters.db";
    public static final String TABLE_CHARACTERS = "characters";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_REWARD = "_reward";


    public CharacterDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_CHARACTERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_REWARD + " TEXT " +
                ");";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTERS);
        onCreate(db);
    }


    public void updateCharacter(int id, String newName, String newReward){

        SQLiteDatabase db = getWritableDatabase();
        String query = "UPDATE " + TABLE_CHARACTERS +
                " SET " + COLUMN_NAME +  " = \"" + newName +
                "\" , " + COLUMN_REWARD +  " = \"" + newReward +
                "\" WHERE " + COLUMN_ID + " = " + id + ";";

        db.execSQL(query);
        db.close();

    }

    public void addCharacter(com.example.frazzle.appforasc.Character character){

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, character.get_name());
        values.put(COLUMN_REWARD, character.get_reward());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CHARACTERS, null, values);
        db.close();

    }

    public void deleteCharacter(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CHARACTERS + " WHERE " + COLUMN_ID + " = " + id + ";");
        db.close();
    }

    public String dbToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CHARACTERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            dbString = dbString + c.getString(c.getColumnIndex("_id"))+ "   "
                    + c.getString(c.getColumnIndex("_name")) + "   "
                    + c.getString(c.getColumnIndex("_reward"))
                    + "\n";

            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;
    }

    public Character[] returnCharacters(){

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CHARACTERS + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        int numberOfCharacters = c.getCount();
        Character[] characters = new Character[numberOfCharacters];
        c.close();

        Cursor c2 = db.rawQuery(query, null);
        c2.moveToFirst();
        int i = 0;
        while(!c2.isAfterLast()){
            String name = c2.getString(c2.getColumnIndex("_name"));
            String reward = c2.getString(c2.getColumnIndex("_reward"));
            int id = Integer.parseInt(c2.getString(c2.getColumnIndex("_id")));

            characters[i] = new Character(name, reward, id);
            i++;
            c2.moveToNext();
        }

        c2.close();
        db.close();
        return characters;

    }
}
