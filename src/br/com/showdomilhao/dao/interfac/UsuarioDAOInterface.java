package src.br.com.showdomilhao.dao.interfac;

import src.br.com.showdomilhao.model.Usuario;
import java.sql.*;
public interface UsuarioDAOInterface {
    boolean autenticar(Usuario usuario, String email, String senha) throws SQLException;
    void inserir(Usuario usuario) throws SQLException;
    void atualizar(Usuario usuario) throws SQLException;
    void deletarPorEmail(String email) throws SQLException;
    Usuario buscarPorEmail(String email);

}
