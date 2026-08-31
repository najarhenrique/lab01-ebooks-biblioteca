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
        estante.adicionar(ebook, tipo);
    }

    public void removerEBook(EBook ebook) {
        estante.remover(ebook);
    }

    public void consultarEstante() {
        System.out.println("Aluno: " + getNome() + " | Matrícula: " + matricula);
        System.out.println("Total de eBooks: " + estante.contarEBooks());

        for (EBook ebook : estante.listar()) {
            System.out.println("- " + ebook.getTitulo() + " [" + estante.getTipo(ebook) + "]");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
            "  Matrícula: " + matricula + "\n" +
            "  Estante: " + estante;
    }

    public String getMatricula() { return matricula; }
    public Estante getEstante() { return estante; }
}