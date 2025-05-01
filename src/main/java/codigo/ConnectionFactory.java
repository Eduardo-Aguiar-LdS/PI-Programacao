package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {
    // Criação das variáveis de conexão
    private static final Dotenv dotenv = Dotenv.load();
    private static String usuario = dotenv.get("USUARIO");
    private static String senha = dotenv.get("SENHA");
    private static String host = dotenv.get("HOST");
    private static String porta = dotenv.get("PORTA");
    private static String db = dotenv.get("DB");

    public static Connection obterConexao() {
        try {
            // Criação da url para requisição
            Connection c = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + db, usuario, senha);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
