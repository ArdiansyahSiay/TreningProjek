package com.trening.day1;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import cz.msebera.android.httpclient.Header;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    String var_username, var_password;
    private RestProcess rest_class;
    ArrayList<HashMap<String, String>>arrayLogin = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        rest_class = new RestProcess();

        final EditText et_User = findViewById(R.id.etUser);
        final EditText et_Pas = findViewById(R.id.etPas);
        Button bt_Login = findViewById(R.id.btLogin);
        final Button bt_Lupa = findViewById(R.id.btLupaPw);

        bt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                var_username = et_User.getText().toString();
                var_password = et_Pas.getText().toString();

                if (var_username.length() == 0) {
                    Toast.makeText(LoginActivity.this, "User Name harus diisi", Toast.LENGTH_SHORT).show();
                } else if (var_password.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Pasword Harus diisi", Toast.LENGTH_SHORT).show();
//                } else if (var_password.length() == 0) {
//                    Toast.makeText(LoginActivity.this, "user name atau password salah", Toast.LENGTH_SHORT).show();

                } else {
                    loginProcess(v);
//                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(login);
                }
            }
        });
        bt_Lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnLupa = new Intent(LoginActivity.this, LupaPassword.class);
                startActivity(btnLupa);
            }
        });
    }
        private void loginProcess(final View view){

        HashMap<String,String>apiData;
        apiData = rest_class.apiSetting();
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            String base_url;

            base_url= apiData.get("str_ws_addr") + "/auth";
            params.put("var_cell_phone",var_username);
            params.put("var_password", var_password);

            client.setBasicAuth(apiData.get("str_ws_user"),apiData.get("str_ws_pass"));
            client.post(base_url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode,Header[] headers, byte[] responseBody) {
                    String resp_content ="";
                    try {
                        resp_content = new String(responseBody,"UTF-8");
                    }catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    try {
                        displayLogin(view,resp_content);
                    }catch (Throwable t){
                        Toast.makeText(LoginActivity.this,"Koneksi Gagal 1", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(LoginActivity.this, "Koneksi Gagal 2", Toast.LENGTH_SHORT).show();
                }
            });
        }
        private void displayLogin(View view,String resp_content){
        try {
            arrayLogin = rest_class.getJsonData(resp_content);
            if (arrayLogin.get(0).get("var_result").equals("1")){
                Intent main_intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(main_intent);
                finish();
            }else if (arrayLogin.get(0).get("var_result").equals("0")){
                Toast.makeText(LoginActivity.this, "Koneksi Gagal 3!",Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException e){
            Toast.makeText(LoginActivity.this,"Koneksi Gagal 4", Toast.LENGTH_SHORT).show();

            }
        }
    }