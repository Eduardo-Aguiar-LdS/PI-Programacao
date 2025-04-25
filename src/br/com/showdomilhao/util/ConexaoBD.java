package src.br.com.showdomilhao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static String host = "localhost";
    private static String porta = "3306";
    private static String db = "seudb";
    private static String usuario = "usuario";
    private static String senha = "1234";

    // public static Connection obterConexao() throws Exception {
    public static Connection obterConexao() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%s/%s", host, porta, db);
        return DriverManager.getConnection(url, usuario, senha);
    }
}
