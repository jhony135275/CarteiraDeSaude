<?php
 
    include_once("conexao.php");

    $alergias = $_POST['alergias'];
    $cirurgias = $_POST['cirurgias'];
    $sangue = $_POST['sangue'];
    $filhos = $_POST['filhos'];
    $cpf = $_POST['cpf'];
    $sexo = $_POST['sexo'];
    $id =  $_POST['id'];
    //$status = 'preenchido';
  

   if (isset($alergias) && isset($cirurgias) && isset($sangue) && isset($filhos) && isset($cpf))
   {
       
        //$senha2 = md5($senha1);
       
       
       
   
        //$sql ="INSERT INTO carteira(alergias, qtd_cirurgias, tipo_sangue ,qtd_filhos, cpf,sexo,id_paciente) VALUES ('" . $alergias. "','" .$cirurgias. "','" . $sangue . "','" . $filhos . "','" .$cpf. "','" .$sexo. "','" .$id. "')";
       
       $sql ="UPDATE carteira SET alergias = '$alergias', qtd_cirurgias = '$cirurgias', tipo_sangue = '$sangue' ,qtd_filhos = '$filhos', cpf = '$cpf', sexo = '$sexo' WHERE id_paciente = '$id'";
       
       // , qtd_cirurgias = '$cirurgias', tipo_sangue = '$sangue' ,qtd_filhos = '$filhos', cpf = '$cpf'
       
       $resultado = $conexao->query($sql) or die($conexao->error);
        //$sql->execute(); 
        if($resultado)
        {
            echo "Dados de saude cadastrados com sucesso";
            //$sql2 = "UPDATE usuario SET status = '$status' WHERE id = '$id'";
            //$resultado2 = $conexao->query($sql2) or die($conexao->error);
        }
        else
        {
            echo "Dados de saúde inválidos";
        }
       
   }

?>