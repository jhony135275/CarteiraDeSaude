-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 08-Set-2021 às 08:57
-- Versão do servidor: 10.4.18-MariaDB
-- versão do PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `tcc`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `agenda`
--

CREATE TABLE `agenda` (
  `id` int(11) NOT NULL,
  `data` varchar(20) NOT NULL,
  `hora` varchar(20) NOT NULL,
  `cod_paciente` int(11) NOT NULL,
  `cod_medico` int(11) NOT NULL,
  `dia_semana` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `agenda`
--

INSERT INTO `agenda` (`id`, `data`, `hora`, `cod_paciente`, `cod_medico`, `dia_semana`) VALUES
(33, '1/6/2021', '7:00', 117, 70, 'Terca-feira'),
(34, '14/6/2021', '7:00', 121, 71, 'Segunda-feira'),
(35, '14/6/2021', '8:00', 121, 71, 'Segunda-feira'),
(36, '14/6/2021', '13:00', 121, 72, 'Segunda-feira'),
(37, '14/6/2021', '7:00', 117, 72, 'Segunda-feira'),
(38, '15/6/2021', '7:00', 121, 72, 'Terca-feira'),
(39, '15/6/2021', '8:00', 121, 71, 'Terca-feira'),
(40, '15/6/2021', '7:00', 121, 71, 'Terca-feira'),
(41, '10/6/2021', '8:00', 121, 72, 'Quinta-feira'),
(42, '15/6/2021', '7:00', 121, 74, 'Terca-feira'),
(43, '14/6/2021', '13:00', 121, 74, 'Segunda-feira'),
(44, '14/6/2021', '7:00', 122, 79, 'Segunda-feira'),
(45, '14/6/2021', '13:00', 122, 79, 'Segunda-feira'),
(46, '14/6/2021', '7:00', 121, 74, 'Segunda-feira'),
(47, '14/6/2021', '12:00', 121, 79, 'Segunda-feira'),
(48, '14/6/2021', '12:00', 122, 74, 'Segunda-feira'),
(49, '14/6/2021', '7:00', 120, 82, 'Segunda-feira'),
(50, '15/6/2021', '7:00', 121, 86, 'Terca-feira'),
(51, '14/6/2021', '7:00', 128, 87, 'Segunda-feira'),
(52, '14/6/2021', '8:00', 121, 87, 'Segunda-feira'),
(53, '14/6/2021', '7:00', 129, 88, 'Segunda-feira'),
(54, '14/6/2021', '13:00', 129, 88, 'Segunda-feira'),
(55, '15/6/2021', '8:00', 121, 88, 'Terca-feira'),
(56, '18/6/2021', '7:00', 129, 75, 'Sexta-feira'),
(57, '18/6/2021', '7:00', 129, 88, 'Sexta-feira'),
(58, '18/6/2021', '8:00', 129, 88, 'Sexta-feira'),
(59, '18/6/2021', '7:00', 129, 115, 'Sexta-feira'),
(60, '18/6/2021', '7:00', 143, 85, 'Sexta-feira'),
(61, '18/6/2021', '8:00', 143, 116, 'Sexta-feira'),
(62, '18/6/2021', '8:00', 117, 85, 'Sexta-feira'),
(63, '18/6/2021', '8:00', 117, 74, 'Sexta-feira'),
(64, '18/6/2021', '7:00', 117, 74, 'Sexta-feira'),
(65, '18/6/2021', '9:00', 120, 74, 'Sexta-feira'),
(66, '18/6/2021', '13:00', 120, 74, 'Sexta-feira'),
(67, '18/6/2021', '7:00', 146, 117, 'Sexta-feira'),
(68, '18/6/2021', '8:00', 146, 117, 'Sexta-feira'),
(69, '18/6/2021', '7:00', 147, 118, 'Sexta-feira'),
(70, '18/6/2021', '7:00', 148, 119, 'Sexta-feira'),
(71, '18/6/2021', '8:00', 117, 119, 'Sexta-feira'),
(72, '21/6/2021', '7:00', 117, 73, 'Segunda-feira');

-- --------------------------------------------------------

--
-- Estrutura da tabela `carteira`
--

CREATE TABLE `carteira` (
  `id` int(11) NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `tipo_sangue` varchar(10) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `alergias` varchar(50) NOT NULL,
  `qtd_cirurgias` int(10) NOT NULL,
  `qtd_filhos` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `carteira`
--

INSERT INTO `carteira` (`id`, `id_paciente`, `tipo_sangue`, `sexo`, `cpf`, `alergias`, `qtd_cirurgias`, `qtd_filhos`) VALUES
(49, 126, 'a+', 'M', '10940102862', 'Picada de insetos', 2, 1),
(39, 120, 'O-', 'M', '646.466.644', 'Picada de formiga', 2, 4),
(40, 121, 'a+', 'F', '668.454.444', 'Nadaa', 85, 16),
(41, 122, 'a+', 'M', '109.401.028-62', 'Poeira', 2, 4),
(42, 123, 'b+', 'F', '446.466.367-64', 'Nada', 2, 2),
(43, 124, 'a+', 'M', '09600198802', 'Formiga', 0, 0),
(38, 118, 'a-', 'M', '245.484.444', 'Formiga', 4, 4),
(37, 117, 'B-', 'M', '109.401.028', 'Poeira', 2, 1),
(59, 148, 'AB+', 'F', '467.683.958-82', 'Poeira', 0, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `especialidade`
--

CREATE TABLE `especialidade` (
  `id` int(11) NOT NULL,
  `especialidade` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `especialidade`
--

INSERT INTO `especialidade` (`id`, `especialidade`) VALUES
(1, 'Ortopedista'),
(2, 'Dentista'),
(3, 'Pediatria'),
(4, 'Fisioterapia'),
(5, 'Otorrino'),
(6, 'Cirurgiao'),
(7, 'Anestesiologia'),
(8, 'Cancerologia'),
(9, 'Cardiologia'),
(10, 'Cirurgia geral'),
(11, 'Coloproctologia'),
(12, 'Dermatologia'),
(13, 'Oftalmologia'),
(14, 'Pediatria'),
(15, 'Ortopedia'),
(16, 'Protesista');

-- --------------------------------------------------------

--
-- Estrutura da tabela `medico`
--

CREATE TABLE `medico` (
  `id` int(11) NOT NULL,
  `nome` varchar(40) NOT NULL,
  `username` varchar(20) NOT NULL,
  `senha` varchar(10) NOT NULL,
  `especializacao` varchar(20) NOT NULL,
  `cfm` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `medico`
--

INSERT INTO `medico` (`id`, `nome`, `username`, `senha`, `especializacao`, `cfm`) VALUES
(70, 'Luiz Paulo', 'luiz', '123', 'Ortopedia', '617'),
(71, 'Matheus silva', 'ma', '123', 'Pediatria', '6755'),
(72, 'Lucas Pinheiro', 'gui', '321', 'Protesista', '123487'),
(73, 'Nissin Santos', 'nissin', 'nissin', 'Cirurgiao', '846484'),
(74, 'Luiz barreto', 'barreto', '135996', 'Ortopedista', '94640'),
(75, 'Laura Lima Aparecida', 'lal', '6181', 'Fisioterapia', '64648'),
(76, 'Otavio Pereira', 'ot7861', 'ota140', 'Otorrino', '9494848'),
(77, 'Isis Valverde', 'isi148', '65799', 'Oftalmologia', '949484'),
(78, 'Renan Calheiros', 'renan854@', 'aaee6', 'Anestesiologia', '646487'),
(79, 'Ana Paula', 'ana965', '67881', 'Dentista', '94944'),
(80, 'Lucas Basso', 'basso1', '681851', 'Dermatologia', '9494875'),
(81, 'Gazoli Dias', 'gazoli', 'gazoli', 'Cancerologia', '4276'),
(82, 'Luana Suares', 'lu3', 'lu3', 'Cardiologia', '6467'),
(83, 'Paulo De olivera', 'paulo13', 'paulo13', 'Cirurgia geral', '67548'),
(84, 'Leonardo Santos', 'leo', 'leo', 'Coloproctologia', '8454'),
(85, 'Daniel Monaro', 'monaro', 'monaro', 'Cancerologia', '4964'),
(86, 'Aparecida Dias', '123', '123', 'Dentista', '4997'),
(87, 'Matheus Gazoli', 'tt', 'tt', 'Dentista', '848'),
(88, 'Jhony Kevin', 'user2', 'user2', 'Dentista', '16737'),
(115, 'Alberto Santos', 'al2', 'al2', 'Oftalmologia', '9494'),
(119, 'Daniel Monaro', 'teste', 'teste', 'Cancerologia', '1584');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(70) NOT NULL,
  `username` varchar(12) NOT NULL,
  `email` varchar(50) NOT NULL,
  `senha` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL,
  `CEP` varchar(14) NOT NULL,
  `numero` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `username`, `email`, `senha`, `status`, `CEP`, `numero`, `bairro`, `cidade`) VALUES
(117, 'Rafael Lima', 'rafa', 'rafa12@gmail.com', '123', 'preenchido', '13446-466', '14', 'Guaianases', 'Americana'),
(118, 'Jhony Kevin', 'jhonykevin', 'testedois238@gmail.com', '123', 'preenchido', '16476-864', '866', 'Recanto', 'Limeira'),
(120, 'Sandro dias', 'sandro', 'sandro12@gmail.com', '123', 'preenchido', '16654-678', '646', 'Borracha', 'Americana'),
(121, 'Gustavo Silva', 'aaaa', 'ah2@hotmail.co', 'aaaa', 'preenchido', '65567-554', '794', 'Feliz júnior', 'Iracemápolis'),
(122, 'Matheus Silva Basso', 'mat', 'matheus1358@hotmaul.com', 'mat', 'preenchido', '15546-785', '1876', 'Teixeira', 'Americana'),
(123, 'Gustavo Silva', 'gusta', 'gustavo12@outlook.com', 'gusta', 'preenchido', '65456-767', '9464', 'Pascoal', 'Americana'),
(124, 'Monaro Gabriel', 'dani', 'dani@gmail.com', 'dani', 'preenchido', '14577-567', '13', 'Dinamarca', 'Americana'),
(126, 'Vitoria Almeida', 'vitoria', 'vitoriaalmeida@hotmail.com', 'vitoria', 'preenchido', '56633-556', '144', 'Rua do algodão', 'Jau'),
(148, 'Daniel Monaro', 'teste', 'teste124@gmail.com', 'teste', 'preenchido', '13465-321', '14', 'Tucanos', 'Sumare');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `carteira`
--
ALTER TABLE `carteira`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cpf` (`cpf`);

--
-- Índices para tabela `especialidade`
--
ALTER TABLE `especialidade`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `medico`
--
ALTER TABLE `medico`
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `cfm` (`cfm`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT de tabela `carteira`
--
ALTER TABLE `carteira`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT de tabela `especialidade`
--
ALTER TABLE `especialidade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de tabela `medico`
--
ALTER TABLE `medico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=120;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=149;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
