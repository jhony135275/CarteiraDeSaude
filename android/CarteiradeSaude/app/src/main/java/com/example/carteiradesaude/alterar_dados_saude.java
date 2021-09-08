package com.example.carteiradesaude;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class alterar_dados_saude extends AppCompatActivity {

    private EditText edCpf, edAlergias, edCirurgias, edFilhos;
    private Button btn_Editar;
    private TextView tvIdPa;
    private RadioGroup RgSexo;
    private RadioButton RbMasc, RbFem;
    private Spinner spinnerTSangue;
    String id,sexo, alergias, cirurgias, sangue, filhos, cpf;
    int flag = 1;
    int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados_saude);
        edCpf = findViewById(R.id.edCpf);
        spinnerTSangue = findViewById(R.id.spinnerTSangue);
        edAlergias = findViewById(R.id.edAlergias);
        edCirurgias = findViewById(R.id.edCirurgias);
        edFilhos = findViewById(R.id.edFilhos);
        tvIdPa = findViewById(R.id.tvIdPa);
        RgSexo = findViewById(R.id.RgSexo);
        RbFem = findViewById(R.id.RbFem);
        RbMasc = findViewById(R.id.RbMasc);
        btn_Editar = findViewById(R.id.btn_Editar);
        sexo = "";

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TipoSanguineo
                , android.R.layout.simple_spinner_item);
        spinnerTSangue.setAdapter(adapter);



        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Handler handler = new Handler(Looper.getMainLooper()); // aqui
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

                PutData putData = new PutData("http:/192.168.0.3/login7/alterarDadosSaude.php", "POST", field, data);

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

                            tvIdPa.setText(separated[0]);
                            edCpf.setText(separated[1]);

                            if(separated[6].equals("A+") || separated[6].equals("a+"))
                            {
                                spinnerTSangue.setSelection(1);
                            }
                            else if(separated[6].equals("A-") || separated[6].equals("a-"))
                            {
                                spinnerTSangue.setSelection(2);
                            }
                            else if(separated[6].equals("B+") || separated[6].equals("b+"))
                            {
                                spinnerTSangue.setSelection(3);
                            }
                            else if(separated[6].equals("B-") || separated[6].equals("b-"))
                            {
                                spinnerTSangue.setSelection(4);
                            }
                            else if(separated[6].equals("AB+") || separated[6].equals("ab+"))
                            {
                                spinnerTSangue.setSelection(5);
                            }
                            else if(separated[6].equals("AB-") || separated[6].equals("ab-"))
                            {
                                spinnerTSangue.setSelection(6);
                            }
                            else if(separated[6].equals("O+") || separated[6].equals("o+"))
                            {
                                spinnerTSangue.setSelection(7);
                            }
                            else if(separated[6].equals("O-") || separated[6].equals("o-"))
                            {
                                spinnerTSangue.setSelection(8);
                            }

                            edFilhos.setText(separated[3]);
                            edCirurgias.setText(separated[4]);
                            edAlergias.setText(separated[5]);
                            sexo = separated[2];


                            //Toast.makeText(getApplicationContext(),sexo,Toast.LENGTH_SHORT).show();



                            if( sexo.equals("F") || sexo.equals("f") ){

                                RbFem.setChecked(true);
                            }
                            else{

                                RbMasc.setChecked(true);
                            }


                        }

                    }
                }
            }
        });

        btn_Editar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                posicao = spinnerTSangue.getSelectedItemPosition();

                sangue = String.valueOf(spinnerTSangue.getSelectedItem());


                alergias = String.valueOf(edAlergias.getText());
                cirurgias = String.valueOf(edCirurgias.getText());



                if(posicao<1)
                {
                    ((TextView)spinnerTSangue.getSelectedView()).setError("Campo obrigatório");
                    spinnerTSangue.requestFocus();
                    flag = 0;


                }

                filhos = String.valueOf(edFilhos.getText());
                cpf = String.valueOf(edCpf.getText());
                ;

                if( RbMasc.isChecked() == true ){

                    sexo = "M";
                    flag = 1;
                   // Toast.makeText(getApplicationContext(), "masc ", Toast.LENGTH_SHORT).show();
                }
                else if( RbFem.isChecked() == true ){

                    sexo = "F";
                    flag = 1;
                    //Toast.makeText(getApplicationContext(), "fem ", Toast.LENGTH_SHORT).show();
                }
                else{
                    flag = 0;
                }


                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {


                        @Override
                        public void run() {

                            String[] field = new String[7];
                            field[0] = "alergias";
                            field[1] = "cirurgias";
                            field[2] = "sangue";
                            field[3] = "filhos";
                            field[4] = "cpf";
                            field[5] = "id";
                            field[6] = "sexo";

                            //Creating array for data
                            String[] data = new String[7];
                            data[0] = alergias;
                            data[1] = cirurgias;
                            data[2] = sangue;
                            data[3] = filhos;
                            data[4] = cpf;
                            data[5] = id;
                            data[6] = sexo;

                            PutData putData = new PutData("http:/192.168.0.3/login7/updatedadosdesaude.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();

                                    if (result.equals("Dados de saúde inválidos")) {
                                        //Toast.makeText(getApplicationContext(), "deu errado", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), home_paciente.class);
                                        Toast.makeText(getApplicationContext(), "Dados alterados com sucesso!", Toast.LENGTH_SHORT).show();
                                        intent.putExtra("id", id);
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

        //Mascara Cpf
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(edCpf, smf);
        edCpf.addTextChangedListener(mtw);
        //Fim da Mascara do cpf

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), home_paciente.class);
        intent.putExtra("id",id);
        startActivity(intent);

        finish();
    }
}