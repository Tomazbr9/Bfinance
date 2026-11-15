# ADR-006 — Isolamento de Dados por Usuário

**Status:** Aceita

## Contexto
O BFinance permitirá o cadastro de múltiplos usuários, exigindo que os dados financeiros de cada usuário sejam protegidos contra acessos não autorizados.

## Decisão
Adotar isolamento lógico dos dados financeiros por usuário, utilizando o identificador do usuário autenticado.

- Contas, categorias e investimentos pertencem a um usuário.
- Consultas e operações devem respeitar a propriedade dos recursos.
- O identificador do usuário será obtido do contexto autenticado.
- O isolamento será aplicado na camada de aplicação e nas consultas de persistência.

## Consequências
- Impede o acesso autorizado de um usuário aos dados financeiros de outro.
- Permite utilizar um banco de dados compartilhado entre usuários.
- Exige validação de propriedade nas operações financeiras.
- Aumenta a necessidade de testes de autorização e isolamento.