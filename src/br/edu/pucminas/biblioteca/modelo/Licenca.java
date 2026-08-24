package br.edu.pucminas.biblioteca.modelo;

public class Licenca {
    private int limiteAcessosSimultaneos = 60;
    private int acessosAtivos;

    public Licenca(int limiteAcessosSimultaneos, int acessosAtivos) {
        this.limiteAcessosSimultaneos = limiteAcessosSimultaneos;
        this.acessosAtivos = acessosAtivos;
    }

    public boolean temVagaDisponivel() {
        // TODO: implementar na Sprint 3
        return false;
    }

    public int getLimiteAcessosSimultaneos() { return limiteAcessosSimultaneos; }
    public int getAcessosAtivos() { return acessosAtivos; }
}