package show_milhao;

public class Pergunta {
    private String texto;
    private String[] opcoes;
    private String opcaoCorreta;
    
    public Pergunta(String texto, String[] opcoes, String opcaoCorreta) {
        this.texto = texto;
        this.opcoes = opcoes;
        this.opcaoCorreta = opcaoCorreta;
    }
    
    public String getTexto() {
        return texto;
    }
    
    public String[] getOpcoes() {
        return opcoes;
    }
    
    public String getOpcaoCorreta() {
        return opcaoCorreta;
    }
}