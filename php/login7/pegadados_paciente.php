<?php

    
    include_once("conexao.php");

    $id =  $_POST['id'];


   if (isset($id))
   {
      
        $sql = $conexao->prepare("SELECT * FROM  usuario WHERE id= '$id'");
        $sql->execute();
  
       if($sql->rowCount() > 0)
        {
           $line = $sql->fetch(PDO::FETCH_ASSOC);
           
            echo $line['nome'];
            echo ";";
            echo $line['username'];
           echo ";";
            echo $line['email'];
           echo ";";
            echo $line['senha'];
           echo ";";
            echo $line['status'];
           
           
           
           // print_r($line);
        }
        else
        {
            echo "ERRO";
        }
      
   }


?>