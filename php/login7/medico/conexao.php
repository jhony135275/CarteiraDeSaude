<?php

//Metodologia orientada a objetos
//instanciar um objeto chamado $_pdo da clase PDO
	$conexao = new PDO("mysql:host=localhost;dbname=tcc","root","");
    
    $conexao->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

//fim do bloco try


?>