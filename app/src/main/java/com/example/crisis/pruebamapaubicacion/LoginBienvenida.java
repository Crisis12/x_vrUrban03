package com.example.crisis.pruebamapaubicacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LoginBienvenida extends AppCompatActivity {

    private ImageButton btnSiguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bienvenida);
        btnSiguiente=(ImageButton)findViewById(R.id.btnSig);


        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {
            String celular = (String) extras.get("celular");
        }

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginBienvenida.this,MapsActivity.class));
            }
        });
    }
}
