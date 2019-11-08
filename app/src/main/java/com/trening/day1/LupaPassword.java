package com.trening.day1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LupaPassword extends AppCompatActivity {

    String etNew, etUlang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);


        Button btnLupa = findViewById(R.id.btnresetPw);
        final EditText et_new = findViewById(R.id.et_newPas);
        final EditText et_ulang = findViewById(R.id.et_UlangPas);

        btnLupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etNew = et_new.getText().toString();
                etUlang = et_ulang.getText().toString();

                if (etNew.equals(etUlang) && etNew.length()!=0 && etUlang.length()!=0) {
                    Intent toMain = new Intent(LupaPassword.this, MainActivity.class);
                    startActivity(toMain);

                }else if (etNew.length()==0 && etUlang.length()==0){
                    Toast.makeText(LupaPassword.this, " Password Tidak Sama", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LupaPassword.this,"User dan pas tidak sama", Toast.LENGTH_SHORT).show();
                }

        }});

        }

}

