package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
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

public class horario extends AppCompatActivity
{
    String id_usuario, id_medico, cfm_medico, especialidade,dataAgendamento,diaSemanaAgendamento, nome_medico;
    RadioButton radio1, radio2, radio3, radio4, radio5, radio6, radio7;
    TextView tv_Especialidade,tv_Cpm,tv_Medico,tv_Data;
    Button btn_Confirmar, btn_Voltar;
    String horario = null;

    ArrayList<String> arrayLista = new ArrayList<String>();
    String item = "";
    String mensagem = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);

        tv_Especialidade = findViewById(R.id.tv_Especialidade);
        tv_Cpm = findViewById(R.id.tv_Cpm);
        tv_Medico = findViewById(R.id.tv_Medico);
        tv_Data = findViewById(R.id.tv_Data);

        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        radio5 = findViewById(R.id.radio5);
        radio6 = findViewById(R.id.radio6);
        radio7 = findViewById(R.id.radio7);
        btn_Voltar= findViewById(R.id.btn_Voltar);

        btn_Confirmar = findViewById(R.id.btn_Confirmar);


        Intent intent = getIntent();
        especialidade = intent.getStringExtra("especialidade");
        id_usuario = intent.getStringExtra("id_usuario");
        id_medico = intent.getStringExtra("id_medico");
        cfm_medico = intent.getStringExtra("cfm");
        nome_medico = intent.getStringExtra("nome");
        dataAgendamento = intent.getStringExtra("data");
        diaSemanaAgendamento = intent.getStringExtra("aa");



        tv_Especialidade.setText("Especialidade: "+especialidade);
        tv_Medico.setText("Nome: "+nome_medico);
        tv_Cpm.setText("CRM: "+cfm_medico);
        tv_Data.setText("Data: "+dataAgendamento+ "(" +diaSemanaAgendamento+")");


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        try {
            // URL do site

            URL site = new URL("http:/192.168.0.3/login7/agenda/pega_data.php?id_medico=" + id_medico+"&data="+dataAgendamento);


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
            while ((linha = leitor.readLine()) != null) {
                decodificador.append(linha);
            }

            // agora vamos ler o JSON
            String stringJSON = decodificador.toString();

            JSONArray saidaJSON = new JSONArray(stringJSON);
            arrayLista = new ArrayList<String>();


            // adiciona as linhas de dados do RecyclerView
            for (int contador = 0; contador < saidaJSON.length(); contador++) {
                JSONObject linhaJSON = saidaJSON.getJSONObject(contador);

                String registro;

                registro = linhaJSON.getString("hora");




                arrayLista.add(registro);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int cont=0; cont < arrayLista.size();cont++) {

            item = arrayLista.get(cont);


            if (item.equals("7:00")) {radio1.setEnabled(false);}
            if (item.equals("8:00")) {radio2.setEnabled(false);}
            if (item.equals("9:00")) {radio3.setEnabled(false);}
            if (item.equals("10:00")) {radio4.setEnabled(false);}
            if (item.equals("11:00")) {radio5.setEnabled(false);}
            if (item.equals("12:00")) {radio6.setEnabled(false);}
            if (item.equals("13:00")) {radio7.setEnabled(false);}

        }








        btn_Confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radio1.isChecked()==true)
                {
                    horario = "7:00";
                }
                else if (radio2.isChecked() == true)
                {
                    horario = "8:00";
                }
                else if(radio3.isChecked()==true)
                {
                    horario = "9:00";
                }
                else if (radio4.isChecked() == true)
                {
                    horario = "10:00";
                }
                else if(radio5.isChecked()==true)
                {
                    horario = "11:00";
                }
                else if (radio6.isChecked() == true)
                {
                    horario = "12:00";
                }

                else if (radio7.isChecked() == true)
                {
                    horario = "13:00";
                }

                //Toast.makeText(getApplicationContext(),horario,Toast.LENGTH_SHORT).show();

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            String[] field = new String[5];
                            field[0] = "id_usuario";
                            field[1] = "id_medico";
                            field[2] = "data";
                            field[3] = "dia_semana";
                            field[4] = "horario";
                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = id_usuario;
                            data[1] = id_medico;
                            data[2] = dataAgendamento;
                            data[3] = diaSemanaAgendamento;
                            data[4] = horario;
                            PutData putData = new PutData("http:/192.168.0.3/login7/agenda/agenda.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Cadastro realizado com sucesso"))
                                    {
                                        Toast.makeText(getApplicationContext(),"Consulta agendada com sucesso!",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), home_agendamento.class);
                                        intent.putExtra("id",id_usuario);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }


        });

        btn_Voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), calendario.class);
                intent.putExtra("id_usuario",id_usuario);
                intent.putExtra("id_medico",id_medico);
                intent.putExtra("especialidade",especialidade);
                intent.putExtra("nome",nome_medico);
                intent.putExtra("cfm",cfm_medico);

                startActivity(intent);

                finish();

            }
        });



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), calendario.class);
        intent.putExtra("id_usuario",id_usuario);
        intent.putExtra("id_medico",id_medico);
        intent.putExtra("especialidade",especialidade);
        intent.putExtra("nome",nome_medico);
        intent.putExtra("cfm",cfm_medico);

        startActivity(intent);

        finish();
    }

}
