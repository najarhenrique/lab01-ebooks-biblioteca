# Registro de Contribuição — Sprint 3 (Lab01S03)

**Integrante:** Henrique Najar  
**Caminho no Repositório:** `docs/contribuicoes/henrique-najar/sprint3.md`

---

## Semana 3
**Contribuição:** Implementação das regras de negócio e refatoração das classes do modelo de domínio (`Licenca`, `EBook` e `Estante`).

**Detalhamento da Implementação:**
- **`Licenca.java`**: Implementação do controle de acessos simultâneos (limite padrão de 60 acessos), manutenção dos dois construtores (padrão e parametrizado), método `temVagaDisponivel()` e validação com disparo de exceção `IllegalStateException` no método `registrarAcesso()`.
- **`EBook.java`**: Adição de construtor sobrecarregado (com 4 parâmetros) para permitir a criação do objeto associando uma `Licenca` padrão, preparando a entidade para a leitura de dados na persistência.
- **`Estante.java`**: Substituição dos stubs pela lógica completa dos métodos `adicionar` (incrementando os contadores de cotas com base no `TipoLeitura`), `remover` e `listar`.

**Decisões:**
- Preservação da assinatura com o parâmetro `TipoLeitura` no método `adicionar` da classe `Estante` para garantir o controle das cotas de leitura obrigatória e livre.
- Manutenção de múltiplos construtores em `Licenca` e `EBook` para oferecer flexibilidade na instanciação de objetos tanto pela aplicação quanto pela camada de persistência.