package testes;

import java.sql.Connection;
import codigo.*;

public class TesteConexao {
    public static void main(String[] args) {
        // Criação de classe utilitária
        DAO dao = new DAO();
        try (Connection conn = ConnectionFactory.obterConexao()) {
            System.out.println("Conectado com sucesso!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
