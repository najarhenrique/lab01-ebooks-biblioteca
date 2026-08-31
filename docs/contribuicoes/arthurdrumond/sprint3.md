# Sprint 3

---

**Contribuição:** Implementei os métodos relacionados ao `Aluno`, `Bibliotecario` e `Usuario`, dando início ao protótipo funcional do sistema com foco na regra de negócio principal da estante do aluno e na autenticação do usuário. Também implementei a persistência em arquivo texto simples e o menu principal em linha de comando, permitindo cadastrar entidades e salvar os registros em `dados/entidades.txt`.

**Decisões:**
- Em `Usuario.autenticar()`, validei a senha informada comparando diretamente com a senha cadastrada no objeto, mantendo a lógica simples e compatível com o fluxo de login em linha de comando.
- Em `Aluno.adicionarEBook()`, apliquei a regra de limite de 6 eBooks na estante e lancei `IllegalStateException` quando o limite é atingido, permitindo tratamento amigável na interface.
- Em `Aluno.consultarEstante()`, mantive a saída textual simples para facilitar a verificação manual do resultado durante os testes da sprint.
- Em `Bibliotecario`, preservei os métodos da classe alinhados ao protótipo da sprint, mantendo `consultarAlunosComEBook()` como ponto de integração futura com repositório/persistência, `cadastrarEBook()` com validação básica de nulidade e `renovarCatalogo()` como extensão para evoluções posteriores.
- Na persistência em arquivo, usei texto simples com uma entidade por linha, separando os campos por ponto e vírgula, e o desenvolvimento foi auxiliado pelo GitHub Copilot para acelerar a estruturação inicial da solução.
- No `MenuPrincipal`, organizei um fluxo de CLI com validações de entrada e gravação das entidades no arquivo de dados, para facilitar testes manuais e execução do protótipo.
