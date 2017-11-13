package com.example.zeeshan.sir_tictactoo;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Zeeshan on 11/13/2017.
 */

public class DbHelper  extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "myDatabase";    // Database Name
    private static final String TABLE_NAME = "score";   // Table Name
    private static final int DATABASE_Version = 1;   // Database Version
    private static final String UID="_id";     // Column I (Primary Key)
    private static final String Player1 = "player1";    //Column II
    private static final String Player2= "player2";    // Column III
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Player1+" VARCHAR(255) ,"+
            Player2+" VARCHAR(225));";
    private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            //Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String myBookQueryCreate="CREATE TABLE  IF NOT EXISTS `score` (\n" +
                "\t`_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`player1`\tTEXT,\n" +
                "\t`player2`\tTEXT,\n" +
                ");";

        try {
     //       Message.message(context,"OnUpgrade");
            db.execSQL(myBookQueryCreate);
            onCreate(db);
        }catch (Exception e) {
       //     Message.message(context,""+e);
        }
    }


     public int insertScore(Integer score1,Integer score2){
        Integer value=0;//this variable is used to conform the successfull insertion into database


       SQLiteDatabase  db =getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("player1",score1);
        contentValues.put("player2",score2);
        try{

            db.insert("score",null,contentValues);
            db.close();
            value=1;//this will conform the successfull insertion into database
        }catch (Exception e){
            Log.i("Insertion","Insertion error "+e.getMessage());
        }
        return  value;
    }

    public Cursor lisetRecord(){

         SQLiteDatabase db=getReadableDatabase();

         Cursor cursor;
        cursor=db.rawQuery("SELECT player1,player2 from score",null);
        return  cursor;
    }


}
