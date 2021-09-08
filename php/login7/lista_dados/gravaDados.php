<?php
  // conexão do BD
  // "mysql:host=<ip do server>;dbname=<nome do database>",
  // <usuário bd>, <senha bd>
  $conexao = new PDO("mysql:host=localhost:3308;dbname=IT3_BDExterno",
     "root", "");
     
  // ativar o depurador de erros
  $conexao->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  
  // os valores enviados na URL devem ser capturados através de get.
  // para tal, o PHP gera um vetor chamado $_GET.
  $operacao = $_GET["operacao"];
  
  // criada a variável id para update/delete. 
  // Se não for passada, ela será zerada (insert)
  $id       = 0;
  if(isset($_GET["id"]))
    $id       = $_GET["id"];
 
  $nome     = $_GET["nome"];
  $idade    = $_GET["idade"];
  
  if($operacao == "I"){
     $comandoSQL = $conexao->prepare(
       "INSERT INTO cadastro(nome       , idade)" .
       "              VALUES('".$nome."',".$idade.");");
  }elseif($operacao == "U"){
     $comandoSQL = $conexao->prepare(
       "UPDATE cadastro".
       "   SET nome  = '".$nome."',".
       "       idade = ".$idade.
       " WHERE id    = ".$id); 
  }else{
     $comandoSQL = $conexao->prepare(
       "DELETE FROM cadastro WHERE id = ".$id);
  }
  $comandoSQL->execute();
?>