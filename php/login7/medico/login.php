<?php

    
    include_once("conexao.php");

    $username = $_POST['username'];
    $senha = $_POST['senha'];


   if (isset($username) && isset($senha))
   {

       
        $sql = $conexao->prepare("SELECT * FROM  medico WHERE username= '$username' AND senha = '$senha'");
        $sql->execute();
  
       if($sql->rowCount() > 0)
        {
           $line = $sql->fetch(PDO::FETCH_ASSOC);
           
            //echo("Login realizado com sucesso");
           
            echo $line['id'];
        }
        else
        {
            echo "Dados invalidos";
        }
      
   }


?>