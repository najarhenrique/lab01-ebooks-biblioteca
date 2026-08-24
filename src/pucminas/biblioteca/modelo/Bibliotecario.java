package pucminas.biblioteca.modelo;

import java.util.ArrayList;
import java.util.List;

public class Bibliotecario extends Usuario {
    private String registroFuncional;

    public Bibliotecario(String id, String nome, String senha, String registroFuncional) {
        super(id, nome, senha);
        this.registroFuncional = registroFuncional;
    }

    public List<Aluno> consultarAlunosComEBook(EBook ebook) {
        // TODO: implementar na Sprint 3
        return new ArrayList<>();
    }

    public void cadastrarEBook(EBook ebook) {
        // TODO: implementar na Sprint 3
    }

    public void renovarCatalogo() {
        // TODO: implementar na Sprint 3
    }

    public String getRegistroFuncional() {
        return registroFuncional;
    }

    public void setRegistroFuncional(String registroFuncional) {
        this.registroFuncional = registroFuncional;
    }
}
