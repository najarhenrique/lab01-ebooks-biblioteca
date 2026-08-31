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
        if (ebook == null) {
            throw new IllegalArgumentException("eBook nao pode ser nulo");
        }

        if (estante.contarEBooks() >= 6) {
            throw new IllegalStateException("Limite de eBooks na estante foi atingido");
        }

        if (!estante.listar().contains(ebook)) {
            estante.listar().add(ebook);
        }
    }

    public void removerEBook(EBook ebook) {
        if (ebook != null) {
            estante.listar().remove(ebook);
        }
    }

    public void consultarEstante() {
        System.out.println("Aluno: " + getNome() + " | Matrícula: " + matricula);
        System.out.println("Total de eBooks: " + estante.contarEBooks());

        for (EBook ebook : estante.listar()) {
            System.out.println("- " + ebook.getTitulo());
        }
    }

    public String getMatricula() { return matricula; }
    public Estante getEstante() { return estante; }
}