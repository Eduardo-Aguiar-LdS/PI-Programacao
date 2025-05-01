import java.sql.Connection;
import src.br.com.showdomilhao.util.ConnectionFactory;

public class TesteConexao {
    public static void main(String[] args) {
        Connection conn = ConnectionFactory.obtemConexao();

        if (conn != null) {
            System.out.println("Conexão estabelecida com sucesso!");
        } else {
            System.out.println("Falha ao estabelecer a conexão.");
        }
    }
}
