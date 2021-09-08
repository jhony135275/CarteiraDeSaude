package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnmedico, btnpaciente, btnTeste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnmedico = findViewById(R.id.btnmedico);
        btnpaciente= findViewById(R.id.btnpaciente);



        btnpaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent tela_login_paciente = new Intent(getApplicationContext(),Login_paciente.class);
               startActivity(tela_login_paciente);
            }
        });
        btnmedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela_login_medico = new Intent(getApplicationContext(),Login_Medico.class);
                startActivity(tela_login_medico);
            }
        });


    }
    @Override
    public void onBackPressed() {
        // não chame o super desse método
    }
}