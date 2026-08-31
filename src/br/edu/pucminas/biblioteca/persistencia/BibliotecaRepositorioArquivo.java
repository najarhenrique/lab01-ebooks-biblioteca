package br.edu.pucminas.biblioteca.persistencia;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Bibliotecario;
import br.edu.pucminas.biblioteca.modelo.EBook;
import br.edu.pucminas.biblioteca.modelo.Estante;
import br.edu.pucminas.biblioteca.modelo.Licenca;
import br.edu.pucminas.biblioteca.modelo.TipoLeitura;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaRepositorioArquivo {
    private static BibliotecaRepositorioArquivo instancia;

    private final Path arquivo;
    private final Map<String, Aluno> alunos = new LinkedHashMap<>();
    private final Map<String, Bibliotecario> bibliotecarios = new LinkedHashMap<>();
    private final Map<String, EBook> ebooks = new LinkedHashMap<>();
    private final Map<String, Licenca> licencas = new LinkedHashMap<>();
    private final List<RelacionamentoEstante> relacionamentos = new ArrayList<>();

    private BibliotecaRepositorioArquivo(Path arquivo) {
        this.arquivo = arquivo;
        carregarDoArquivo();
    }

    public static synchronized BibliotecaRepositorioArquivo carregar(String caminhoArquivo) {
        instancia = new BibliotecaRepositorioArquivo(Paths.get(caminhoArquivo));
        return instancia;
    }

    public static synchronized BibliotecaRepositorioArquivo getInstancia() {
        if (instancia == null) {
            throw new IllegalStateException("Repositorio ainda nao foi inicializado");
        }
        return instancia;
    }

    public synchronized Aluno cadastrarAluno(String id, String nome, String senha, String matricula) {
        Aluno aluno = new Aluno(id, nome, senha, matricula);
        alunos.put(aluno.getId(), aluno);
        salvarTudo();
        return aluno;
    }

    public synchronized Bibliotecario cadastrarBibliotecario(String id, String nome, String senha, String registroFuncional) {
        Bibliotecario bibliotecario = new Bibliotecario(id, nome, senha, registroFuncional);
        bibliotecarios.put(bibliotecario.getId(), bibliotecario);
        salvarTudo();
        return bibliotecario;
    }

    public synchronized EBook cadastrarEBook(String id, String titulo, String editora, String formato, String categoria, Licenca licenca) {
        EBook ebook = new EBook(id, titulo, editora, formato, categoria, licenca);
        ebooks.put(ebook.getId(), ebook);
        if (licenca != null) {
            licencas.put(licenca.getId(), licenca);
        }
        salvarTudo();
        return ebook;
    }

    public synchronized Licenca cadastrarLicenca(String id, int limiteAcessosSimultaneos, int acessosAtivos) {
        Licenca licenca = new Licenca(id, limiteAcessosSimultaneos, acessosAtivos);
        licencas.put(licenca.getId(), licenca);
        salvarTudo();
        return licenca;
    }

    public synchronized Aluno autenticarAluno(String nome, String senha) {
        for (Aluno aluno : alunos.values()) {
            if (aluno.getNome().equals(nome) && aluno.autenticar(senha)) {
                return aluno;
            }
        }
        return null;
    }

    public synchronized Bibliotecario autenticarBibliotecario(String nome, String senha) {
        for (Bibliotecario bibliotecario : bibliotecarios.values()) {
            if (bibliotecario.getNome().equals(nome) && bibliotecario.autenticar(senha)) {
                return bibliotecario;
            }
        }
        return null;
    }

    public synchronized EBook buscarEBookPorId(String id) {
        return ebooks.get(id);
    }

    public synchronized Aluno buscarAlunoPorId(String id) {
        return alunos.get(id);
    }

    public synchronized List<EBook> listarEBooks() {
        return new ArrayList<>(ebooks.values());
    }

    public synchronized List<Aluno> listarAlunos() {
        return new ArrayList<>(alunos.values());
    }

    public synchronized List<Bibliotecario> listarBibliotecarios() {
        return new ArrayList<>(bibliotecarios.values());
    }

    public synchronized List<Licenca> listarLicencas() {
        return new ArrayList<>(licencas.values());
    }

    public synchronized void adicionarEBookNaEstante(String alunoId, String ebookId, TipoLeitura tipo) {
        Aluno aluno = alunos.get(alunoId);
        EBook ebook = ebooks.get(ebookId);

        if (aluno == null) {
            throw new IllegalArgumentException("Aluno nao encontrado");
        }
        if (ebook == null) {
            throw new IllegalArgumentException("eBook nao encontrado");
        }

        aluno.adicionarEBook(ebook, tipo);
        atualizarRelacionamento(alunoId, ebookId, tipo);
        salvarTudo();
    }

    public synchronized void removerEBookDaEstante(String alunoId, String ebookId) {
        Aluno aluno = alunos.get(alunoId);
        EBook ebook = ebooks.get(ebookId);

        if (aluno == null) {
            throw new IllegalArgumentException("Aluno nao encontrado");
        }
        if (ebook == null) {
            throw new IllegalArgumentException("eBook nao encontrado");
        }

        aluno.removerEBook(ebook);
        relacionamentos.removeIf(rel -> rel.alunoId.equals(alunoId) && rel.ebookId.equals(ebookId));
        salvarTudo();
    }

    public synchronized List<Aluno> consultarAlunosComEBook(EBook ebook) {
        List<Aluno> encontrados = new ArrayList<>();
        if (ebook == null) {
            return encontrados;
        }

        for (Aluno aluno : alunos.values()) {
            if (aluno.getEstante().contem(ebook)) {
                encontrados.add(aluno);
            }
        }
        return encontrados;
    }

    public synchronized void salvarTudo() {
        List<String> linhas = new ArrayList<>();

        for (Aluno aluno : alunos.values()) {
            linhas.add("ALUNO;" + aluno.getId() + ";" + aluno.getNome() + ";" + aluno.getSenha() + ";" + aluno.getMatricula());
        }
        for (Bibliotecario bibliotecario : bibliotecarios.values()) {
            linhas.add("BIBLIOTECARIO;" + bibliotecario.getId() + ";" + bibliotecario.getNome() + ";" + bibliotecario.getSenha() + ";" + bibliotecario.getRegistroFuncional());
        }
        for (Licenca licenca : licencas.values()) {
            linhas.add("LICENCA;" + licenca.getId() + ";" + licenca.getLimiteAcessosSimultaneos() + ";" + licenca.getAcessosAtivos());
        }
        for (EBook ebook : ebooks.values()) {
            Licenca licenca = ebook.getLicenca();
            linhas.add("EBOOK;" + ebook.getId() + ";" + ebook.getTitulo() + ";" + ebook.getEditora() + ";" + ebook.getFormato() + ";" + ebook.getCategoria() + ";" + (licenca != null ? licenca.getId() : "") + ";" + (licenca != null ? licenca.getLimiteAcessosSimultaneos() : 0) + ";" + (licenca != null ? licenca.getAcessosAtivos() : 0));
        }
        for (RelacionamentoEstante rel : relacionamentos) {
            linhas.add("ESTANTE;" + rel.alunoId + ";" + rel.ebookId + ";" + rel.tipo);
        }

        try {
            if (arquivo.getParent() != null) {
                Files.createDirectories(arquivo.getParent());
            }
            Files.write(arquivo, linhas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel salvar o estado da biblioteca", e);
        }
    }

    private void carregarDoArquivo() {
        alunos.clear();
        bibliotecarios.clear();
        ebooks.clear();
        licencas.clear();
        relacionamentos.clear();

        if (!Files.exists(arquivo)) {
            return;
        }

        try {
            for (String linha : Files.readAllLines(arquivo, StandardCharsets.UTF_8)) {
                if (linha == null || linha.isBlank()) {
                    continue;
                }

                String[] partes = linha.split(";");
                switch (partes[0]) {
                    case "ALUNO" -> carregarAluno(partes);
                    case "BIBLIOTECARIO" -> carregarBibliotecario(partes);
                    case "LICENCA" -> carregarLicenca(partes);
                    case "EBOOK" -> carregarEBook(partes);
                    case "ESTANTE" -> carregarRelacionamento(partes);
                    default -> {
                    }
                }
            }

            aplicarRelacionamentos();
        } catch (IOException e) {
            throw new IllegalStateException("Nao foi possivel ler o arquivo de dados", e);
        }
    }

    private void carregarAluno(String[] partes) {
        if (partes.length >= 5) {
            alunos.put(partes[1], new Aluno(partes[1], partes[2], partes[3], partes[4]));
        }
    }

    private void carregarBibliotecario(String[] partes) {
        if (partes.length >= 5) {
            bibliotecarios.put(partes[1], new Bibliotecario(partes[1], partes[2], partes[3], partes[4]));
        }
    }

    private void carregarLicenca(String[] partes) {
        if (partes.length >= 4) {
            licencas.put(partes[1], new Licenca(partes[1], parseIntSeguro(partes[2], 60), parseIntSeguro(partes[3], 0)));
        } else if (partes.length == 3) {
            licencas.put(partes[1], new Licenca(null, parseIntSeguro(partes[1], 60), parseIntSeguro(partes[2], 0)));
        }
    }

    private void carregarEBook(String[] partes) {
        if (partes.length >= 8) {
            Licenca licenca = licencas.get(partes[6]);
            if (licenca == null) {
                licenca = new Licenca(partes[6], parseIntSeguro(partes[7], 60), parseIntSeguro(partes[8], 0));
                licencas.put(licenca.getId(), licenca);
            }
            ebooks.put(partes[1], new EBook(partes[1], partes[2], partes[3], partes[4], partes[5], licenca));
        } else if (partes.length == 7) {
            Licenca licenca = new Licenca(null, parseIntSeguro(partes[5], 60), parseIntSeguro(partes[6], 0));
            ebooks.put(partes[1], new EBook(partes[1], partes[2], partes[3], partes[4], partes[5], licenca));
        } else if (partes.length == 6) {
            ebooks.put(partes[1], new EBook(partes[1], partes[2], partes[3], partes[4], partes[5], new Licenca()));
        }
    }

    private void carregarRelacionamento(String[] partes) {
        if (partes.length >= 4) {
            relacionamentos.add(new RelacionamentoEstante(partes[1], partes[2], TipoLeitura.valueOf(partes[3])));
        }
    }

    private void aplicarRelacionamentos() {
        for (RelacionamentoEstante rel : relacionamentos) {
            Aluno aluno = alunos.get(rel.alunoId);
            EBook ebook = ebooks.get(rel.ebookId);
            if (aluno != null && ebook != null) {
                aluno.getEstante().adicionar(ebook, rel.tipo);
            }
        }
    }

    private void atualizarRelacionamento(String alunoId, String ebookId, TipoLeitura tipo) {
        for (RelacionamentoEstante rel : relacionamentos) {
            if (rel.alunoId.equals(alunoId) && rel.ebookId.equals(ebookId)) {
                rel.tipo = tipo;
                return;
            }
        }
        relacionamentos.add(new RelacionamentoEstante(alunoId, ebookId, tipo));
    }

    private int parseIntSeguro(String valor, int padrao) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException ex) {
            return padrao;
        }
    }

    private static class RelacionamentoEstante {
        private final String alunoId;
        private final String ebookId;
        private TipoLeitura tipo;

        private RelacionamentoEstante(String alunoId, String ebookId, TipoLeitura tipo) {
            this.alunoId = alunoId;
            this.ebookId = ebookId;
            this.tipo = tipo;
        }
    }
}
