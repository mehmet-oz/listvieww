package com.example.listvieww;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sonuc extends AppCompatActivity {
    EditText isim, sinif;
            TextView artiSayisi, eksiSayisi, numara;
    Button anasayfa, guncelle, incEksi, decEksi, incArti, decArti;
    Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);
        initialize();

        intenAl();

        numaradanOgrenciBilgileriniAl(numara.getText().toString());


        buttonClicked();


    }

    private void numaradanOgrenciBilgileriniAl(String s) {

        MyDataBase mdb = new MyDataBase(c);

        Ogrenci o = mdb.getirForSonuc(s);

        numara.setText(o.getNumara());
        isim.setText(o.getIsim());
        sinif.setText(o.getSinif());
        if (o.getArtiSayisi() == null) {

            artiSayisi.setText("0");
        }
        else artiSayisi.setText(o.getArtiSayisi());
        if (o.getEksiSayisi() == null) {
            eksiSayisi.setText("0");
        }
        else eksiSayisi.setText(o.getEksiSayisi());

    }

    private void intenAl() {
        Intent i = getIntent();
        String s = i.getStringExtra("numara");
        numara = findViewById(R.id.numara1);
        numara.setText(s);


    }

    public void buttonClicked() {
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(c, MainActivity.class);
                a.putExtra("sinif", sinif.getText().toString());
                startActivity(a);
            }
        });
        decArti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = artiSayisi.getText().toString();
                int i = Integer.parseInt(s);
                if (i > 0)
                    i--;
                s = String.valueOf(i);
                artiSayisi.setText(s);


            }
        });
        incArti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = artiSayisi.getText().toString();
                int i = Integer.parseInt(s);
                i++;
                s = String.valueOf(i);
                artiSayisi.setText(s);
            }
        });
        decEksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = eksiSayisi.getText().toString();
                int i = Integer.parseInt(s);
                if (i > 0)
                    i--;
                s = String.valueOf(i);
                eksiSayisi.setText(s);

            }
        });
        incEksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = eksiSayisi.getText().toString();
                int i = Integer.parseInt(s);
                i++;
                s = String.valueOf(i);
                eksiSayisi.setText(s);

            }
        });
        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ogrenci o= new Ogrenci();
                o.setEksiSayisi(eksiSayisi.getText().toString());
                o.setArtiSayisi(artiSayisi.getText().toString());
                o.setSinif(sinif.getText().toString());
                o.setNumara(numara.getText().toString());
                o.setIsim(isim.getText().toString());
                MyDataBase mdb = new MyDataBase(c);
                if(mdb.guncelle(o)){
                    Toast.makeText(c,"Güncelleme Başarılı", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(c,"Güncelleme Başarısız", Toast.LENGTH_SHORT).show();
                };




            }
        });


    }

    public void initialize() {
        isim = findViewById(R.id.name1);
        sinif = findViewById(R.id.sinif1);
        artiSayisi = findViewById(R.id.arti1);
        eksiSayisi = findViewById(R.id.eksi1);
        guncelle = findViewById(R.id.kaydet1);
        incEksi = findViewById(R.id.incEksi);
        decEksi = findViewById(R.id.decEksi);
        incArti = findViewById(R.id.incArti);
        decArti = findViewById(R.id.decArti);
        anasayfa = findViewById(R.id.anasayfa);
        c = this;
    }

}
