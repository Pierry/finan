package pierrydev.com.br.finan.domain.entities;

public class Local {

    private long idLocal;
    private String descricao;

    public Local(){

    }

    public Local(String descricao){
        this.descricao = descricao;
    }

    public long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(long idLocal) {
        this.idLocal = idLocal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
