package br.edu.pucminas.biblioteca.modelo;

public abstract class Usuario {
    private String id;
    private String nome;
    private String senha;

    public Usuario(String id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public boolean autenticar(String senhaDigitada) {
        return senha != null && senha.equals(senhaDigitada);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "\n" +
                "  ID: " + id + "\n" +
                "  Nome: " + nome;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getSenha() { return senha; }
}