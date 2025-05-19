package show_milhao;

import java.util.*;

public class BancoDePerguntas {
    private static List<Pergunta> todasPerguntas = new ArrayList<>();
    private static List<Pergunta> perguntasRestantes = new ArrayList<>();
    private static Random random = new Random();

    static {
        todasPerguntas.add(new Pergunta(
            "Qual é a capital do Brasil?",
            new String[]{"Rio de Janeiro", "Brasília", "São Paulo", "Belo Horizonte"},
            "Brasília"
        ));

        todasPerguntas.add(new Pergunta(
            "Quem descobriu o Brasil?",
            new String[]{"Pedro Álvares Cabral", "Dom Pedro I", "Tiradentes", "Vasco da Gama"},
            "Pedro Álvares Cabral"
        ));

        todasPerguntas.add(new Pergunta(
            "Qual é o maior planeta do sistema solar?",
            new String[]{"Terra", "Marte", "Júpiter", "Saturno"},
            "Júpiter"
        ));

        todasPerguntas.add(new Pergunta(
            "Em que ano o Corinthians foi fundado?", 
            new String[]{"1905", "1920", "1915", "1910"},
            "1910"
        ));

        perguntasRestantes.addAll(todasPerguntas);

        
    }


    public static boolean acabou() {
        return perguntasRestantes.isEmpty();
    }

    public static Pergunta obterPerguntaAleatoria() {
        if (acabou()) return null;

        int index = random.nextInt(perguntasRestantes.size());
        Pergunta sorteada = perguntasRestantes.get(index);
        perguntasRestantes.remove(index);
        return sorteada;
    }
}
