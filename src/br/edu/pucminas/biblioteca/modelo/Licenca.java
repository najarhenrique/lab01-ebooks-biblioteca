package br.edu.pucminas.biblioteca.modelo;

public class Licenca {
    private int limiteAcessosSimultaneos;
    private int acessosAtivos;

    public Licenca() {
        this.limiteAcessosSimultaneos = 60;
        this.acessosAtivos = 0;
    }

    public Licenca(int limiteAcessosSimultaneos, int acessosAtivos) {
        this.limiteAcessosSimultaneos = limiteAcessosSimultaneos;
        this.acessosAtivos = acessosAtivos;
    }

    public boolean temVagaDisponivel() {
        return acessosAtivos < limiteAcessosSimultaneos;
    }

    public void registrarAcesso() {
        if (!temVagaDisponivel()) {
            throw new IllegalStateException("Numero maximo de acessos simultaneos atingido");
        }
        acessosAtivos++;
    }

    public int getLimiteAcessosSimultaneos() { return limiteAcessosSimultaneos; }
    public int getAcessosAtivos() { return acessosAtivos; }
}