package com.example.carteiradesaude;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class home extends AppCompatActivity {
    Button btn_Enviar;
    Button btn_Consultas;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_Enviar = findViewById(R.id.btn_Enviar);
        btn_Consultas =  findViewById(R.id.btn_Consultas);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        btn_Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela_login_paciente = new Intent(getApplicationContext(),lista_pacientes.class);
                startActivity(tela_login_paciente);
            }
        });

        btn_Consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent tela = new Intent(getApplicationContext(),consulta_medico.class);
                tela.putExtra("id",id);
                startActivity(tela);

            }
        });

    }

    public void abrir2 (View v)
    {
        Intent intent = new Intent(getApplicationContext(), menu.class);

        startActivity(intent);

        finish();



    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Sair da conta")
                .setMessage("Deseja realmente sair da conta?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), Login_Medico.class);
                        startActivity(intent);
                        finish();
                    }

                })
                .setNegativeButton("Não", null)
                .show();
    }


}