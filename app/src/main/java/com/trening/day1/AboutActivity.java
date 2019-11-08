package com.trening.day1;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toolbar;
import com.trening.day1.dirabout.AboutTim;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.trening.day1.dirabout.AboutAdapter;
import com.trening.day1.dirabout.DataTim;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private ArrayList<DataTim> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle("Tim Projek Kelompok II");

        recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        list.addAll(AboutTim.getDataTim());
        ShowRecyclerTim();

    }
    private void ShowRecyclerTim(){
        recycler.setLayoutManager(new LinearLayoutManager(this));
        AboutAdapter aboutAdapter = new AboutAdapter(list);
        recycler.setAdapter(aboutAdapter);

    }

}
