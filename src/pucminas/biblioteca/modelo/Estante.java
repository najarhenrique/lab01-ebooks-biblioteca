package pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estante {
    private int qtdObrigatorios;
    private int qtdLivres;
    private Map<EBook, TipoLeitura> ebooks;

    public Estante() {
        this.qtdObrigatorios = 0;
        this.qtdLivres = 0;
        this.ebooks = new HashMap<>();
    }

    public void adicionar(EBook ebook, TipoLeitura tipo) {
        // TODO: implementar na Sprint 3
    }

    public void remover(EBook ebook) {
        // TODO: implementar na Sprint 3
    }

    public List<EBook> listar() {
        // TODO: implementar na Sprint 3
        return new ArrayList<>();
    }

    public int contarEBooks() {
        return ebooks.size();
    }

    public int getQtdObrigatorios() {
        return qtdObrigatorios;
    }

    public void setQtdObrigatorios(int qtdObrigatorios) {
        this.qtdObrigatorios = qtdObrigatorios;
    }

    public int getQtdLivres() {
        return qtdLivres;
    }

    public void setQtdLivres(int qtdLivres) {
        this.qtdLivres = qtdLivres;
    }

    public Map<EBook, TipoLeitura> getEbooks() {
        return ebooks;
    }

    public void setEbooks(Map<EBook, TipoLeitura> ebooks) {
        this.ebooks = ebooks;
    }
}
