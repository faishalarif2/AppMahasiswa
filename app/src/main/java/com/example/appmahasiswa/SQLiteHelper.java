package com.example.appmahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String namadb = "datamahasiswa.db";
    private static final String namatabel = "mahasiswa";

    private static final String kolom1 = "ID";
    private static final String kolom2 = "NAMA";
    private static final String kolom3 = "NIM";
    private static final String kolom4 = "JURUSAN";
    private static final String kolom5 = "ALAMAT";
    private static final String kolom6 = "MATA KULIAH";
    private static final String kolom7 = "TELEPON";


    public SQLiteHelper(@Nullable Context context) {
        super(context, namadb, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + namatabel + "(" +
                kolom1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                kolom2 + " TEXT, " +
                kolom3 + " TEXT, " +
                kolom4 + " TEXT, " +
                kolom5 + " TEXT, " +
                kolom6 + " TEXT, " +
                kolom7 + " TEXT " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + namatabel);
    }

    public Boolean insertData(String nama, String nim, String jurusan, String alamat, String matkul, String telepon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(kolom2, nama);
        values.put(kolom3, nim);
        values.put(kolom4, jurusan);
        values.put(kolom5, alamat);
        values.put(kolom6, matkul);
        values.put(kolom7, telepon);

        long result = db.insert(namatabel, null, values);
        return result != -1;
    }

    public Cursor getDataAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + namatabel, null);
    }

    public boolean updateData(String id, String nama, String nim, String jurusan, String alamat, String matkul, String telepon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(kolom1,id);
        values.put(kolom2,nama);
        values.put(kolom3,nim);
        values.put(kolom4,jurusan);
        values.put(kolom5,alamat);
        values.put(kolom6, matkul);
        values.put(kolom7, telepon);
        db.update(namatabel,values,kolom1+" = ? ",new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(namatabel,kolom1+" ? ",new String[]{id});
    }
}
