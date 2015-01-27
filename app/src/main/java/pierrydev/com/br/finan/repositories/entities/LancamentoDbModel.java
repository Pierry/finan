package pierrydev.com.br.finan.repositories.entities;

public class LancamentoDbModel {

    public static String IDLANC = "idlanc";
    public static String VALOR = "valor";
    public static String DESCRICAO = "descricao";
    public static String LOCAL = "idlocal";
    public static String TIPO = "tipo";
    public static String ENVIADO = "enviado";
    public static String DATA = "data";
    public static String IDUSUARIO = "idusuario";
    public static String TOTAL_POR_LOCAL = "sum(valor) as valor";

    public static String[] colunas = new String[]{IDLANC, VALOR,
            DESCRICAO, LOCAL, TIPO, ENVIADO, DATA, IDUSUARIO };

    public static String[] colunasSomandoValorAgrupado = new String[]{IDLANC, TOTAL_POR_LOCAL,
            DESCRICAO, LOCAL, TIPO, ENVIADO, DATA, IDUSUARIO };
}
