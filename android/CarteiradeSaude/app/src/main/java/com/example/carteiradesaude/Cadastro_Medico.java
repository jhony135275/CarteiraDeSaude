package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Cadastro_Medico extends AppCompatActivity {

    private TextView tvUser, tvTitulo, tvSenha;
    private EditText edUsername, edSenha, edcfm, edNome;
    private Button btnCadastrar;
    private Spinner spinnerEspecialidade;
    String user, pass, cfm , especialidade, nome;
    int posicao;
    boolean validado;


    //ArrayList<baseArrayLista2> arrayLista = new ArrayList<baseArrayLista2>();
    ArrayList<String> arrayLista = new ArrayList<String>();

    String esp[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__medico);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        tvUser = findViewById(R.id.tvUsername);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvSenha = findViewById(R.id.tvSenha);
        edUsername = findViewById(R.id.edUser);
        edSenha = findViewById(R.id.edSenha);
        edcfm = findViewById(R.id.edcfm);
        edNome = findViewById(R.id.edNome);


        spinnerEspecialidade = findViewById(R.id.spinnerEspecialidade);


        btnCadastrar = findViewById(R.id.btnCadastrar);

        try {
            // URL do site
            URL site = new URL("http:/192.168.0.3/login7/lista_especialidade.php");

            // abrir a conexão com o site
            HttpURLConnection conexao = (HttpURLConnection) site.openConnection();
            conexao.connect();

            // ler os dados do site
            InputStream entradaDados = conexao.getInputStream();
            BufferedReader leitor = new BufferedReader(new InputStreamReader(entradaDados));

            // decodificar = ler o InputStream linha a linha
            String linha;
            StringBuilder decodificador = new StringBuilder();

            // este laço lê linha a linha o InputStream
            while((linha = leitor.readLine()) != null){
                decodificador.append(linha);
            }

            // agora vamos ler o JSON
            String stringJSON = decodificador.toString();

            JSONArray saidaJSON = new JSONArray(stringJSON);
            arrayLista = new ArrayList<String>();


            arrayLista.add("Selecione sua especialidade");
            // adiciona as linhas de dados do RecyclerView
            for(int contador = 0; contador < saidaJSON.length(); contador++){
                JSONObject linhaJSON = saidaJSON.getJSONObject(contador);

                String registro;
                //baseArrayLista2 registro = new baseArrayLista2();
                registro = linhaJSON.getString("especialidade");
                //registro.especialidade  = linhaJSON.getString("especialidade");

                //esp[contador] = linhaJSON.getString("especialidade");

                arrayLista.add(registro);
            }


            // colocar o adaptador

            ArrayAdapter<String> adapitador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayLista);
            spinnerEspecialidade.setAdapter(adapitador);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user =  String.valueOf(edUsername.getText());
                pass =  String.valueOf(edSenha.getText());
                cfm = String.valueOf(edcfm.getText());
                nome = String.valueOf(edNome.getText());

                //int posicao = spinnerEspecialidade.getSelectedItemPosition();
                especialidade = String.valueOf(spinnerEspecialidade.getSelectedItem());
                //Toast.makeText(getApplicationContext(), "posicao: "+posicao+ "Estado: "+estado, Toast.LENGTH_SHORT).show();

                boolean ok = false;

                if(TextUtils.isEmpty(user)){
                    edUsername.setError("Este Campo é obrigatório");
                    edUsername.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(pass)){
                    edSenha.setError("Este Campo é obrigatório");
                    edSenha.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(cfm)){
                    edcfm.setError("Este Campo é obrigatório");
                    edcfm.requestFocus();
                    ok = false;
                }
                if(TextUtils.isEmpty(nome)){
                    edNome.setError("Este Campo é obrigatório");
                    edNome.requestFocus();
                    ok = false;
                }

                posicao = spinnerEspecialidade.getSelectedItemPosition();

                if(posicao<1)
                {
                    ((TextView)spinnerEspecialidade.getSelectedView()).setError("Selecione uma especialidade");
                    spinnerEspecialidade.requestFocus();
                    validado = false;


                }

                if(!user.equals("") && !pass.equals("")  && !cfm.equals("") && !nome.equals("")  && !especialidade.equals("Selecione sua especialidade"))
                {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "user";
                            field[1] = "pass";
                            field[2] = "cfm";
                            field[3] = "especialidade";
                            field[4] = "nome";
                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = user;
                            data[1] = pass;
                            data[2] = cfm;
                            data[3] = especialidade;
                            data[4] = nome;
                            PutData putData = new PutData("http:/192.168.0.3/login7/medico/cadastro.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Cadastro realizado com sucesso"))
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Login_Medico.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "User ou CFM já cadastrados", Toast.LENGTH_SHORT).show();
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
    }
}