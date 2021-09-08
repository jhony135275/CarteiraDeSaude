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

public class Login_Medico extends AppCompatActivity {

    private TextView tvUser, tvTitulo, tvSenha, tvLink;
    private EditText edUsername, edSenha;
    private Button btnLogin;
    String  username, senha ;
    int id;
    boolean ehNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__medico);

        tvUser = findViewById(R.id.tvUsername);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvSenha = findViewById(R.id.tvSenha);
        edUsername = findViewById(R.id.edUsername);
        edSenha = findViewById(R.id.edSenha);
        btnLogin = findViewById(R.id.btnCadastrar);
        tvLink = findViewById(R.id.tvLink);

        tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Medico.this, Cadastro_Medico.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = String.valueOf(edUsername.getText());
                senha = String.valueOf(edSenha.getText());


                if(!username.equals("") && !senha.equals(""))
                {
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

                            PutData putData = new PutData("http:/192.168.0.3/login7/medico/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();

                              try
                                    {
                                        id = (Integer.parseInt(result));
                                        ehNumero = true;

                                    }
                                    catch (NumberFormatException e)
                                    {
                                        ehNumero = false;
                                    }
                                    if(ehNumero)
                                    {

                                        //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), home.class);
                                        String id2 = String.valueOf(id);

                                        intent.putExtra("id",id2);
                                        startActivity(intent);
                                        finish();


                                    }


                                   else if(result.equals("Dados invalidos"))
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                    }


                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                                    }
//

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Complete os campos corretamente",Toast.LENGTH_SHORT).show();
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