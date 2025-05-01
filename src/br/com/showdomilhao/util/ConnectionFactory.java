package src.br.com.showdomilhao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionFactory {

    // private static final Dotenv dotenv = Dotenv.load();

    // private static String usuario = dotenv.get("USUARIO");
    // private static String senha = dotenv.get("SENHA");
    // private static String host = dotenv.get("HOST");
    // private static String porta = dotenv.get("PORTA");
    // private static String db = dotenv.get("DB");

        // private static final Dotenv dotenv = Dotenv.load();

    private static String usuario = "USUARIO";
    private static String senha = "SENHA";
    private static String host = "HOST";
    private static String porta = "PORTA";
    private static String db = "DB";
    public static Connection obtemConexao() {
        try {
            String url = "jdbc:mysql://" + host + ":" + porta + "/" + db;
            Connection c = DriverManager.getConnection(url, usuario, senha);
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; 
        }
    }
}
