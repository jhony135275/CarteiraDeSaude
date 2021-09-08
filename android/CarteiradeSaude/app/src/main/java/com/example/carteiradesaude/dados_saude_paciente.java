package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class dados_saude_paciente extends AppCompatActivity {

    TextView tv_id_paciente,tv_id, tv_cpf, tv_sexo, tv_tipo, tv_filhos, tv_qtd_cirurgias,tv_alergias;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_saude_paciente);
        tv_id_paciente      = findViewById(R.id.tv_id_paciente);
        tv_id = findViewById(R.id.tv_id);
        tv_cpf = findViewById(R.id.tv_cpf);
        tv_sexo = findViewById(R.id.tv_sexo);
        tv_tipo = findViewById(R.id.tv_tipo);
        tv_filhos= findViewById(R.id.tv_filhos);
        tv_qtd_cirurgias = findViewById(R.id.tv_qtd_cirurgias);
        tv_alergias = findViewById(R.id.tv_alergias);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "id";

                //Creating array for data
                String[] data = new String[1];
                data[0] = id;

                PutData putData = new PutData("http:/192.168.0.3/login7//lista_dados/dados_paciente.php", "POST", field, data);

                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if(result.equals("ERRO"))
                        {
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                            String [] separated = result.split(";");

                            tv_id_paciente.setText(separated[0]);

                            tv_id.setText(separated[1]);
                            tv_cpf.setText(separated[2]);
                            tv_sexo.setText(separated[3]);
                            tv_filhos.setText(separated[4]);
                            tv_qtd_cirurgias.setText(separated[5]);
                            tv_alergias.setText(separated[6]);
                            tv_tipo.setText(separated[7]);


                        }

                    }
                }
                //End Write and Read data with URL
            }
        });
       // tv_id_paciente.setText(id);
        //Toast.makeText(this, "aaa:"+id, Toast.LENGTH_SHORT).show();







    }
}