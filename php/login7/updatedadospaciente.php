<?php
 
    include_once("conexao.php");

    $nome = $_POST['nome'];
    $senha = $_POST['senha'];
    $email = $_POST['email'];
    $username = $_POST['username'];
    $id =  $_POST['idd'];
    //$status = 'preenchido';
  
    
    if (isset($nome) && isset($senha) && isset($email) && isset($username))
   {
  
       
       //$senha2 = md5($senha1);
       
       $sql ="UPDATE usuario SET nome = '$nome', senha = '$senha', email = '$email',
       username = '$username' WHERE id = '$id' ";
       
       
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