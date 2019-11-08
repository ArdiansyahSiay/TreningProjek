package com.trening.day1.dirabout;

import android.widget.ImageView;


import com.trening.day1.R;

import java.util.ArrayList;

public class AboutTim {

    private static String []NamaTim = {
            "Maulisa Dewi Mahendra",
            "Tangguh Destio Pramono",
            "Salman Fadhulloh",
            "Ardiansyah,",
            "Andika Hasbigumbi Sudewo"};

    private static String []UnivTim = {
            "Universitas Gajah Mada",
            "Universitas Gunadarma",
            "Universitas Gunadarma",
            "Universitas MH.Thamrin",
            "Universitas Gunadarma"};

    private static String []AlamatTim = {
            "Yogyakarta",
            "Jakarta",
            "Jakarta",
            "Jakarta",
            "Jakarta",
    };

    private static String []TlpTim = {
            "082134516060",
            "082110056911",
            "081295347874",
            "0895328869774",
            "085777477852",
    };

    private static int []ImgTim = {
            R.drawable.androidimg,
            R.drawable.androidimg,
            R.drawable.androidimg,
            R.drawable.ardiansyah,
            R.drawable.androidimg,
    };

   public static ArrayList<DataTim>getDataTim() {

        ArrayList<DataTim> list = new ArrayList<>();
        for (int position = 0; position < NamaTim.length; position++) {
            DataTim dataTim = new DataTim();
            dataTim.setNama(NamaTim[position]);
            dataTim.setUniv(UnivTim[position]);
            dataTim.setAlmt(AlamatTim[position]);
            dataTim.setTlp(TlpTim[position]);
            dataTim.setImgTim(ImgTim[position]);
            list.add(dataTim);
        }
        return list;
    }
}