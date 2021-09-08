package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class meusdados_paciente extends AppCompatActivity {

    String id, senha, email, username, nome, idd;
    private TextView txtid;
    private EditText edNome, edUsarname, edEmail, edSenha;
    private Button btnAltdadosau;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meusdados_paciente);
        txtid = findViewById(R.id.txtid);
        edNome = findViewById(R.id.edNome);
        edUsarname = findViewById(R.id.edUsarname);
        edSenha = findViewById(R.id.edSenha);
        edEmail = findViewById(R.id.edEspecializacao);
        btnAltdadosau = findViewById(R.id.btnAltdadomed);


        Intent intent = getIntent();
        id = intent.getStringExtra("id2");

        txtid.setText(id);


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

                PutData putData = new PutData("http:/192.168.0.3/login7/pegadados_paciente.php", "POST", field, data);

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

                            edNome.setText(separated[0]);
                            edUsarname.setText(separated[1]);
                            edEmail.setText(separated[2]);
                            edSenha.setText(separated[3]);
                            


                            // String[] exploded= result.split(" > ");

                            // txtnome.setText(exploded[0]);

                            //  txtnome.setText(result);
                            // txtnome.setText(array[0]);


                        }

                    }
                }
                //End Write and Read data with URL
            }
        });

        btnAltdadosau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getApplicationContext(), "taa", Toast.LENGTH_SHORT).show();
                nome = String.valueOf(edNome.getText());
                email = String.valueOf(edEmail.getText());
                senha = String.valueOf(edSenha.getText());
                username = String.valueOf(edUsarname.getText());
                idd = String.valueOf(txtid.getText());


                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        String[] field = new String[5];
                        field[0] = "nome";
                        field[1] = "senha";
                        field[2] = "email";
                        field[3] = "username";
                        field[4] = "idd";


                        //Creating array for data
                        String[] data = new String[5];
                        data[0] = nome;
                        data[1] = senha;
                        data[2] = email;
                        data[3] = username;
                        data[4] = idd;


                        //Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();

                        PutData putData = new PutData("http:/192.168.0.3/login7/updatedadospaciente.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();

                                if (result.equals("Dados de saúde inválidos")) {
                                    Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_SHORT).show();

                                } else {
                                    Intent intent = new Intent(getApplicationContext(),home_paciente.class);
                                    intent.putExtra("id",idd);
                                    Toast.makeText(getApplicationContext(), "Dados de perfil alterado com sucesso!", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    finish();
                                }

//

                            }
                        }
                    }
                });


            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), home_paciente.class);
        intent.putExtra("id",id);
        startActivity(intent);

        finish();
    }
}