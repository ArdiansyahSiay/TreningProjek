package com.trening.day1;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class Employee extends AppCompatActivity {
    private RestProcess rest_class;
    ListView lv_employee;
    ListAdapter adapter;

    ArrayList<HashMap<String, String>> datakaryawan_arrayList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        rest_class = new RestProcess();
        lv_employee = findViewById(R.id.lv_employee);
        getDataKaryawan(null);

    }

    private void getDataKaryawan(final View view) {

        HashMap<String, String> apiData = new HashMap<String, String>();
        apiData = rest_class.apiSetting();
        AsyncHttpClient client = new AsyncHttpClient();
        String base_url;

        base_url = apiData.get("str_ws_addr")+"/employee";

        client.setBasicAuth(apiData.get("str_ws_user"), apiData.get("str_ws_pass"));
        client.post(base_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode,Header[] headers, byte[] responseBody) {
                String resp_content = "";
                try {
                    resp_content = new String(responseBody, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    displayDataKaryawan(view, resp_content);
                } catch (Throwable t) {
                    Toast.makeText(Employee.this, "Koneksi Gagal 1", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(Employee.this, "Koneksi Gagal 2", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayDataKaryawan(View view, String resp_content) {
        try {
            datakaryawan_arrayList = rest_class.getJsonData(resp_content);
            if (datakaryawan_arrayList.get(0).get("var_result").equals("1")) {
                datakaryawan_arrayList.remove(0);
                adapter = new ListAdapter(Employee.this, datakaryawan_arrayList, 1);
                lv_employee.setAdapter(adapter);
                Toast.makeText(Employee.this, "Koneksi Berhasil", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(Employee.this, "Koneksi Gagal 3", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            Toast.makeText(Employee.this, "Koneksi Gagal 4", Toast.LENGTH_SHORT).show();
        }
    }
}
