package pierrydev.com.br.finan.domain.entities;

public class Usuario {

    private long idUsuario;
    private String usuario;
    private String senha;
    private int salvo;

    public Usuario(){

    }

    public Usuario(String user, String pass){
        usuario = user;
        senha = pass;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getSalvo() {
        return salvo;
    }

    public void setSalvo(int salvo) {
        this.salvo = salvo;
    }
}
