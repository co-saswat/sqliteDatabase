package com.saswat.myfouthtask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "College.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "students_details";
    private static final String COL_STUDENT_ID = "id";
    private static final String COL_STUDENT_NAME = "student_name";
    private static final String COL_STUDENT_GUARDIAN = "parent_name";
    private static final String COL_STUDENT_EMAIL = "student_email";
    private static final String COL_STUDENT_PHONE = "student_phone";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" + COL_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_STUDENT_NAME + " TEXT, " +
                COL_STUDENT_GUARDIAN + " TEXT, " +
                COL_STUDENT_EMAIL + " TEXT, " +
                COL_STUDENT_PHONE + " INTEGER);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addDataToDatabase(String std_name, String std_guardian_name, String std_email, int std_phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_STUDENT_NAME, std_name);
        cv.put(COL_STUDENT_GUARDIAN, std_guardian_name);
        cv.put(COL_STUDENT_EMAIL, std_email);
        cv.put(COL_STUDENT_PHONE, std_phone);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor retriveDataToDatabase() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase dblite = this.getReadableDatabase();
        Cursor cursor = null;
        if (dblite != null) {
            cursor = dblite.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String std_name, String std_parent_name, String std_email, String std_phone_no) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_STUDENT_NAME, std_name);
        cv.put(COL_STUDENT_GUARDIAN, std_parent_name);
        cv.put(COL_STUDENT_EMAIL, std_email);
        cv.put(COL_STUDENT_PHONE, std_phone_no);

        long result = sqLiteDatabase.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed!!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success!!!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRowData(String row_id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME,"id=?", new String[]{row_id});
        if (result==-1){
            Toast.makeText(context, "Failed!!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success!!!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAll(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
