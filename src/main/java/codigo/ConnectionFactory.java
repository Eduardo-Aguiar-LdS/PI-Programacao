package codigo;

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
    public static Connection obtemConexao(){
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://"+host+":"+porta+"/"+db, usuario, senha);
            // conexao = DriverManager.getConnection("jdbc:mysql://show-poliedro-poo-imt-ap10-2024.d.aivencloud.com:19862/show_poliedro", "avnadmin", "AVNS_t08ZimINytna96T8tl9");
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
