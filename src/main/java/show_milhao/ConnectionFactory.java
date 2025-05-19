package show_milhao;

import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {
    private static final Dotenv dotenv = Dotenv.load();
    private static String usuario = dotenv.get("USUARIO");
    private static String senha = dotenv.get("SENHA");
    private static String host = dotenv.get("HOST");
    private static String porta = dotenv.get("PORTA");
    private static String db = dotenv.get("DB");

    public static Connection obterConexao() {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://" + host + ":" + porta + "/" + db, usuario, senha);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
