# Sprint 3

---

**Contribuição:** Implementei os métodos relacionados ao `Aluno`, `Bibliotecario` e `Usuario`, dando início ao protótipo funcional do sistema com foco na regra de negócio principal da estante do aluno e na autenticação do usuário.

**Decisões:**
- Em `Usuario.autenticar()`, validei a senha informada comparando diretamente com a senha cadastrada no objeto, mantendo a lógica simples e compatível com o fluxo de login em linha de comando.
- Em `Aluno.adicionarEBook()`, apliquei a regra de limite de 6 eBooks na estante e lancei `IllegalStateException` quando o limite é atingido, permitindo tratamento amigável na interface.
- Em `Aluno.consultarEstante()`, mantive a saída textual simples para facilitar a verificação manual do resultado durante os testes da sprint.
- Em `Bibliotecario`, preservei os métodos da classe alinhados ao protótipo da sprint, mantendo `consultarAlunosComEBook()` como ponto de integração futura com repositório/persistência, `cadastrarEBook()` com validação básica de nulidade e `renovarCatalogo()` como extensão para evoluções posteriores.
