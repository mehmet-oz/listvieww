package com.example.listvieww;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listW;
    Button getir, kaydet, sil;
    EditText isim, numara, sinif;
    Context c;
    List<String> ogrenciler;
    MyDataBase mdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        intentAl();
        buttonClicked();


    }

    public void initialize() {

        getir = findViewById(R.id.getir);
        isim = findViewById(R.id.adSoyad);

        sinif = findViewById(R.id.sinif);
        numara = findViewById(R.id.numara);
        kaydet = findViewById(R.id.kaydet);
        listW = findViewById(R.id.lv);
        sil = findViewById(R.id.sil);
        c = this;
        mdb = new MyDataBase(c);
    }

    public void buttonClicked() {
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ogrenci o;
                int isimN = isim.getText().toString().length();
                int sinifN = sinif.getText().toString().length();
                int numaraN = numara.getText().toString().length();

                if (sinifN==0 || numaraN==0){
                    Toast.makeText(c, "Sınıf ve No girilmeli" , Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(isimN==0){
                    o= new Ogrenci("Yok",sinif.getText().toString(),numara.getText().toString(),"0","0");
                }
                else {
                    o= new Ogrenci( isim.getText().toString(),sinif.getText().toString(),numara.getText().toString(),"0","0");
                }




                if (mdb.ekle(o)==-1) {
                    Toast.makeText(c, "kayıt başarısız " , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(c, "kayıt başarılı", Toast.LENGTH_SHORT).show();
                }


            }
        });
        getir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String klas = sinif.getText().toString();
                ogrenciler = mdb.getir(klas);
                final String[] str = new String[ogrenciler.size()];
                for (int i = 0; i < ogrenciler.size(); i++) {
                    str[i] = ogrenciler.get(i);
                }
                lisViewOlustur(str);

            }
        });
        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = numara.getText().toString();
                mdb.sil(number);


            }
        });
    }

    public void lisViewOlustur(final String[] s) {


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, s);
        listW.setAdapter(adapter);
        listW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String num = s[position].substring(0, s[position].indexOf(" "));
                Intent i = new Intent(MainActivity.this, Sonuc.class);

                i.putExtra("numara", num);
                startActivity(i);
            }
        });
        listW.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                // TODO Auto-generated method stub
                String num = s[pos].substring(0, s[pos].indexOf(" "));

                mdb.sil(num);

                Toast.makeText(c, s[pos] + " silindi", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }

    private void intentAl() {
        Intent a = getIntent();
        String strSinif = a.getStringExtra("sinif");
        sinif.setText(strSinif);
    }


}
