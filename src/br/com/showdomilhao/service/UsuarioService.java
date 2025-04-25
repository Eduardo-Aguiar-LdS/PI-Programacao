package src.br.com.showdomilhao.service;
import src.br.com.showdomilhao.dao.impl.UsuarioDAO;
import src.br.com.showdomilhao.model.Usuario;

public class UsuarioService {
    private UsuarioDAO dao = new UsuarioDAO();

    public boolean autenticar(String nome, String senha) throws Exception {
        Usuario usuario = new Usuario(nome, senha);
        return dao.exist(usuario);
    }

    public void cadastrar(String nome, String senha) throws Exception {
        Usuario usuario = new Usuario(nome, senha);
        dao.inserir(usuario);
    }

// adicionar outros metodos depois
}
