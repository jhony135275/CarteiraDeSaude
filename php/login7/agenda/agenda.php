<?php


    include_once("../conexao.php");

    $id_usuario = $_POST['id_usuario'];
    $id_medico = $_POST['id_medico'];
    $data = $_POST['data']; 
    $dia_semana = $_POST['dia_semana'];
    $horario = $_POST['horario'];

/*field[0] = "id_usuario";
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
                            data[4] = horario;*/

  
        
       
       $sql ="INSERT INTO agenda(data, hora ,cod_paciente, cod_medico,dia_semana) VALUES ('" . $data. "','" . $horario. "','" .$id_usuario. "','" . $id_medico . "','" . $dia_semana . "')";
        
       
        $resultado = $conexao->query($sql) or die($conexao->error);
        //$sql->execute(); 
        if($resultado)
        {
            echo "Cadastro realizado com sucesso";
        }
        else
        {
            echo "Cadastro não realizado";
        }
       
   


?>