# language: pt
Funcionalidade: API - Itens

  @smoke
  Cenário: Registrar um novo item
    Quando submeter um novo item
    Então o item é registrado com sucesso

  Cenário: Buscar um item existente
    Dado que um item já foi publicado
    Quando requisitar a busca do item
    Então o item é exibido com sucesso

  Cenário: Listar itens existentes
    Dado que um item já foi publicado
    Quando requisitar a lista de itens
    Então os itens são exibidos com sucesso

  Cenário: Alterar um item existente
    Dado que um item já foi publicado
    Quando requisitar a alteração do item
    Então o item é atualizado com sucesso

  Cenário: Excluir um item existente
    Dado que um item já foi publicado
    Quando requisitar a exclusão do item
    Então o item é removido com sucesso
