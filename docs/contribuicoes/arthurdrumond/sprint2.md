## Semana 1

**Contribuição**
* Fiz as correções no diagrama de caso de uso e criei as classes do diagrama de classes.
* Modelei e implementei no código a herança entre a classe abstrata `Usuario` e suas implementações concretas (`Aluno` e `Bibliotecario`).
* Modelei e implementei os relacionamentos de composição entre `Aluno` e `Estante`, e de agregação entre `Estante` e `EBook`.

**Decisões**
* **Herança (`Usuario`):** Centraliza as credenciais de acesso para evitar código duplicado, garantindo que o sistema instancie apenas os perfis específicos de alunos e bibliotecários.
* **Agregação (`Estante` e `EBook`):** Demonstra que a estante armazena até seis livros, mas os títulos continuam existindo de forma independente no acervo da biblioteca, mesmo se forem removidos da seleção do aluno.
* **Composição (`Aluno` e `Estante`):** Estabelece uma dependência existencial obrigatória, indicando que a estante é estritamente pessoal e seria destruída caso o registro do aluno fosse excluído do sistema.