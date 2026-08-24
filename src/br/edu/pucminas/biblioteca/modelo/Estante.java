package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

public class Estante {
    private int qtdObrigatorios = 0;
    private int qtdLivres = 0;
    private List<EBook> ebooks = new ArrayList<>();

    public Estante() {
    }

    public void adicionar(EBook ebook, TipoLeitura tipo) {
        // TODO: implementar na Sprint 3
    }

    public void remover(EBook ebook) {
        // TODO: implementar na Sprint 3
    }

    public List<EBook> listar() {
        // TODO: implementar na Sprint 3
        return ebooks;
    }

    public int contarEBooks() {
        return ebooks.size();
    }

    public int getQtdObrigatorios() { return qtdObrigatorios; }
    public int getQtdLivres() { return qtdLivres; }
}