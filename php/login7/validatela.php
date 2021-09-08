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
           
            echo $line['status'];
        }
        else
        {
            echo "ERRO";
        }
      
   }


?>