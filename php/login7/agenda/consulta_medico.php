<?php 


  // conexao do BD
  // "mysql:host=<ip do servidor bd>;dbname=<nome do database>",
  // <usuário do bd>, <senha do bd>
  $conexao = new PDO("mysql:host=localhost;dbname=tcc","root","");
    
  // ativar o depurador de erros
  $conexao->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  
  $id  = $_GET["id_medico"];

  $comandoSQL = $conexao->query("SELECT * FROM agenda WHERE cod_medico= '$id'  ORDER BY STR_TO_DATE(agenda.data,'%d/%m/%Y') ASC ");
  
  // pulo do gato
  $arraySelect = array();
  
  // leitura do BD e inserção dos dados num array
  while($linhaBD = $comandoSQL->fetch()){
     $arraySelect[] = $linhaBD;
  }
  
  // transformar o array em JSON
  $arrayJSON = json_encode($arraySelect, 
     JSON_UNESCAPED_SLASHES || JSON_UNESCAPED_UNICODE);
     
  echo $arrayJSON;

?>