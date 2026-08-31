package br.edu.pucminas.biblioteca;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Bibliotecario;
import br.edu.pucminas.biblioteca.modelo.EBook;
import br.edu.pucminas.biblioteca.modelo.Licenca;
import br.edu.pucminas.biblioteca.modelo.TipoLeitura;
import br.edu.pucminas.biblioteca.persistencia.BibliotecaRepositorioArquivo;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuPrincipal {
    private static final String ARQUIVO_DADOS = "dados/entidades.txt";

    public static void main(String[] args) {
        BibliotecaRepositorioArquivo repositorio = BibliotecaRepositorioArquivo.carregar(ARQUIVO_DADOS);

        try (Scanner leitor = new Scanner(System.in)) {
            boolean continuar = true;

            while (continuar) {
                System.out.println();
                System.out.println("==============================");
                System.out.println("   Sistema de Gestão de eBooks");
                System.out.println("==============================");
                System.out.println("1. Cadastrar entidade");
                System.out.println("2. Realizar login");
                System.out.println("3. Listar registros salvos");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opcao: ");

                int opcao = lerInteiroNoIntervalo(leitor, 1, 4);

                try {
                    switch (opcao) {
                        case 1 -> menuCadastro(leitor, repositorio);
                        case 2 -> menuLogin(leitor, repositorio);
                        case 3 -> listarRegistros(repositorio);
                        case 4 -> continuar = false;
                        default -> System.out.println("Opcao invalida, tente novamente.");
                    }
                } catch (IllegalStateException | IllegalArgumentException ex) {
                    System.out.println("Nao foi possivel concluir a acao: " + ex.getMessage());
                }
            }
        }
    }

    private static void menuCadastro(Scanner leitor, BibliotecaRepositorioArquivo repositorio) {
        boolean voltar = false;

        while (!voltar) {
            System.out.println();
            System.out.println("=== Cadastro ===");
            System.out.println("1. Aluno");
            System.out.println("2. Bibliotecario");
            System.out.println("3. EBook");
            System.out.println("4. Licenca");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opcao: ");

            int opcao = lerInteiroNoIntervalo(leitor, 1, 5);
            switch (opcao) {
                case 1 -> cadastrarAluno(leitor, repositorio);
                case 2 -> cadastrarBibliotecario(leitor, repositorio);
                case 3 -> cadastrarEBook(leitor, repositorio);
                case 4 -> cadastrarLicenca(leitor, repositorio);
                case 5 -> voltar = true;
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    private static void menuLogin(Scanner leitor, BibliotecaRepositorioArquivo repositorio) {
        System.out.println();
        System.out.println("=== Login ===");
        System.out.println("1. Aluno");
        System.out.println("2. Bibliotecario");
        System.out.print("Tipo de usuario: ");

        int tipoUsuario = lerInteiroNoIntervalo(leitor, 1, 2);
        String nome = lerTextoObrigatorio(leitor, "Nome: ");
        String senha = lerTextoObrigatorio(leitor, "Senha: ");

        if (tipoUsuario == 1) {
            Aluno aluno = repositorio.autenticarAluno(nome, senha);
            if (aluno == null) {
                System.out.println("Credenciais invalidas.");
                return;
            }
            System.out.println("Login realizado com sucesso: " + aluno.getNome());
            menuAluno(leitor, repositorio, aluno);
        } else {
            Bibliotecario bibliotecario = repositorio.autenticarBibliotecario(nome, senha);
            if (bibliotecario == null) {
                System.out.println("Credenciais invalidas.");
                return;
            }
            System.out.println("Login realizado com sucesso: " + bibliotecario.getNome());
            menuBibliotecario(leitor, repositorio, bibliotecario);
        }
    }

    private static void menuAluno(Scanner leitor, BibliotecaRepositorioArquivo repositorio, Aluno aluno) {
        boolean logout = false;

        while (!logout) {
            System.out.println();
            System.out.println("=== Menu do Aluno ===");
            System.out.println("1. Adicionar eBook à estante");
            System.out.println("2. Remover eBook da estante");
            System.out.println("3. Consultar estante pessoal");
            System.out.println("4. Listar eBooks cadastrados");
            System.out.println("5. Sair da conta");
            System.out.print("Escolha uma opcao: ");

            int opcao = lerInteiroNoIntervalo(leitor, 1, 5);
            try {
                switch (opcao) {
                    case 1 -> adicionarEBookNaEstante(leitor, repositorio, aluno);
                    case 2 -> removerEBookDaEstante(leitor, repositorio, aluno);
                    case 3 -> aluno.consultarEstante();
                    case 4 -> listarEBooks(repositorio);
                    case 5 -> logout = true;
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (IllegalStateException | IllegalArgumentException ex) {
                System.out.println("Nao foi possivel concluir a acao: " + ex.getMessage());
            }
        }
    }

    private static void menuBibliotecario(Scanner leitor, BibliotecaRepositorioArquivo repositorio, Bibliotecario bibliotecario) {
        boolean logout = false;

        while (!logout) {
            System.out.println();
            System.out.println("=== Menu do Bibliotecario ===");
            System.out.println("1. Cadastrar eBook");
            System.out.println("2. Consultar alunos com um eBook");
            System.out.println("3. Listar eBooks cadastrados");
            System.out.println("4. Sair da conta");
            System.out.print("Escolha uma opcao: ");

            int opcao = lerInteiroNoIntervalo(leitor, 1, 4);
            try {
                switch (opcao) {
                    case 1 -> cadastrarEBook(leitor, repositorio);
                    case 2 -> consultarAlunosComEBook(leitor, repositorio, bibliotecario);
                    case 3 -> listarEBooks(repositorio);
                    case 4 -> logout = true;
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (IllegalStateException | IllegalArgumentException ex) {
                System.out.println("Nao foi possivel concluir a acao: " + ex.getMessage());
            }
        }
    }

    private static void cadastrarAluno(Scanner leitor, BibliotecaRepositorioArquivo repositorio) {
        String nome = lerTextoObrigatorio(leitor, "Nome: ");
        String senha = lerTextoObrigatorio(leitor, "Senha: ");
        String matricula = lerTextoObrigatorio(leitor, "Matricula: ");

        String id = gerarId("ALU");
        Aluno aluno = repositorio.cadastrarAluno(id, nome, senha, matricula);
        System.out.println("Aluno cadastrado com sucesso.");
        System.out.println(aluno);
    }

    private static void cadastrarBibliotecario(Scanner leitor, BibliotecaRepositorioArquivo repositorio) {
        String nome = lerTextoObrigatorio(leitor, "Nome: ");
        String senha = lerTextoObrigatorio(leitor, "Senha: ");
        String registro = lerTextoObrigatorio(leitor, "Registro funcional: ");

        String id = gerarId("BIB");
        Bibliotecario bibliotecario = repositorio.cadastrarBibliotecario(id, nome, senha, registro);
        System.out.println("Bibliotecario cadastrado com sucesso.");
        System.out.println(bibliotecario);
    }

    private static void cadastrarEBook(Scanner leitor, BibliotecaRepositorioArquivo repositorio) {
        String titulo = lerTextoObrigatorio(leitor, "Titulo: ");
        String editora = lerTextoObrigatorio(leitor, "Editora: ");
        String formato = lerTextoObrigatorio(leitor, "Formato: ");
        String categoria = lerTextoObrigatorio(leitor, "Categoria: ");
        int limite = lerInteiroNoIntervalo(leitor, 1, 60);
        int ativos = lerInteiroMinimo(leitor, "Acessos ativos: ", 0);

        while (ativos > limite) {
            System.out.println("Acessos ativos nao pode ser maior que o limite.");
            ativos = lerInteiroMinimo(leitor, "Acessos ativos: ", 0);
        }

        String licencaId = gerarId("LIC");
        String ebookId = gerarId("EBK");
        Licenca licenca = repositorio.cadastrarLicenca(licencaId, limite, ativos);
        EBook ebook = repositorio.cadastrarEBook(ebookId, titulo, editora, formato, categoria, licenca);
        System.out.println("EBook cadastrado com sucesso.");
        System.out.println(ebook);
    }

    private static void cadastrarLicenca(Scanner leitor, BibliotecaRepositorioArquivo repositorio) {
        int limite = lerInteiroNoIntervalo(leitor, 1, 60);
        int ativos = lerInteiroMinimo(leitor, "Acessos ativos: ", 0);

        while (ativos > limite) {
            System.out.println("Acessos ativos nao pode ser maior que o limite.");
            ativos = lerInteiroMinimo(leitor, "Acessos ativos: ", 0);
        }

        String id = gerarId("LIC");
        Licenca licenca = repositorio.cadastrarLicenca(id, limite, ativos);
        System.out.println("Licenca cadastrada com sucesso.");
        System.out.println(licenca);
    }

    private static void adicionarEBookNaEstante(Scanner leitor, BibliotecaRepositorioArquivo repositorio, Aluno aluno) {
        listarEBooks(repositorio);
        EBook ebook = selecionarEBook(leitor, repositorio);
        TipoLeitura tipoLeitura = selecionarTipoLeitura(leitor);
        repositorio.adicionarEBookNaEstante(aluno.getId(), ebook.getId(), tipoLeitura);
        System.out.println("eBook adicionado à estante com sucesso.");
    }

    private static void removerEBookDaEstante(Scanner leitor, BibliotecaRepositorioArquivo repositorio, Aluno aluno) {
        if (aluno.getEstante().contarEBooks() == 0) {
            System.out.println("A estante esta vazia.");
            return;
        }

        aluno.consultarEstante();
        EBook ebook = selecionarEBookDaEstante(leitor, aluno);
        repositorio.removerEBookDaEstante(aluno.getId(), ebook.getId());
        System.out.println("eBook removido da estante com sucesso.");
    }

    private static void consultarAlunosComEBook(Scanner leitor, BibliotecaRepositorioArquivo repositorio, Bibliotecario bibliotecario) {
        listarEBooks(repositorio);
        EBook ebook = selecionarEBook(leitor, repositorio);
        List<Aluno> alunos = bibliotecario.consultarAlunosComEBook(ebook);

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno possui este eBook.");
            return;
        }

        System.out.println("\n=== Alunos com o eBook ===");
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
            System.out.println("----------------------------------------");
        }
    }

    private static void listarEBooks(BibliotecaRepositorioArquivo repositorio) {
        List<EBook> ebooks = repositorio.listarEBooks();
        if (ebooks.isEmpty()) {
            System.out.println("Nenhum eBook cadastrado.");
            return;
        }

        System.out.println("\n=== EBooks cadastrados ===");
        for (EBook ebook : ebooks) {
            System.out.println(ebook);
            System.out.println("----------------------------------------");
        }
    }

    private static void listarRegistros(BibliotecaRepositorioArquivo repositorio) {
        System.out.println();
        System.out.println("=== Alunos ===");
        imprimirLista(repositorio.listarAlunos());
        System.out.println("=== Bibliotecarios ===");
        imprimirLista(repositorio.listarBibliotecarios());
        System.out.println("=== Licencas ===");
        imprimirLista(repositorio.listarLicencas());
        System.out.println("=== EBooks ===");
        imprimirLista(repositorio.listarEBooks());
    }

    private static void imprimirLista(List<?> lista) {
        if (lista.isEmpty()) {
            System.out.println("  (vazio)");
            return;
        }

        for (Object item : lista) {
            System.out.println(item);
            System.out.println("----------------------------------------");
        }
    }

    private static EBook selecionarEBook(Scanner leitor, BibliotecaRepositorioArquivo repositorio) {
        String id = lerTextoObrigatorio(leitor, "Informe o ID do eBook: ");
        EBook ebook = repositorio.buscarEBookPorId(id);
        if (ebook == null) {
            throw new IllegalArgumentException("eBook nao encontrado");
        }
        return ebook;
    }

    private static EBook selecionarEBookDaEstante(Scanner leitor, Aluno aluno) {
        String id = lerTextoObrigatorio(leitor, "Informe o ID do eBook para remocao: ");
        for (EBook ebook : aluno.getEstante().listar()) {
            if (ebook.getId() != null && ebook.getId().equals(id)) {
                return ebook;
            }
        }
        throw new IllegalArgumentException("eBook nao encontrado na estante do aluno");
    }

    private static TipoLeitura selecionarTipoLeitura(Scanner leitor) {
        System.out.println("Tipo de leitura:");
        System.out.println("1. Obrigatoria");
        System.out.println("2. Livre");
        System.out.print("Escolha uma opcao: ");

        int opcao = lerInteiroNoIntervalo(leitor, 1, 2);
        return opcao == 1 ? TipoLeitura.OBRIGATORIA : TipoLeitura.LIVRE;
    }

    private static String gerarId(String prefixo) {
        return prefixo + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private static String lerTextoObrigatorio(Scanner leitor, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = leitor.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("Campo obrigatorio. Tente novamente.");
        }
    }

    private static int lerInteiroNoIntervalo(Scanner leitor, int minimo, int maximo) {
        while (true) {
            String entrada = leitor.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.print("Opcao invalida. Digite um numero entre " + minimo + " e " + maximo + ": ");
        }
    }

    private static int lerInteiroMinimo(Scanner leitor, String mensagem, int minimo) {
        while (true) {
            System.out.print(mensagem);
            String entrada = leitor.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= minimo) {
                    return valor;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Digite um numero valido.");
        }
    }
}
