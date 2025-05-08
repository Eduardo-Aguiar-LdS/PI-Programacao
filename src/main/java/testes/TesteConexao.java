package testes;

import java.sql.Connection;

import show_milhao.*;

public class TesteConexao {
    public static void main(String[] args) {
        DAO dao = new DAO();
        try (Connection conn = ConnectionFactory.obterConexao()) {
            System.out.println("Conectado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}     