package br.edu.pucminas.biblioteca.modelo;

public class Licenca {
    private String id;
    private int limiteAcessosSimultaneos;
    private int acessosAtivos;

    public Licenca() {
        this.id = null;
        this.limiteAcessosSimultaneos = 60;
        this.acessosAtivos = 0;
    }

    public Licenca(int limiteAcessosSimultaneos, int acessosAtivos) {
        this.id = null;
        this.limiteAcessosSimultaneos = limiteAcessosSimultaneos;
        this.acessosAtivos = acessosAtivos;
    }

    public Licenca(String id, int limiteAcessosSimultaneos, int acessosAtivos) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Licença\n" +
            "  ID: " + id + "\n" +
            "  Limite de acessos simultâneos: " + limiteAcessosSimultaneos + "\n" +
            "  Acessos ativos: " + acessosAtivos;
    }

    public String getId() { return id; }
    public int getLimiteAcessosSimultaneos() { return limiteAcessosSimultaneos; }
    public int getAcessosAtivos() { return acessosAtivos; }
}