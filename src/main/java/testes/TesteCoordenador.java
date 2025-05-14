package testes;

import java.sql.Connection;

import show_milhao.*;

public class TesteCoordenador {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.obterConexao()) {
            //Teste de validação Coordenador
            String email = "adm";
            String senha = "adm";
            Coordenador coordenador = new Coordenador(email, senha);
            coordenador.fazerLogin(coordenador);
            coordenador.atributosDB(coordenador);
            System.out.println(coordenador);
            
            //Teste de cadastrar professor

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
