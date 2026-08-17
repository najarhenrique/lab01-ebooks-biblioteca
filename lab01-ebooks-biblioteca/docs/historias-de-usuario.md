# Histórias de Usuário — Sistema de Gestão de eBooks

---

## Responsável: Henrique Najar

### HU01: Realizar login
**Descrição:** Como usuário do sistema (Aluno ou Bibliotecário), eu quero informar minhas credenciais e senha para me autenticar no sistema, para que apenas pessoas autorizadas acessem minha conta e minhas funcionalidades correspondentes.
**Critérios de Aceite:**
- O sistema deve validar a senha digitada pelo usuário.
- O acesso só deve ser liberado mediante confirmação válida das credenciais.

### HU02: Adicionar eBook à estante
**Descrição:** Como aluno, eu quero adicionar eBooks à minha estante pessoal durante o período de acesso do semestre, para que eu possa ter acesso à leitura dos títulos selecionados.
**Critérios de Aceite:**
- O sistema deve verificar se a operação ocorre dentro do período de acesso permitido do semestre.
- O aluno pode adicionar até 4 eBooks de leitura obrigatória (indicados pela disciplina).
- O aluno pode adicionar até 2 eBooks de leitura livre (de sua própria escolha).
- O sistema de gestão de eBooks deve notificar o sistema de estatísticas de uso sempre que um eBook for adicionado à estante.

### HU03: Remover eBook da estante
**Descrição:** Como aluno, eu quero remover um eBook previamente adicionado da minha estante pessoal durante o período de acesso, para liberar espaço para outro eBook de leitura obrigatória ou livre.
**Critérios de Aceite:**
- A remoção só pode ser executada dentro do período de acesso permitido no semestre.
- O eBook removido deixa de ocupar a cota na estante do aluno.

---


### HU04: Consultar estante pessoal
**Descrição:** Como aluno, eu quero visualizar os eBooks que adicionei à minha estante pessoal, para acompanhar os títulos de leitura obrigatória e livre escolhidos para o semestre.
**Critérios de Aceite:**
- Exibir a lista de eBooks atualmente presentes na estante do aluno logado.
- Diferenciar ou identificar visualmente os títulos de leitura obrigatória e livre.

### HU05: Consultar alunos com um eBook
**Descrição:** Como bibliotecário, eu quero pesquisar determinado eBook no sistema para saber quais alunos o possuem em suas estantes, para acompanhar o uso e a distribuição do acervo.
**Critérios de Aceite:**
- O bibliotecário deve selecionar ou buscar um determinado eBook cadastrado.
- O sistema deve listar todos os alunos que adicionaram o referido título à estante no semestre atual.

### HU06: Cadastrar eBook
**Descrição:** Como bibliotecário, eu quero cadastrar novos eBooks no acervo a cada semestre, informando suas propriedades e licença de uso, para disponibilizá-los aos alunos.
**Critérios de Aceite:**
- Devem ser informados: título, editora, formato de arquivo (ex.: PDF ou EPUB) e categoria (ex.: literatura, técnico ou periódico).
- Deve ser configurada a licença de uso definindo o limite de acessos simultâneos (máximo de 60 acessos simultâneos).