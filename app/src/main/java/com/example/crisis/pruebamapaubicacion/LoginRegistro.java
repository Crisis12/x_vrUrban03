package com.example.crisis.pruebamapaubicacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class LoginRegistro extends AppCompatActivity {

    private ImageButton btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registro);

        btnSiguiente=(ImageButton)findViewById(R.id.btnSig);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginRegistro.this,MapsActivity.class));
            }
        });
    }
}
