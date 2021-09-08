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

import java.util.InputMismatchException;

public class cadastro_dados_de_saude extends AppCompatActivity {

    private EditText edCpf, edAlergias, edCirurgias, edFilhos;
    private Button btnEnviar;
    private RadioGroup RgSexo;
    private Spinner spinnerTSangue;
    private RadioButton RbMasc, RbFem;
    String alergias, cirurgias, sangue, filhos, cpf,id,sexo;
    int flag = 1;
    int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_dados_de_saude);

        edAlergias = findViewById(R.id.edAlergias);
        edCirurgias = findViewById(R.id.edCirurgias);
        edCpf = findViewById(R.id.edCpf);
        edFilhos = findViewById(R.id.edFilhos);
        spinnerTSangue = findViewById(R.id.spinnerTSangue);
        btnEnviar = findViewById(R.id.btnEnviar);;
        RgSexo = findViewById(R.id.RgSexo);
        RbMasc = findViewById(R.id.RbMasc);
        RbFem = findViewById(R.id.RbFem);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.TipoSanguineo
                , android.R.layout.simple_spinner_item);
        spinnerTSangue.setAdapter(adapter);



        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                posicao = spinnerTSangue.getSelectedItemPosition();




                sangue = String.valueOf(spinnerTSangue.getSelectedItem());
                //Toast.makeText(getApplicationContext(), "posicao: "+posicao+ "Estado: "+estado, Toast.LENGTH_SHORT).show();

                if(posicao<1)
                {
                    ((TextView)spinnerTSangue.getSelectedView()).setError("Campo obrigatório");
                    spinnerTSangue.requestFocus();
                    flag = 0;


                }

                alergias = String.valueOf(edAlergias.getText());
                cirurgias = String.valueOf(edCirurgias.getText());
                filhos = String.valueOf(edFilhos.getText());
                cpf = String.valueOf(edCpf.getText());

                cpf = cpf.replace(".",""); //remove UM ponto
                cpf  = cpf.replace("-",""); //remove UM TRACO
                sexo = "";

                if( RbMasc.isChecked() == true ){

                    sexo = "M";
                    flag = 1;
                    //Toast.makeText(getApplicationContext(), "masc ", Toast.LENGTH_SHORT).show();
                }
                else if( RbFem.isChecked() == true ){

                    sexo = "F";
                    flag = 1;
                    //Toast.makeText(getApplicationContext(), "fem ", Toast.LENGTH_SHORT).show();
                }
                else{
                    flag = 0;
                }
                if(!isCPF(cpf))
                {
                    edCpf.setError("CPF inválido");
                    edCpf.requestFocus();
                    flag = 2;

                }

                if(!alergias.equals("") && !cirurgias.equals("") && !sangue.equals("") && !filhos.equals("") && !cpf.equals("")) {

                    if (flag == 1) {


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

                                PutData putData = new PutData("http:/192.168.0.3/login7/cadastra_dados_saude.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        String result = putData.getResult();

                                        if (result.equals("Dados de saude cadastrados com sucesso")) {

                                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(),Login_paciente.class);
                                            startActivity(intent);
                                            finish();

                                        } else {
                                            Toast.makeText(getApplicationContext(), "CPF já cadastrado", Toast.LENGTH_SHORT).show();

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
                else{
                    Toast.makeText(getApplicationContext(), "Complete os campos corretamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Mascara Cpf
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(edCpf, smf);
        edCpf.addTextChangedListener(mtw);
        //Fim da Mascara do cpf


    }
    public boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

}