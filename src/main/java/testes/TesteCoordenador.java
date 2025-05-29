package testes;

import java.sql.Connection;
import io.github.cdimascio.dotenv.Dotenv;
import show_milhao.*;

public class TesteCoordenador {
    private static final Dotenv dotenv = Dotenv.load();
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.obterConexao()) {
            //Teste de validação Coordenador
            String email = dotenv.get("EMAIL_COORDENADOR");
            String senha = "adm";
            Coordenador coordenador = new Coordenador(email, senha);
            coordenador.fazerLogin(coordenador, null);
            coordenador.atributosDB(coordenador);
            System.out.println(coordenador);
            //Teste de cadastrar professor

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
