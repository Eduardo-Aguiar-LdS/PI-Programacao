package show_milhao;

public class BancoDePerguntas {
    public static Pergunta obterPerguntaAleatoria() {
        String texto = "Qual é a capital do Brasil?";
        String[] opcoes = {
            "Rio de Janeiro",
            "Brasília", 
            "São Paulo",
            "Belo Horizonte"
        };
        String opcaoCorreta = "Brasília";
        
        return new Pergunta(texto, opcoes, opcaoCorreta);
    }
}