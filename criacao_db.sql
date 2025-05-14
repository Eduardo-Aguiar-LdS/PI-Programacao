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