package com.example.crisis.pruebamapaubicacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class loginVr2 extends AppCompatActivity {

    private FrameLayout layoutUrb;
    private AutoCompleteTextView cel;
    private ImageButton siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_vr2);

        layoutUrb=(FrameLayout)findViewById(R.id.layoutUrban);

        cel=(AutoCompleteTextView)findViewById(R.id.tvcel);

        cel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                layoutUrb.setVisibility(View.GONE);
                return false;
            }
        });

        siguiente=(ImageButton)findViewById(R.id.btnSig);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String celS= cel.getText().toString();
                Intent intent;
                intent = new Intent(loginVr2.this,LoginBienvenida.class);
                intent.putExtra("celular", celS);

            }
        });
    }

}
