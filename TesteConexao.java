import java.sql.Connection;

import src.br.com.showdomilhao.util.ConexaoBD;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = ConexaoBD.obterConexao()) {
            System.out.println("Conectado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
