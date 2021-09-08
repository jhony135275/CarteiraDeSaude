<?php

    
    include_once("conexao.php");

    $id =  $_POST['id'];


   if (isset($id))
   {
      
        $sql = $conexao->prepare("SELECT * FROM  carteira WHERE id_paciente= '$id'");
        $sql->execute();
  
       if($sql->rowCount() > 0)
        {
           $line = $sql->fetch(PDO::FETCH_ASSOC);
           
            echo $line['id_paciente'];
            echo ";";
            echo $line['id'];
           echo ";";
            echo $line['cpf'];
           echo ";";
            echo $line['sexo'];
           echo ";";
            echo $line['qtd_filhos'];
           echo ";";
            echo $line['qtd_cirurgias'];
           echo ";";
            echo $line['alergias'];
           
           echo ";";
           echo $line['tipo_sangue'];
           
           
           
           // print_r($line);
        }
        else
        {
            echo "ERRO";
        }
      
   }


?>