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
        this.ebooks.add(ebook);
        if (tipo == TipoLeitura.OBRIGATORIA) {
            this.qtdObrigatorios++;
        } else {
            this.qtdLivres++;
        }
    }

    public void remover(EBook ebook) {
        this.ebooks.remove(ebook);
    }

    public List<EBook> listar() {
        return this.ebooks;
    }

    public int contarEBooks() {
        return this.ebooks.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Estante");
        builder.append("\n  Obrigatórios: ").append(qtdObrigatorios);
        builder.append("\n  Livres: ").append(qtdLivres);
        builder.append("\n  EBooks:");

        if (ebooks.isEmpty()) {
            builder.append("\n    (vazia)");
            return builder.toString();
        }

        for (EBook ebook : ebooks) {
            builder.append("\n    - ").append(ebook.getTitulo());
        }

        return builder.toString();
    }

    public int getQtdObrigatorios() { return qtdObrigatorios; }
    public int getQtdLivres() { return qtdLivres; }
}