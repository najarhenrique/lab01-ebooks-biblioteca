package pucminas.biblioteca.modelo;

public class Licenca {
    private int limiteAcessosSimultaneos;
    private int acessosAtivos;

    public Licenca() {
        this.limiteAcessosSimultaneos = 60;
        this.acessosAtivos = 0;
    }

    public Licenca(int limiteAcessosSimultaneos) {
        this.limiteAcessosSimultaneos = limiteAcessosSimultaneos;
        this.acessosAtivos = 0;
    }

    public boolean temVagaDisponivel() {
        // TODO: implementar na Sprint 3
        return false;
    }

    public void incrementarAcessos() {
        // TODO: implementar na Sprint 3
    }

    public void decrementarAcessos() {
        // TODO: implementar na Sprint 3
    }

    public int getLimiteAcessosSimultaneos() {
        return limiteAcessosSimultaneos;
    }

    public void setLimiteAcessosSimultaneos(int limiteAcessosSimultaneos) {
        this.limiteAcessosSimultaneos = limiteAcessosSimultaneos;
    }

    public int getAcessosAtivos() {
        return acessosAtivos;
    }

    public void setAcessosAtivos(int acessosAtivos) {
        this.acessosAtivos = acessosAtivos;
    }
}
