<?php

    
    include_once("conexao.php");

    $user = $_POST['user'];
    $pass = $_POST['pass'];
    
    $cfm = $_POST['cfm'];
    $especialidade = $_POST['especialidade'];
	$nome = $_POST['nome'];
  

   if (isset($user) && isset($pass) && isset($cfm) && isset($especialidade) && isset($nome))
   {
        
       
       $sql ="INSERT INTO medico(nome, username ,senha, especializacao,cfm) VALUES ('" . $nome. "','" . $user. "','" .$pass. "','" . $especialidade . "','" . $cfm . "')";
        /*$sql = $conexao->prepare("INSERT INTO usuario(nome ,senha, username,email) VALUES ('" . $nome1. "','" .$username1. "','" . $senha1 . "','" . $email1 . "')");*/
       
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
       
   }


?>