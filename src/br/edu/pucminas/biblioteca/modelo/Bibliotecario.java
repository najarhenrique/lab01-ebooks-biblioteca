package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.pucminas.biblioteca.persistencia.BibliotecaRepositorioArquivo;

public class Bibliotecario extends Usuario {
    private String registroFuncional;

    public Bibliotecario(String id, String nome, String senha, String registroFuncional) {
        super(id, nome, senha);
        this.registroFuncional = registroFuncional;
    }

    public List<Aluno> consultarAlunosComEBook(EBook ebook) {
        if (ebook == null) {
            throw new IllegalArgumentException("eBook nao pode ser nulo");
        }

        try {
            return BibliotecaRepositorioArquivo.getInstancia().consultarAlunosComEBook(ebook);
        } catch (IllegalStateException ex) {
            return new ArrayList<>();
        }
    }

    public void cadastrarEBook(EBook ebook) {
        if (ebook == null) {
            throw new IllegalArgumentException("eBook nao pode ser nulo");
        }

        try {
            BibliotecaRepositorioArquivo.getInstancia().cadastrarEBook(
                    ebook.getId(),
                    ebook.getTitulo(),
                    ebook.getEditora(),
                    ebook.getFormato(),
                    ebook.getCategoria(),
                    ebook.getLicenca());
        } catch (IllegalStateException ex) {
            // Se o repositório ainda não foi inicializado, apenas valida a operação.
        }
    }


    @Override
    public String toString() {
        return super.toString() + "\n" +
            "  Registro funcional: " + registroFuncional;
    }

    public String getRegistroFuncional() { return registroFuncional; }
}