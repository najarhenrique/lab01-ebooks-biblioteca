package pucminas.biblioteca.modelo;

public class EBook {
    private String titulo;
    private String editora;
    private String formato;
    private String categoria;
    private int totalAdicoesNaEstante;

    public EBook(String titulo, String editora, String formato, String categoria) {
        this.titulo = titulo;
        this.editora = editora;
        this.formato = formato;
        this.categoria = categoria;
        this.totalAdicoesNaEstante = 0;
    }

    public boolean isElegivelParaRenovacao() {
        // TODO: implementar na Sprint 3
        return false;
    }

    public void incrementarAdiceoes() {
        // TODO: implementar na Sprint 3
    }

    public void decrementarAdiceoes() {
        // TODO: implementar na Sprint 3
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getTotalAdicoesNaEstante() {
        return totalAdicoesNaEstante;
    }

    public void setTotalAdicoesNaEstante(int totalAdicoesNaEstante) {
        this.totalAdicoesNaEstante = totalAdicoesNaEstante;
    }
}
