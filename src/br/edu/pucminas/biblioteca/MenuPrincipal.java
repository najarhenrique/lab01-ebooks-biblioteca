package br.edu.pucminas.biblioteca;

import br.edu.pucminas.biblioteca.modelo.Aluno;
import br.edu.pucminas.biblioteca.modelo.Bibliotecario;
import br.edu.pucminas.biblioteca.modelo.EBook;
import br.edu.pucminas.biblioteca.modelo.Licenca;
import br.edu.pucminas.biblioteca.persistencia.RegistroTxtRepositorio;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MenuPrincipal {
    private static final String ARQUIVO_DADOS = "dados/entidades.txt";

    public static void main(String[] args) {
        RegistroTxtRepositorio repositorio = new RegistroTxtRepositorio(ARQUIVO_DADOS);

        try (Scanner leitor = new Scanner(System.in)) {
            boolean continuar = true;

            while (continuar) {
                System.out.println();
                System.out.println("=== Menu Principal ===");
                System.out.println("1. Cadastrar Aluno");
                System.out.println("2. Cadastrar Bibliotecario");
                System.out.println("3. Cadastrar EBook");
                System.out.println("4. Cadastrar Licenca");
                System.out.println("5. Listar registros salvos");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opcao: ");

                int opcao = lerInteiroNoIntervalo(leitor, 1, 6);

                try {
                    switch (opcao) {
                        case 1:
                            cadastrarAluno(leitor, repositorio);
                            break;
                        case 2:
                            cadastrarBibliotecario(leitor, repositorio);
                            break;
                        case 3:
                            cadastrarEBook(leitor, repositorio);
                            break;
                        case 4:
                            cadastrarLicenca(leitor, repositorio);
                            break;
                        case 5:
                            listarRegistros(repositorio);
                            break;
                        case 6:
                            continuar = false;
                            break;
                        default:
                            System.out.println("Opcao invalida, tente novamente.");
                    }
                } catch (IllegalStateException | IllegalArgumentException ex) {
                    System.out.println("Nao foi possivel concluir a acao: " + ex.getMessage());
                }
            }
        }
    }

    private static void cadastrarAluno(Scanner leitor, RegistroTxtRepositorio repositorio) {
        String nome = lerTextoObrigatorio(leitor, "Nome: ");
        String senha = lerTextoObrigatorio(leitor, "Senha: ");
        String matricula = lerTextoObrigatorio(leitor, "Matricula: ");

        String id = gerarId("ALU");
        Aluno aluno = new Aluno(id, nome, senha, matricula);
        repositorio.salvarLinha("ALUNO;" + aluno.getId() + ";" + aluno.getNome() + ";" + aluno.getSenha() + ";" + aluno.getMatricula());
        System.out.println("Aluno cadastrado com sucesso.");
        System.out.println("ID gerado: " + aluno.getId());
    }

    private static void cadastrarBibliotecario(Scanner leitor, RegistroTxtRepositorio repositorio) {
        String nome = lerTextoObrigatorio(leitor, "Nome: ");
        String senha = lerTextoObrigatorio(leitor, "Senha: ");
        String registro = lerTextoObrigatorio(leitor, "Registro funcional: ");

        String id = gerarId("BIB");
        Bibliotecario bibliotecario = new Bibliotecario(id, nome, senha, registro);
        repositorio.salvarLinha("BIBLIOTECARIO;" + bibliotecario.getId() + ";" + bibliotecario.getNome() + ";" + bibliotecario.getSenha() + ";" + bibliotecario.getRegistroFuncional());
        System.out.println("Bibliotecario cadastrado com sucesso.");
        System.out.println("ID gerado: " + bibliotecario.getId());
    }

    private static void cadastrarEBook(Scanner leitor, RegistroTxtRepositorio repositorio) {
        String titulo = lerTextoObrigatorio(leitor, "Titulo: ");
        String editora = lerTextoObrigatorio(leitor, "Editora: ");
        String formato = lerTextoObrigatorio(leitor, "Formato: ");
        String categoria = lerTextoObrigatorio(leitor, "Categoria: ");

        String id = gerarId("EBK");
        EBook ebook = new EBook(id, titulo, editora, formato, categoria, new Licenca());
        repositorio.salvarLinha("EBOOK;" + ebook.getId() + ";" + ebook.getTitulo() + ";" + ebook.getEditora() + ";" + ebook.getFormato() + ";" + ebook.getCategoria() + ";" + ebook.getLicenca().getLimiteAcessosSimultaneos() + ";" + ebook.getLicenca().getAcessosAtivos());
        System.out.println("EBook cadastrado com sucesso.");
        System.out.println("ID gerado: " + id);
    }

    private static void cadastrarLicenca(Scanner leitor, RegistroTxtRepositorio repositorio) {
        int limite = lerInteiroPositivo(leitor, "Limite de acessos simultaneos: ");
        int ativos = lerInteiroMinimo(leitor, "Acessos ativos: ", 0);

        while (ativos > limite) {
            System.out.println("Acessos ativos nao pode ser maior que o limite.");
            ativos = lerInteiroMinimo(leitor, "Acessos ativos: ", 0);
        }

        String id = gerarId("LIC");
        Licenca licenca = new Licenca(id, limite, ativos);
        repositorio.salvarLinha("LICENCA;" + licenca.getId() + ";" + licenca.getLimiteAcessosSimultaneos() + ";" + licenca.getAcessosAtivos());
        System.out.println("Licenca cadastrada com sucesso.");
        System.out.println("ID gerado: " + id);
    }

    private static void listarRegistros(RegistroTxtRepositorio repositorio) {
        List<String> registros = repositorio.carregarLinhas();

        if (registros.isEmpty()) {
            System.out.println("Nenhum registro salvo em dados/entidades.txt.");
            return;
        }

        System.out.println("\n=== Registros salvos ===");
        for (String registro : registros) {
            System.out.println(formatarRegistro(registro));
            System.out.println("----------------------------------------");
        }
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

    private static int lerInteiroPositivo(Scanner leitor, String mensagem) {
        return lerInteiroMinimo(leitor, mensagem, 1);
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

    private static String formatarRegistro(String registro) {
        String[] partes = registro.split(";");
        if (partes.length == 0) {
            return registro;
        }

        try {
            return switch (partes[0]) {
                case "ALUNO" -> new Aluno(partes[1], partes[2], partes[3], partes[4]).toString();
                case "BIBLIOTECARIO" -> new Bibliotecario(partes[1], partes[2], partes[3], partes[4]).toString();
                case "EBOOK" -> new EBook(partes[1], partes[2], partes[3], partes[4], partes[5], new Licenca(partes[1], Integer.parseInt(partes[6]), Integer.parseInt(partes[7]))).toString();
                case "LICENCA" -> new Licenca(partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3])).toString();
                default -> registro;
            };
        } catch (RuntimeException ex) {
            return registro;
        }
    }
}