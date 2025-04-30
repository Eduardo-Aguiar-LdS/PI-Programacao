package codigo;
import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.obtemConexao()) {
            System.out.println("Conectado com sucesso!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
