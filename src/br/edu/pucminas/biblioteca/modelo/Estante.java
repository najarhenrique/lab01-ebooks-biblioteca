package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Estante {
    private int qtdObrigatorios = 0;
    private int qtdLivres = 0;
    private Map<EBook, TipoLeitura> ebooks = new LinkedHashMap<>();

    public Estante() {
    }

    public void adicionar(EBook ebook, TipoLeitura tipo) {
        if (ebook == null) {
            throw new IllegalArgumentException("eBook nao pode ser nulo");
        }

        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de leitura nao pode ser nulo");
        }

        if (ebooks.containsKey(ebook)) {
            return;
        }

        if (contarEBooks() >= 6) {
            throw new IllegalStateException("Limite de eBooks na estante foi atingido");
        }

        if (tipo == TipoLeitura.OBRIGATORIA && qtdObrigatorios >= 4) {
            throw new IllegalStateException("Limite de eBooks obrigatorios foi atingido");
        }

        if (tipo == TipoLeitura.LIVRE && qtdLivres >= 2) {
            throw new IllegalStateException("Limite de eBooks livres foi atingido");
        }

        this.ebooks.put(ebook, tipo);
        if (tipo == TipoLeitura.OBRIGATORIA) {
            this.qtdObrigatorios++;
        } else {
            this.qtdLivres++;
        }
    }

    public void remover(EBook ebook) {
        TipoLeitura tipo = this.ebooks.remove(ebook);
        if (tipo == null) {
            return;
        }

        if (tipo == TipoLeitura.OBRIGATORIA && qtdObrigatorios > 0) {
            qtdObrigatorios--;
        }

        if (tipo == TipoLeitura.LIVRE && qtdLivres > 0) {
            qtdLivres--;
        }
    }

    public List<EBook> listar() {
        return new ArrayList<>(this.ebooks.keySet());
    }

    public boolean contem(EBook ebook) {
        return this.ebooks.containsKey(ebook);
    }

    public TipoLeitura getTipo(EBook ebook) {
        return this.ebooks.get(ebook);
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

        for (Map.Entry<EBook, TipoLeitura> entry : ebooks.entrySet()) {
            builder.append("\n    - ")
                   .append(entry.getKey().getTitulo())
                   .append(" [")
                   .append(entry.getValue())
                   .append("]");
        }

        return builder.toString();
    }

    public int getQtdObrigatorios() { return qtdObrigatorios; }
    public int getQtdLivres() { return qtdLivres; }
}