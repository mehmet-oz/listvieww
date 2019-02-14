package com.example.listvieww;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class MyDataBase extends SQLiteOpenHelper {
    private static final int Version = 1;
    private static final String Database_Name = "OgrenciDB";
    private static final String Table_Ogrenci = "OGRENCI";

    private static final String ogrenciAdi = "ISIM";
    private static final String ogrenciSinifi = "SINIF";
    private static final String ogrenciNo = "NUMARA";
    private static final String artiSayisi = "ARTI_SAYISI";
    private static final String eksiSayisi = "EKSI_SAYISI";
    private static final String CreateTable = "CREATE TABLE " + Table_Ogrenci + " ( " + ogrenciSinifi + " TEXT, " + ogrenciNo + " TEXT PRIMARY KEY, " + ogrenciAdi +
            " TEXT, " + artiSayisi + " TEXT, " + eksiSayisi + " TEXT);";

    public MyDataBase(Context context) {
        super(context, Database_Name, null, Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS OGRENCI");
        onCreate(db);

    }

    public void ekle(String sinif, String numara, String isim, String eksiler, String artilar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put(ogrenciNo, numara);
        degerler.put(ogrenciAdi, isim);
        degerler.put(artiSayisi, eksiler);
        degerler.put(eksiSayisi, artilar);
        degerler.put(ogrenciSinifi, sinif);
        db.insert(Table_Ogrenci, null, degerler);
        db.close();

    }

    public boolean ekle(String sinif, String numara, String isim) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put(ogrenciNo, numara);
        degerler.put(ogrenciAdi, isim);
        degerler.put(ogrenciSinifi, sinif);
        long result=db.insert(Table_Ogrenci, null, degerler);
        db.close();
        return result==-1 ? false : true;

    }

    public void ekle(String sinif, String numara) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put(ogrenciNo, numara);
        degerler.put(ogrenciSinifi, sinif);
        db.insert(Table_Ogrenci, null, degerler);
        db.close();

    }

    public long ekle(Ogrenci o) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues degerler = new ContentValues();
        degerler.put(ogrenciSinifi, o.getSinif());
        degerler.put(ogrenciNo, o.getNumara());
        degerler.put(ogrenciAdi, o.getIsim());
        degerler.put(artiSayisi, o.getArtiSayisi());
        degerler.put(eksiSayisi, o.getEksiSayisi());


        long x =db.insert(Table_Ogrenci, null, degerler);

        db.close();
        return x;

    }

    public boolean guncelle(Ogrenci o) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues degerler = new ContentValues();
        degerler.put(ogrenciSinifi, o.getSinif());
        degerler.put(ogrenciNo, o.getNumara());
        degerler.put(ogrenciAdi, o.getIsim());
        degerler.put(artiSayisi, o.getArtiSayisi());
        degerler.put(eksiSayisi, o.getEksiSayisi());


        db.update(Table_Ogrenci, degerler, ogrenciNo + " LIKE " + o.getNumara(), null);
        db.close();
        return true;

    }

    public List<String> getir(String klas) {
        List<String> ogrenciler = new ArrayList<>();
        String query = "SELECT * FROM " + Table_Ogrenci + " WHERE SINIF = '" + klas+ "'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        String s;

        if (c.moveToFirst()) {
            do {
                s = c.getInt(1) + "  " + c.getString(2);
                ogrenciler.add(s);

            } while (c.moveToNext());

        }
        db.close();
        c.close();
        return ogrenciler;

    }

    public Ogrenci getirForSonuc(String studentNumber) {
        Ogrenci ogrenci = new Ogrenci();
        String query = "SELECT * FROM " + Table_Ogrenci + " WHERE " + ogrenciNo + " LIKE " + studentNumber;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);


        c.moveToFirst();

        ogrenci.setSinif(c.getString(0));
        ogrenci.setNumara(c.getString(1));
        ogrenci.setIsim(c.getString(2));
        ogrenci.setArtiSayisi(c.getString(3));
        ogrenci.setEksiSayisi(c.getString(4));


        return ogrenci;

    }

    public void sil(String numara) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_Ogrenci, "NUMARA = '" + numara + "'", null);

    }


}