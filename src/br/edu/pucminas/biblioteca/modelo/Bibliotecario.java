package br.edu.pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

public class Bibliotecario extends Usuario {
    private String registroFuncional;

    public Bibliotecario(String id, String nome, String senha, String registroFuncional) {
        super(id, nome, senha);
        this.registroFuncional = registroFuncional;
    }

    public List<Aluno> consultarAlunosComEBook(EBook ebook) {
        // A consulta depende de um repositório de alunos ainda não integrado neste protótipo.
        return new ArrayList<>();
    }

    public void cadastrarEBook(EBook ebook) {
        if (ebook == null) {
            throw new IllegalArgumentException("eBook nao pode ser nulo");
        }
    }

    public void renovarCatalogo() {
        // TODO: integração com o catálogo persistido em arquivo será feita na próxima etapa.
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
            "  Registro funcional: " + registroFuncional;
    }

    public String getRegistroFuncional() { return registroFuncional; }
}