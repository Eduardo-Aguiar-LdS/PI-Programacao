drop database if exists show_poliedro;
create database if not exists show_poliedro;
use show_poliedro;

create table Turma (
id_turma int primary key auto_increment,
nome_turma varchar(2) not null);

-- describe Turma;

create table Aluno (
id_aluno int primary key auto_increment,
nome_aluno varchar(50) not null,
email varchar(50) not null unique,
senha varchar(50) not null,
id_turma int not null,
pontuacao int,
respostas_corretas int,
respostas_erradas int,
foreign key(id_turma) references Turma(id_turma));

-- describe Aluno;

create table Professor (
id_professor int primary key auto_increment,
nome_professor varchar(50) not null,
email varchar(50) not null unique,
senha varchar(50) not null,
pontuacao int);

-- describe Professor;

create table Pergunta (
id_pergunta int primary key auto_increment,
dificuldade varchar(7) not null,
pergunta varchar(70) not null unique,
id_professor int,
foreign key(id_professor) references Professor(id_professor));

-- describe Pergunta;

create table Resposta(
id_resposta int primary key auto_increment,
resposta_correta varchar(50) not null,
resposta_um varchar(50) not null,
resposta_dois varchar(50) not null,
resposta_tres varchar(50) not null,
id_pergunta int not null,
foreign key(id_pergunta) references Pergunta(id_pergunta));

-- describe Resposta;

-- Teste para desenvolvimento
insert into Turma (nome_turma) values
('1A'),
('1B'),
('2A'),
('2B');

insert into Aluno (nome_aluno, email, senha, id_turma, pontuacao, respostas_corretas, respostas_erradas) values
('Eduardo', 'testeA@p4ed.com', 'aluno', 1, 100, 10, 5);

insert into Professor (nome_professor, email, senha) values
('Coordenador', 'adm@coordenador.com', 'adm'),
('Eduardo', 'testeP@sistemapoliedro.com.br', 'professor');

insert into Pergunta (dificuldade, pergunta, id_professor) values
('Fácil', 'Qual é a capital do Brasil?', 1),
('Fácil', 'Quem escreveu "O Pequeno Príncipe"?', 1),
('Fácil', 'Quantos segundos há em um minuto?', 1),
('Fácil', 'Qual animal é conhecido como "o melhor amigo do homem"?', 1),
('Fácil', 'Em que continente está localizado o Brasil?', 1),
('Fácil', 'Qual é o oposto de "noite"?', 1),
('Fácil', 'Quantas patas tem uma aranha?', 1),
('Fácil', 'Qual destes é um meio de transporte aquático?', 1),
('Fácil', 'Qual é o principal ingrediente do pão?', 1),
('Fácil', 'Que cor se forma ao misturar azul com amarelo?', 1),
('Médio', 'Quem descobriu o Brasil?', 1),
('Médio', 'Em que ano o homem pisou na Lua pela primeira vez?', 1),
('Médio', 'Quem pintou a obra "A Última Ceia"?', 1),
('Médio', 'Qual é a capital do Canadá?', 1),
('Médio', 'Quantos lados tem um hexágono?', 1),
('Médio', 'Qual o nome do gás essencial para a respiração humana?', 1),
('Médio', 'Quem foi o primeiro presidente do Brasil?', 1),
('Médio', 'Que número romano representa o 50?', 1),
('Médio', 'Qual país é famoso por ter pirâmides antigas?', 1),
('Médio', 'Qual elemento tem o símbolo "Na" na tabela periódica?', 1),
('Difícil', 'Qual é o maior planeta do sistema solar?', 1),
('Difícil', 'Qual é a capital da Austrália?', 1),
('Difícil', 'Qual é o segundo maior oceano do mundo?', 1),
('Difícil', 'Quem escreveu "Dom Casmurro"?', 1),
('Difícil', 'Qual foi a primeira civilização a desenvolver a escrita?', 1),
('Difícil', 'Qual é o maior país do mundo em extensão territorial?', 1),
('Difícil', 'O que significa "ADN", em português?', 1),
('Difícil', 'Quem foi o líder da Revolução Russa de 1917?', 1),
('Difícil', 'Qual compositor é autor da "Nona Sinfonia"?', 1),
('Difícil', 'O que mede a escala Richter?', 1);

insert into Resposta (resposta_correta, resposta_um, resposta_dois, resposta_tres, id_pergunta) values
('Brasília', 'Rio de Janeiro', 'São Paulo', 'Belo Horizonte', 1),
('Antoine de Saint-Exupéry', 'Monteiro Lobato', 'J.K. Rowling', 'Machado de Assis', 2),
('60', '100', '30', '90', 3),
('Cachorro', 'Cavalo', 'Gato', 'Papagaio', 4),
('América', 'Europa', 'Ásia', 'África', 5),
('Dia', 'Amanhecer', 'Escuro', 'Tarde', 6),
('8', '6', '4', '7', 7),
('Submarino', 'Bicicleta', 'Avião', 'Carro', 8),
('Farinha de trigo', 'Açúcar', 'Sal', 'Leite', 9),
('Verde', 'Roxo', 'Laranja', 'Marrom', 10),
('Pedro Álvares Cabral', 'Dom Pedro I', 'Tiradentes', 'Vasco da Gama', 11),
('1969', '1959', '1979', '1989', 12),
('Leonardo da Vinci', 'Van Gogh', 'Picasso', 'Michelangelo', 13),
('Ottawa', 'Toronto', 'Vancouver', 'Montreal', 14),
('6', '8', '5', '7', 15),
('Oxigênio', 'Dióxido de Carbono', 'Hidrogênio', 'Nitrogênio', 16),
('Marechal Deodoro da Fonseca', 'Getúlio Vargas', 'Juscelino Kubitschek', 'Dom Pedro II', 17),
('L', 'C', 'X', 'V', 18),
('Egito', 'Índia', 'México', 'China', 19),
('Sódio', 'Nitrogênio', 'Nióbio', 'Neônio', 20),
('Júpiter', 'Terra', 'Marte', 'Saturno', 21),
('Canberra', 'Brisbane', 'Melbourne', 'Sydney', 22),
('Atlântico', 'Pacífico', 'Índico', 'Ártico', 23),
('Machado de Assis', 'Graciliano Ramos', 'Clarice Lispector', 'José de Alencar', 24),
('Mesopotâmica', 'Egípcia', 'Grega', 'Romana', 25),
('Rússia', 'Estados Unidos', 'China', 'Canadá', 26),
('Ácido Desoxirribonucleico', 'Ácido Dioxirribonucleico', 'Ácido Deoxirribonucleico', 'Ácido Dioxigenado', 27),
('Lenin', 'Gorbachev', 'Trotsky', 'Stalin', 28),
('Beethoven', 'Mozart', 'Bach', 'Chopin', 29),
('Intensidade de terremotos', 'Umidade', 'Temperatura', 'Pressão atmosférica', 30);