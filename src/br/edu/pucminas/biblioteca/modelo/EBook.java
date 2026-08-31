package br.edu.pucminas.biblioteca.modelo;

public class EBook {
    private String id;
    private String titulo;
    private String editora;
    private String formato;
    private String categoria;
    private int totalAdicoesNaEstante;
    private Licenca licenca;

    public EBook(String titulo, String editora, String formato, String categoria) {
        this(null, titulo, editora, formato, categoria, new Licenca());
    }

    public EBook(String titulo, String editora, String formato, String categoria, Licenca licenca) {
        this(null, titulo, editora, formato, categoria, licenca);
    }

    public EBook(String id, String titulo, String editora, String formato, String categoria, Licenca licenca) {
        this.id = id;
        this.titulo = titulo;
        this.editora = editora;
        this.formato = formato;
        this.categoria = categoria;
        this.licenca = licenca;
        this.totalAdicoesNaEstante = 0;
    }

    public boolean isElegivelParaRenovacao() {
        return this.totalAdicoesNaEstante > 0;
    }

    @Override
    public String toString() {
        return "EBook\n" +
                "  ID: " + id + "\n" +
                "  Título: " + titulo + "\n" +
                "  Editora: " + editora + "\n" +
                "  Formato: " + formato + "\n" +
                "  Categoria: " + categoria + "\n" +
                "  Total de adições na estante: " + totalAdicoesNaEstante + "\n" +
                "  Licença:\n" + indentar(licenca.toString());
    }

    private String indentar(String texto) {
        return texto.replace("\n", "\n    ");
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getEditora() { return editora; }
    public String getFormato() { return formato; }
    public String getCategoria() { return categoria; }
    public int getTotalAdicoesNaEstante() { return totalAdicoesNaEstante; }
    public Licenca getLicenca() { return licenca; }
}