package com.example.carteiradesaude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import java.time.DayOfWeek;
import java.util.Calendar;

public class calendario extends AppCompatActivity {

    CalendarView calendario;

    String id_usuario;
    String id_medico;
    String especialidade, nome_medico, cfm_medico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        calendario = findViewById(R.id.calendario);

        Intent intent = getIntent();
        id_usuario = intent.getStringExtra("id_usuario");
        id_medico = intent.getStringExtra("id_medico");
        especialidade = intent.getStringExtra("especialidade");
        nome_medico = intent.getStringExtra("nome");
        cfm_medico = intent.getStringExtra("cfm");







        //String mensagem = "Usuario: "+id_usuario+ "  Especialidade: "+especialidade+ "  Medico: "+medico;

        //Toast.makeText(getApplicationContext(),mensagem,Toast.LENGTH_LONG).show();
        
        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int ano, int mes, int diaMes) {
                String dataHoje = diaMes+"/"+(mes+1)+"/"+ano;

                Calendar calendario2 = Calendar.getInstance();
                calendario2.set(ano, mes, diaMes);
               int dia_semana = calendario2.get(Calendar.DAY_OF_WEEK);



                //Toast.makeText(getApplicationContext(),dataHoje,Toast.LENGTH_SHORT).show();

              // Toast.makeText(getApplicationContext(),String.valueOf(dia_semana),Toast.LENGTH_SHORT).show();

               if(dia_semana==1)
               {

                   Toast.makeText(getApplicationContext(),"Não agendamos consultas aos domingos",Toast.LENGTH_SHORT).show();
               }

               else
               {
                   String dia_semanal = null;
                   Intent intent = new Intent(getApplicationContext(), horario.class);
                   intent.putExtra("especialidade", especialidade);
                   intent.putExtra("id_usuario",id_usuario);
                   intent.putExtra("id_medico",id_medico);
                   intent.putExtra("cfm", cfm_medico);
                   intent.putExtra("nome", nome_medico);
                   intent.putExtra("data", dataHoje);
                   //intent.putExtra("dia", dia_semana);
                   
                   if(dia_semana==2){dia_semanal="Segunda-feira";}
                   if(dia_semana==3){dia_semanal="Terca-feira";}
                   if(dia_semana==4){dia_semanal="Quarta-feira";}
                   if(dia_semana==5){dia_semanal="Quinta-feira";}
                   if(dia_semana==6){dia_semanal="Sexta-feira";}
                   if(dia_semana==7){dia_semanal="Sabado";}

                   intent.putExtra("aa",dia_semanal);
                       

                   String mensagem = "Usuario: "+id_usuario+ "  cfm: "+cfm_medico+ "  Id_medico: "+id_medico
                           +" Nome medico: "+nome_medico+ " dia: "+dia_semana;

                   //Toast.makeText(getApplicationContext(),mensagem,Toast.LENGTH_LONG).show();



                   startActivity(intent);
                   finish();
               }





            }
        });
    }
}