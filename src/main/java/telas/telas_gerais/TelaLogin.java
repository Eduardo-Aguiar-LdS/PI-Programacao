package telas.telas_gerais;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import show_milhao.Aluno;
import show_milhao.Coordenador;
import show_milhao.Professor;
import telas.componentes.botoes.ButtonUtils;
import telas.componentes.botoes.RoundedButton;
import telas.componentes.campos.RoundedTextField;
import telas.componentes.util.FontUtils;
import telas.componentes.util.IconUtils;
import telas.telas_aluno.TelaPrincipalAluno;
import telas.telas_professor.TelaPrincipalAdmin;

public class TelaLogin extends JFrame {
    private static final Dimension NOTEBOOK_SIZE = new Dimension(1366, 768);

    // Declara os campos como variáveis de instância para acesso em outros métodos
    private RoundedTextField tfEmail;
    private RoundedTextField tfSenha;

    public TelaLogin() {
        super("Show do Milhão Acadêmico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(IconUtils.getAppIcon());

        Font headerFont = FontUtils.interOrSans(32);
        Font textFont = FontUtils.interOrSans(26);
        Font fieldFont = FontUtils.interOrSans(22);

        JLabel lblEmail = new JLabel("Email");
        JLabel lblSenha = new JLabel("Senha");
        for (JLabel lbl : new JLabel[]{lblEmail, lblSenha}) {
            lbl.setFont(textFont);
        }

        tfEmail = new RoundedTextField(30);
        tfEmail.setFont(fieldFont);
        tfEmail.setPlaceholder("Inserir e-mail");

        tfSenha = new RoundedTextField(20);
        tfSenha.setFont(fieldFont);
        tfSenha.setPlaceholder("Inserir senha");

        Dimension prefFull = tfEmail.getPreferredSize();
        int fullW = prefFull.width;
        int fullH = prefFull.height;
        tfEmail.setPreferredSize(new Dimension(fullW, fullH));
        tfSenha.setPreferredSize(new Dimension(fullW, fullH));

        RoundedButton btnAcessar = new RoundedButton("Acessar");
        RoundedButton btnVoltar = new RoundedButton("Voltar");
        ButtonUtils.estilizarPadrao(btnAcessar, new Dimension(250, 50));
        ButtonUtils.estilizarPadrao(btnVoltar, new Dimension(250, 50));

        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        content.add(IconUtils.getAppIconLabel(), gbc);

        JLabel title = new JLabel("Fazer Login", SwingConstants.CENTER);
        title.setFont(headerFont);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        content.add(title, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 5, 0);
        content.add(lblEmail, gbc);
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        content.add(tfEmail, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 5, 0);
        content.add(lblSenha, gbc);
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 30, 0);
        content.add(tfSenha, gbc);

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 75, 10, 75);
        content.add(btnAcessar, gbc);
        gbc.gridy = 7;
        content.add(btnVoltar, gbc);

        btnAcessar.addActionListener(e -> realizarLogin());
        btnVoltar.addActionListener(e -> voltarTelaAnterior());

        setContentPane(content);
        pack();
        setSize(NOTEBOOK_SIZE);
        setMinimumSize(NOTEBOOK_SIZE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

        private void realizarLogin() {
        String email = tfEmail.getText();
        String senha = new String(tfSenha.getText());

        if (email.isEmpty() || email.equals("Digite seu e-mail") ||
                senha.isEmpty() || senha.equals("Digite sua senha")) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos!", "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            // Fazer verificação de aluno, professor ou coordenador
            if (email.contains("@coordenador.com")) {
                // Coordenador
                try {
                    Coordenador coordenador = new Coordenador(email, senha);
                    coordenador.fazerLogin(coordenador, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (email.contains("@sistemapoliedro.com.br")) {
                // Professor
                try {
                    Professor professor = new Professor(email, senha);
                    professor.fazerLogin(professor, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (email.contains("@p4ed.com")) {
                // Aluno
                try {
                    Aluno aluno = new Aluno(email, senha);
                    aluno.fazerLogin(aluno, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não tem cadastro", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void irTelaPrincipalAluno(Aluno aluno) throws Exception {
        this.dispose();
        aluno.atributosDB(aluno);
        new TelaPrincipalAluno(aluno).setVisible(true);
    }

    public void irTelaPrincipalAdmin(Professor professor) throws Exception {
        this.dispose();
        professor.atributosDB(professor);
        new TelaPrincipalAdmin(professor, null).setVisible(true);
    }

    public void irTelaPrincipalAdmin(Coordenador coordenador) throws Exception {
        this.dispose();
        coordenador.atributosDB(coordenador);
        new TelaPrincipalAdmin(null, coordenador).setVisible(true);
    }

    private void voltarTelaAnterior() {
        this.dispose();
        new TelaInicial().setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaLogin frame = new TelaLogin();
            frame.setVisible(true);
        });
    }
}