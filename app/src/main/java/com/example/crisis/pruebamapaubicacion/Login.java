package com.example.crisis.pruebamapaubicacion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A login screen that offers login via email/password.
 */
public class Login extends AppCompatActivity {

    private EditText idEditText;
    private EditText nameEditText;
    private Button okButton;
    ProgressDialog progressDialog;
    //192.168.0.6
    //10.0.30.255

    //10.0.0.84
    // http://devgeo.dyndns.org:9090/Android/Index.php
    private String END_POINT_URL = "http://devgeo.dyndns.org:9090/Android/Index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idEditText = (EditText) findViewById(R.id.email);
        nameEditText = (EditText) findViewById(R.id.password);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere...");
        progressDialog.setCancelable(false);

        okButton = (Button) findViewById(R.id.button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEditText.getText().toString();
                String name = nameEditText.getText().toString();

                RequestParams params = new RequestParams();
                params.put("username", id);
                params.put("password", name);
                callRegisterWebservice(params);
            }
        });
    }

    private void callRegisterWebservice(RequestParams params)
    {
        progressDialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(END_POINT_URL, params, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, String content) {
                progressDialog.hide();

                try {
                    JSONObject jsonResponse = new JSONObject(content);
                    String msg = "Bienvenido " + idEditText.getText().toString();
                    if(jsonResponse.getInt("status") == 1){
                        JSONArray usersArray = jsonResponse.getJSONArray("users");
                        StringBuilder Id = new StringBuilder();

                        if(usersArray.length() > 0){
                            for(int i=0; i< 1 ; i++){
                                JSONObject eachUser = usersArray.getJSONObject(i);
                                Id.append(eachUser.getString("Id"));
                            }

                            String VID = Id.toString();

                            Intent intento = new Intent(getApplicationContext(),MapsActivity.class);
                            intento.putExtra("IdUsuario",VID);
                            startActivity(intento);
                        }
                    }else{
                        msg = jsonResponse.getString("msg");
                    }
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }catch(JSONException e){
                    Toast.makeText(getApplicationContext(), "Error al enviar su solicitud, Verifique el Json" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Throwable error, String content) {
                progressDialog.hide();

                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "No se envio la solicitud", Toast.LENGTH_LONG).show();
                }
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Algo ocurrio al final", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error: El dispositivo tal vez no este conectado a la red o el server se encuentra sin funcionar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
