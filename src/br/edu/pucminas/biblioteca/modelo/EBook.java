package br.edu.pucminas.biblioteca.modelo;

public class EBook {
    private String titulo;
    private String editora;
    private String formato;
    private String categoria;
    private int totalAdicoesNaEstante;
    private Licenca licenca;

    public EBook(String titulo, String editora, String formato, String categoria) {
        this(titulo, editora, formato, categoria, new Licenca());
    }

    public EBook(String titulo, String editora, String formato, String categoria, Licenca licenca) {
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

    public String getTitulo() { return titulo; }
    public String getEditora() { return editora; }
    public String getFormato() { return formato; }
    public String getCategoria() { return categoria; }
    public int getTotalAdicoesNaEstante() { return totalAdicoesNaEstante; }
    public Licenca getLicenca() { return licenca; }
}