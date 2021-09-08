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

public class Login_paciente extends AppCompatActivity {

    private TextView tvNome, tvTitulo, tvSenha, tvLink;
    private EditText edNome, edSenha;
    private Button btnCadastrar;
    String  username, senha ;
    int id;
    boolean ehNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_paciente);

        edNome = findViewById(R.id.edNome);
        edSenha = findViewById(R.id.edSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        tvLink = findViewById(R.id.tvLink);


        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela_cadastro_paciente = new Intent(getApplicationContext(), Cadastro_Paciente.class);
                startActivity(tela_cadastro_paciente);
            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = String.valueOf(edNome.getText());
                senha = String.valueOf(edSenha.getText());


                if (!username.equals("") && !senha.equals("")) {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "senha";

                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = senha;

                            PutData putData = new PutData("http:/192.168.0.3/login7/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();

                                    try {
                                        id = (Integer.parseInt(result));
                                        ehNumero = true;

                                    } catch (NumberFormatException e) {
                                        ehNumero = false;
                                    }
                                    if (ehNumero) {

                                        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                        String[] field2 = new String[1];
                                        field2[0] = "id";

                                        //Creating array for data
                                        String[] data2 = new String[1];
                                        data2[0] = String.valueOf(id);

                                        PutData putData2 = new PutData("http:/192.168.0.3/login7/validatela.php", "POST", field2, data2);
                                        if (putData2.startPut()) {
                                            if (putData2.onComplete()) {
                                                String result2 = putData2.getResult();
                                                if (result2.equals("preencher")) {

                                                    Intent intent = new Intent(getApplicationContext(), cadastro_dados_de_saude.class);

                                                    intent.putExtra("id", result);
                                                    startActivity(intent);
                                                    finish();

                                                } else if (result2.equals("preenchido")) {
                                                    Intent intent = new Intent(getApplicationContext(), home_paciente.class);

                                                    intent.putExtra("id", result);
                                                    startActivity(intent);
                                                    finish();


                                                } else if (result2.equals("ERRO")) {
                                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        }

                                    } else if (result.equals("Dados invalidos")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                    } else {
                                        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                    }

//

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Complete os campos corretamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        finish();
    }

}