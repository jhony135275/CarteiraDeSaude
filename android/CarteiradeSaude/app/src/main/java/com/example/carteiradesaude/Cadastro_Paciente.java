package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Cadastro_Paciente extends AppCompatActivity {

    private TextView tvUsername, tvNome, tvSenha, tvEmail;
    private EditText edNome, edSenha, edEmail, edUsername, edCidade4, edCidade3, edCidade2, edCidade, edDate;
    private Button btnCadastrar;
    String nome, username, senha , email, cep, numero, bairro, cidade, dt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__paciente);

        edNome = findViewById(R.id.edNome);
        edSenha = findViewById(R.id.edSenha);
        edEmail = findViewById(R.id.edCFM);
        edUsername = findViewById(R.id.edUser);
        edCidade4 = findViewById(R.id.edCidade4);
        edCidade3 = findViewById(R.id.edCidade3);
        edCidade2 = findViewById(R.id.edCidade2);
        edCidade = findViewById(R.id.edCidade);
        edDate = findViewById(R.id.edDate);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                nome = String.valueOf(edNome.getText());
                username = String.valueOf(edUsername.getText());
                senha = String.valueOf(edSenha.getText());
                email = String.valueOf(edEmail.getText());
                cep = String.valueOf(edCidade4.getText());
                numero = String.valueOf(edCidade3.getText());
                bairro = String.valueOf(edCidade2.getText());
                cidade = String.valueOf(edCidade.getText());
                dt = String.valueOf(edDate.getText());

                boolean ok = false;

                if(TextUtils.isEmpty(nome)){
                    edNome.setError("Este Campo é obrigatório");
                    edNome.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(senha)){
                    edSenha.setError("Este Campo é obrigatório");
                    edSenha.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(username)){
                    edUsername.setError("Este Campo é obrigatório");
                    edUsername.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(email)){
                    edEmail.setError("Este Campo é obrigatório");
                    edEmail.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(cep)){
                    edCidade4.setError("Este Campo é obrigatório");
                    edCidade4.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(numero)){
                    edCidade3.setError("Este Campo é obrigatório");
                    edCidade3.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(bairro)){
                    edCidade2.setError("Este Campo é obrigatório");
                    edCidade2.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(cidade)){
                    edCidade.setError("Este Campo é obrigatório");
                    edCidade.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(dt)){
                    edDate.setError("Este Campo é obrigatório");
                    edDate.requestFocus();
                    ok = false;
                }

                if(!nome.equals("") && !username.equals("")  && !senha.equals("")  && !email.equals("") && !cep.equals("") &&
                        !numero.equals("") && !bairro.equals("") && !cidade.equals("") )
                {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[9];
                            field[0] = "nome";
                            field[1] = "username";
                            field[2] = "senha";
                            field[3] = "email";
                            field[4] = "status";
                            field[5] = "cep";
                            field[6] = "numero";
                            field[7] = "bairro";
                            field[8] = "cidade";
                            //Creating array for data
                            String[] data = new String[9];
                            data[0] = nome;
                            data[1] = username;
                            data[2] = senha;
                            data[3] = email;
                            data[4] = "preencher";
                            data[5] = cep;
                            data[6] = numero;
                            data[7] = bairro;
                            data[8] = cidade;
                            PutData putData = new PutData("http:/192.168.0.3/login7/cadastro.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Cadastro realizado com sucesso"))
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login_paciente.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Email ou user já cadastrado", Toast.LENGTH_SHORT).show();
                                    }

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

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtw = new MaskTextWatcher(edCidade4, smf);
        edCidade4.addTextChangedListener(mtw);

        SimpleMaskFormatter smf3 = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw3 = new MaskTextWatcher(edDate, smf3);
        edDate.addTextChangedListener(mtw3);

    }
}