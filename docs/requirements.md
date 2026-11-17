# BFinance — Requisitos do MVP

## 1. Objetivo

O BFinance é uma aplicação web de finanças pessoais destinada ao controle de contas, receitas, despesas, transferências e investimentos.

## 2. Requisitos funcionais

### RF-001 — Gerenciar contas
- Cadastrar, consultar, editar e excluir contas financeiras.
- Informar nome e saldo inicial (opcional).
- Consultar o saldo atualizado de cada conta.

### RF-002 — Gerenciar categorias
- Cadastrar, consultar, editar e excluir categorias.
- Permitir a criação de categorias principais e subcategorias.
- Organizar categorias em uma hierarquia de até dois níveis.

### RF-003 — Gerenciar transações
- Registrar receitas e despesas.
- Editar, excluir e consultar transações.
- Informar descrição, valor, data, tipo, conta e categoria.
- Consultar o histórico de movimentações.

### RF-004 — Realizar transferências
- Transferir valores entre contas.
- Informar conta de origem, destino, valor e data.
- Consultar o histórico de transferências.

### RF-005 — Gerenciar investimentos
- Cadastrar e consultar investimentos.
- Registrar aplicações e resgates.
- Consultar o saldo aplicado em cada investimento.
- Consultar o histórico de aplicações e resgates.
- Permitir o cadastro de investimentos com valor inicial.

### RF-006 — Visualizar dashboard
- Consultar o saldo total das contas.
- Visualizar receitas e despesas do mês.
- Visualizar o resultado financeiro mensal.
- Consultar o total aplicado em investimentos.
- Visualizar movimentações recentes.

### RF-007 — Autenticação
- Permitir o cadastro de usuários com e-mail e senha.
- Autenticar usuários.
- Permitir renovação da sessão.
- Permitir logout.
- Restringir o acesso às funcionalidades financeiras.

## 3. Requisitos não funcionais

- **RNF-001:** A interface deve ser responsiva e funcionar em navegadores modernos.
- **RNF-002:** O acesso aos dados financeiros deve exigir autenticação.
- **RNF-003:** Os valores monetários devem utilizar precisão decimal.
- **RNF-004:** As operações financeiras devem garantir consistência e atomicidade.
- **RNF-005:** O sistema deve apresentar valores em reais (BRL) e utilizar o padrão brasileiro de datas.
- **RNF-006:** Os dados financeiros de cada usuário devem permanecer isolados dos demais usuários.

## 4. Regras de negócio

### Contas
- **RN-001:** O saldo de uma conta pode ser negativo.
- **RN-002:** Quando o saldo inicial não for informado, deverá ser considerado zero.

### Categorias
- **RN-003:** Categorias não possuem tipo de transação; receitas e despesas são definidas nas transações.
- **RN-004:** Uma categoria pode ser principal ou subcategoria, com hierarquia limitada a dois níveis.
- **RN-005:** Uma subcategoria deve pertencer a uma categoria principal do mesmo usuário.
- **RN-006:** O nome da categoria não pode ser vazio.
- **RN-007:** Não são permitidas categorias com nomes duplicados no mesmo nível e sob o mesmo pai, para o mesmo usuário.
- **RN-008:** Categorias vinculadas a transações não podem ser excluídas.
- **RN-009:** Categorias principais com subcategorias não podem ser excluídas enquanto possuírem subcategorias.

### Transações
- **RN-010:** O valor de uma transação deve ser maior que zero.
- **RN-011:** Receitas aumentam o saldo da conta; despesas diminuem.
- **RN-012:** Transações futuras só afetam o saldo na data programada.
- **RN-013:** Uma categoria pode ser utilizada tanto em receitas quanto em despesas.

### Transferências
- **RN-014:** Transferências devem ocorrer entre contas diferentes e ativas.
- **RN-015:** Transferências reduzem o saldo da origem e aumentam o saldo do destino, sem representar receitas ou despesas.
- **RN-016:** Transferências futuras só afetam os saldos na data programada.

### Investimentos
- **RN-017:** Aplicações reduzem o saldo da conta de origem e aumentam o saldo investido.
- **RN-018:** Resgates reduzem o saldo investido e aumentam o saldo da conta de destino.
- **RN-019:** Aplicações e resgates não são classificados como receitas ou despesas.
- **RN-020:** O valor resgatado não pode superar o saldo disponível do investimento.
- **RN-021:** Investimentos existentes podem ser cadastrados com valor inicial, sem movimentar contas financeiras.
- **RN-022:** Aplicações e resgates futuros só afetam os saldos na data programada.

### Usuários
- **RN-023:** O e-mail do usuário deve ser único.
- **RN-024:** Cada usuário pode acessar e modificar apenas seus próprios dados financeiros.
- **RN-025:** Contas, categorias e investimentos devem pertencer a um usuário.

## 5. Fora do escopo do MVP

- Cartões de crédito e faturas.
- Integrações bancárias e com corretoras.
- Rendimentos, rentabilidade e valorização de investimentos.
- Cotações automáticas de ativos.
- Controle de investimentos por quantidade de cotas.
- Orçamentos e metas financeiras.
- Transações recorrentes automáticas.
- Relatórios financeiros avançados.