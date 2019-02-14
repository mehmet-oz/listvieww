package com.example.listvieww;

public class Ogrenci {
    private String artiSayisi;
    private String eksiSayisi;
    private String isim, sinif;
    private String numara;

    public Ogrenci(String isim, String sinif, String numara) {
        this.isim = isim;
        this.sinif = sinif;
        this.numara = numara;
    }

    public Ogrenci(String isim, String sinif, String numara, String artiSayisi, String eksiSayisi) {
        this.artiSayisi = artiSayisi;
        this.eksiSayisi = eksiSayisi;
        this.isim = isim;
        this.sinif = sinif;
        this.numara = numara;
    }

    public Ogrenci(String sinif, String numara) {
        this.sinif = sinif;
        this.numara = numara;
    }

    public Ogrenci(String numara) {
        this.numara = numara;
    }
    public Ogrenci() {

    }



    public String getArtiSayisi() {
        return artiSayisi;
    }

    public void setArtiSayisi(String artiSayisi) {
        this.artiSayisi = artiSayisi;
    }

    public String getEksiSayisi() {
        return eksiSayisi;
    }

    public void setEksiSayisi(String eksiSayisi) {
        this.eksiSayisi = eksiSayisi;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSinif() {
        return sinif;
    }

    public void setSinif(String sinif) {
        this.sinif = sinif;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }

    public void increaseArtiSayisi() {
        int i= Integer.parseInt(this.artiSayisi);
        i++;
        artiSayisi=String.valueOf(i);
    }

    public void decreaseArtiSayisi() {
        int i= Integer.parseInt(this.artiSayisi);
        i--;
        artiSayisi=String.valueOf(i);
    }

    public void increaseEksiSayisi() {
        int i= Integer.parseInt(this.eksiSayisi);
        i++;
       eksiSayisi=String.valueOf(i);

    }

    public void decreaseEksiSayisi() {
        int i= Integer.parseInt(this.eksiSayisi);
        i--;
        eksiSayisi=String.valueOf(i);
    }

}
