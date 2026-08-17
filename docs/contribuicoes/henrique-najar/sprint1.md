# Registro de Contribuição — Sprint 1 (Lab01S01)

**Integrante:** Henrique Najar  
**Caminho no Repositório:** `docs/contribuicoes/henrique-najar/sprint1.md`

---

## Tarefas Sob Minha Responsabilidade
Como único integrante responsável pelas entregas da Sprint 1, assumi a execução integral dos artefatos solicitados:

- [x] **Modelagem do Diagrama de Casos de Uso (PlantUML):**
  - UC01: Realizar login
  - UC02: Adicionar eBook à estante
  - UC03: Remover eBook da estante
  - UC04: Consultar estante pessoal
  - UC05: Consultar alunos com um eBook
  - UC06: Cadastrar eBook
- [x] **Elaboração das Histórias de Usuário em Markdown (padrão INVEST):**
  - HU01: Realizar login
  - HU02: Adicionar eBook à estante
  - HU03: Remover eBook da estante
  - HU04: Consultar estante pessoal
  - HU05: Consultar alunos com um eBook
  - HU06: Cadastrar eBook
- [x] **Estruturação e Documentação do Repositório GitHub:**
  - Criação da estrutura de pastas padronizada (`docs/diagramas/` e `docs/contribuicoes/henrique-najar/`).
  - Criação do arquivo `README.md` principal com resumos e links diretos para os artefatos.
  - Exportação e inclusão da imagem gerada do diagrama (`casos-de-uso.png`).

---

## Principais Decisões Tomadas
1. **Atentamento ao Escopo de Atores:** Manteve-se a modelagem restrita aos atores humanos (`Aluno` e `Bibliotecário`), alinhada ao modelo demonstrado no roteiro hands-on da Sprint 1.
2. **Relacionamentos de Inclusão (`<<include>>`):** O caso de uso de autenticação `Realizar login` (UC01) foi associado como inclusão obrigatória para as funcionalidades de manipulação e consulta da estante do aluno.
3. **Mapeamento das Regras de Negócio:** As diretrizes do sistema (limite de 4 livros obrigatórios e 2 livres por aluno, máximo de 60 acessos simultâneos por licença e validação do período de acesso) foram detalhadas diretamente nos critérios de aceite das Histórias de Usuário correspondentes (HU02 e HU06).