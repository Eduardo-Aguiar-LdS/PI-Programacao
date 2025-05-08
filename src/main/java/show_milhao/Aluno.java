package show_milhao;

public class Aluno {
   private String nome;
   private String email;
   private String senha;
   private int pontuacao;
   private int respostas_corretas;
   private int respostas_erradas;

   public Aluno(String var1, String var2) {
      this.nome = var1;
      this.email = var2;
   }

   public Aluno(String var1, String var2, String var3) {
      this.nome = var1;
      this.email = var2;
      this.senha = var3;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String var1) {
      this.nome = var1;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String var1) {
      this.email = var1;
   }

   public String getSenha() {
      return this.senha;
   }

   public void setSenha(String var1) {
      this.senha = var1;
   }

   @Override
   public String toString() {
       return "Aluno{nome='" + nome + "', email='" + email + "'}";
   }
   
}

