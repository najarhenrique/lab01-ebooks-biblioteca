# Registro de Contribuição — Sprint 2 (Lab01S02)

**Integrante:** Henrique Najar  
**Caminho no Repositório:** `docs/contribuicoes/henrique-najar/sprint2.md`

---

## Semana 1
**Contribuição:** Revisão do Diagrama de Classes UML em PlantUML (`docs/diagramas/diagrama-de-classes.puml` e `.png`), ajustando inconsistências para adequar o modelo visual às especificações solicitadas pelo professor.

**Decisões:** 
- Incluí a associação simples entre `EBook` e `Licenca` (`1..1`) para garantir o alinhamento com os requisitos de controle de acessos simultâneos.
- Mantive a estrutura de herança com `Usuario` (classe abstrata), a composição entre `Aluno` e `Estante`, e a agregação de `0..6` livros entre `Estante` e `EBook`.

---

## Semana 2
**Contribuição:** Adequação da estrutura de pastas e pacotes Java do projeto de acordo com o solicitado pelo professor, sob a convenção `br.edu.pucminas.biblioteca` (com as subpastas `modelo/` e `persistencia/`). Ajuste e refinamento das classes Java já existentes no modelo (`Usuario`, `Aluno`, `Bibliotecario`, `EBook`, `Licenca`, `Estante` e `TipoLeitura`), implementando os construtores completos e stubs de métodos necessários.

**Decisões:**
- Implementei construtores completos em todas as classes do modelo para permitir a reconstituição de objetos durante a leitura da persistência na Sprint 3.
- Implementei por completo o método `contarEBooks()` na classe `Estante` (retornando `ebooks.size()`), já deixando pronta a funcionalidade necessária para a validação do limite de eBooks.
- Incluí a classe `MenuPrincipal.java` e o arquivo `.gitkeep` em `persistencia/` para garantir a integridade do envio de toda a hierarquia de pastas para o GitHub.