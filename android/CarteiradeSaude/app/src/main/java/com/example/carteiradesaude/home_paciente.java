package com.example.carteiradesaude;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class home_paciente extends AppCompatActivity {

    String id;
    private Button btndados, btnTeste, btnEnviar, btnAgendamento,btn_Consultas, btnAltdados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_paciente);

        btn_Consultas = findViewById(R.id.btn_Consultas);

        btnAltdados = findViewById(R.id.btnAltdados);





        btnAgendamento= findViewById(R.id.btnAgendamento);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        btnAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), home_agendamento.class);

                intent.putExtra("id", id);
                startActivity(intent);
                finish();


                startActivity(intent);
                finish();

            }
        });

        btn_Consultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), consulta_paciente.class);

                intent.putExtra("id", id);
                startActivity(intent);
                finish();

            }
        });

        btnAltdados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), alterar_dados_saude.class);

                intent.putExtra("id", id);
                startActivity(intent);
                finish();
                //Toast.makeText(getApplicationContext(),"G0d",Toast.LENGTH_SHORT).show();
            }
        });






    }
    public void abrir( View v)
    {
        Intent intent = new Intent(getApplicationContext(), meusdados_paciente.class);

                intent.putExtra("id2", id);
                startActivity(intent);
                finish();

        //Toast.makeText(getApplicationContext(),"ala",Toast.LENGTH_SHORT).show();


    }
    public void abrir2 ( View x)
    {
        Intent intent = new Intent(getApplicationContext(), menu.class);


        startActivity(intent);
        finish();

        //Toast.makeText(getApplicationContext(),"ala2",Toast.LENGTH_SHORT).show();


    }

    /*@Override
    public void onBackPressed() {


        /*Intent intent = new Intent(getApplicationContext(), Login_paciente.class);
        startActivity(intent);
        finish();
    }*/
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
                        Intent intent = new Intent(getApplicationContext(), Login_paciente.class);
                        startActivity(intent);
                        finish();
                    }

                })
                .setNegativeButton("Não", null)
                .show();
    }


}