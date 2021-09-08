<?php

//Metodologia orientada a objetos
try //estrutura try-catch
{
	//instanciar um objeto chamado $_pdo da clase PDO
	$conexao = new PDO("mysql:host=localhost;dbname=tcc","root","");

}//fim do bloco try
catch( PDOException $_e)
{
	var_dump( $_e); //abre ve o que esta dentro do objeto9variavel) e. O var_dump tb consegue ver conteudos de vetores 

	echo $_e->getMessage(); //o objeto $_e invoca a execução do seu metodo getMessage(), que por sua vez, retorna a mensagem do erro ocorrido
	
	//echo $_e->getCode(); //o objeto $_e invoca a execucao do seu metodo getCode. que por sua vez, retorna o codigo numero do erro ocorrido
}

?>