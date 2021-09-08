<?php

    
    include_once("conexao.php");

    $nome = $_POST['nome'];
    $email = $_POST['email'];
    $username =$_POST['username'];
    $senha = $_POST['senha'];
    $status = $_POST['status'];
    $cep = $_POST['cep'];
    $numero = $_POST['numero'];
    $bairro = $_POST['bairro'];  
    $cidade = $_POST['cidade'];

  

   if (isset($nome) && isset($email) && isset($username) && isset($senha))
   {
         
       
       $sql ="INSERT INTO usuario(nome ,senha, username,email,status,CEP,numero,bairro,cidade) VALUES ('" . $nome. "','" .$senha. "','" . $username . "','" . $email . "','" .$status. "','". $cep ."','". $numero ."','". $bairro ."','". $cidade ."')";
       
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
