package br.edu.pucminas.biblioteca.modelo;

public class Aluno extends Usuario {
    private String matricula;
    private Estante estante;

    public Aluno(String id, String nome, String senha, String matricula) {
        super(id, nome, senha);
        this.matricula = matricula;
        this.estante = new Estante();
    }

    public void adicionarEBook(EBook ebook, TipoLeitura tipo) {
        // TODO: implementar na Sprint 3
    }

    public void removerEBook(EBook ebook) {
        // TODO: implementar na Sprint 3
    }

    public void consultarEstante() {
        // TODO: implementar na Sprint 3
    }

    public String getMatricula() { return matricula; }
    public Estante getEstante() { return estante; }
}