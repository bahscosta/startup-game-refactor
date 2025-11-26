# Equipe CodeGirls

Alunas:

Bárbara Silva Costa 
RA: 10438935

Nicole Sofia Silva Beira
RA: 10403742

 # SUMÁRIO:

- Sobre o jogo
- Versionamento: usando Git
– Estrutura do projeto
– Resumo da estrutura do projeto
- Configurações do jogo
- Principais componentes implementados
- Como compilar e rodar este projeto:
- Padrões de projeto usados
- Banco de Dados

# Sobre o jogo - REFATORAÇÃO DO STARTUP GAME:

Neste repositório há o projeto de refatoração do Startup Game: um jogo em console do qual duas startups passam 8 rodadas tomando decisões que afetam o desempenho delas.


As decisões estão listadas abaixo:

- Marketing
- Equipe
- Produto
- Investidores
- Cortar Custos

Elas irão impactar em diferentes áreas, como: : Caixa, Receita Base, Reputação e Moral da startup!


O Objetivo do jogo é calcular, ao final das rodadas, um score e, posteriormente, apresentar um ranking.

Já o objetivo deste projeto, como solicitado, é aplicar boas práticas de POO, por meio de padrões Strategy e uso de Value Objects.



# Versionamento: usando Git:
o Repositório se encontra neste link: https://github.com/bahscosta/startup-game-refactor


# ESTRUTURA DO PROJETO:

startup-game-refactor/
│
├── .vscode/
│    └── settings.json
│
├── resources/
│    ├── .gitkeep
│    └── schema.sql
│
├── src/
│   ├── actions/
│   │    ├── CortarCustosStrategy.java
│   │    ├── DecisaoFactory.java
│   │    ├── DecisaoStrategy.java
│   │    ├── EquipeStrategy.java
│   │    ├── InvestidoresStrategy.java
│   │    ├── MarketingStrategy.java
│   │    └── ProdutoStrategy.java
│   │
│   ├── config/
│   │    └── Config.java
│   │
│   ├── engine/
│   │    ├── BotDecision.java
│   │    ├── GameEngine.java
│   │    └── ScoreService.java   (seu serviço de score)
│   │
│   ├── model/
│   │    ├── vo/
│   │    │     ├── Dinheiro.java
│   │    │     ├── Humor.java
│   │    │     └── Percentual.java
│   │    │
│   │    ├── Deltas.java
│   │    └── Startup.java
│   │
│   ├── observer/
│   │    ├── ConsoleObserver.java
│   │    ├── CSVObserver.java
│   │    └── ObserverStartup.java
│   │
│   ├── persistence/
│   │    ├── DataSourceProvider.java
│   │    ├── DecisaoAplicadaRepository.java
│   │    ├── RodadaRepository.java
│   │    └── StartupRepository.java
│   │
│   └── ui/
│        └── ConsoleApp.java
│
├── .gitkeep
├── App.java
├── Main.java
├── game.properties
├── README.md
└── relatorio_startups.csv


#Estrutura resumida do projeto:
src/
├── actions/ # Estratégias das decisões (Strategy + Factory)
├── config/ # Configurações do jogo (game.properties)
├── engine/ # GameEngine (coração do jogo) + IA Bot
├── model/ # Startup, Deltas e Value Objects (Dinheiro, Humor…)
├── observer/ # Observers (Console e CSV)
├── persistence/ # Repositórios (opcional avançado)
└── ui/ # Interface simples de console

//VOs feitos:
// Startup e Deltas feitos
//Strategies implementadas
//Config, ConsoleApp e Main prontos


# Configurações do jogo:
total.rodadas=8
max.decisoes.por.rodada=3

Esses valores serão lidos pela classe Config.

# Principais componentes implementados:

1 - GameEngine (engine/):

O “motor do jogo”.
Responsável por:

iniciar a simulação

controlar o fluxo das rodadas

pedir decisões (jogador ou bot)

aplicar estratégias

atualizar atributos da startup

calcular receita e score

chamar observers

exibir ranking final

É o componente mais importante.

2️- Startup (model/)

Objeto principal do jogo.
Cada startup tem:

caixa (Dinheiro)

receita base (Dinheiro)

moral (Humor)

reputação (Humor)

bônus de receita

lista de observers

Guarda tudo que muda ao longo da simulação.

3️- Value Objects (model/vo/)

Representam valores com regra própria, usados para deixar o código mais organizado:

Dinheiro

Humor

Percentual

Eles garantem consistência e encapsulam regras (bom ponto de POO).

4️- Deltas (model/)

Classe que representa as mudanças que cada decisão gera:

delta de caixa

delta de moral

delta de reputação

delta de bônus

Cada Strategy retorna um objeto Deltas.

5️- Strategy + Factory (actions/)
Strategy:

Cada decisão do jogo é uma classe específica que implementa a interface:

MarketingStrategy

ProdutoStrategy

EquipeStrategy

InvestidoresStrategy

CortarCustosStrategy

Cada uma tem sua lógica de negócio.

 Factory:

DecisaoFactory cria a estratégia certa de acordo com o código selecionado.

Esse conjunto mostra entendimento sólido de:

polimorfismo

responsabilidade única

desacoplamento

extensibilidade

6️- Observer Pattern (observer/)

Você implementou dois observers reais:

ConsoleObserver

CSVObserver

Eles recebem eventos como:

início da rodada

decisão aplicada

fim da rodada

fim do jogo

Isso adiciona reatividade ao sistema, sem acoplamento.

7️- Bot de IA simples (engine/BotDecision.java)

Componente opcional, que permite:

decisões automáticas para a BetaTech

escolhas aleatórias, sem repetição

funcionamento independente do jogador

Demonstra:

uso de lógica adicional

extensão do jogo

cumprimento de um dos opcionais do professor

8️- Persistência inicial (persistence/)

Você criou repositórios com interfaces reais:

DataSourceProvider

RodadaRepository

StartupRepository

etc.

Mesmo não sendo totalmente utilizados, mostram estrutura de software profissional.

9️- Config (config/Config.java)

Leitura automática do arquivo game.properties:

total de rodadas

quantidade de decisões por rodada

Separar configuração em arquivo externo é muito certo e demonstra maturidade.

10- Interface de Usuário (ui/ConsoleApp.java / App.java)

A interface simples que:

dá boas-vindas

inicia o jogo

imprime mensagens

encerra o jogo

E o App.java funciona como ponto de entrada oficial.



>>>>RESUMO DOS PRINCIPAIS COMPONENTES

GameEngine → controla toda a lógica do jogo

Startup → representa o estado de cada empresa

Strategy + Factory → decisões do jogo desacopladas

Deltas → mudanças aplicadas a cada rodada

Value Objects → Dinheiro, Humor, Percentual

Observer Pattern → console e CSV registrando eventos

Bot IA → decisões automáticas para uma startup

Config → leitura de arquivo de configurações

Persistência (estrutura inicial)

Interface Console → entrada e saída do usuário



# Como compilar e rodar este projeto:
Rodar direto pela classe App.java:
Insira:
javac App.java

depois: java App


ou:
startup-game-refactor/
e insira: javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })

e depois: java -cp "out;resources" Main



# Padrões de Projeto usados:

--> Strategy:

Cada “decisão” do jogo é uma estratégia diferente.
Assim, para novas decisões, basta criar novas classes sem mexer na GameEngine.

--> Observer (Opcional entregue)

Implementei dois observers:

ConsoleObserver → mostra eventos no terminal

CSVObserver → gera um arquivo .csv com as mudanças das startups

Eventos observados:

início da rodada

fim da rodada

decisão aplicada

fim do jogo

--> Exportação CSV (Opcional entregue)

Gerado automaticamente:
relatorio_startups.csv
Guarda registro de cada evento relevante (startup, moral, caixa, reputação etc).

--> Bot IA simples (Opcional entregue)

A BetaTech joga automaticamente usando a classe BotDecision.java, que escolhe decisões de forma aleatória (sem repetir).
Eu controlo a AlphaLabs e o bot controla a BetaTech.


# Banco de Dados (H2):

O projeto irá utilizar H2, com URL padrão.

A URL está em persistence/DataSourceProvider.java

O resources/ schema.sql precisará conter as tabelas: startup, rodada e decisao_aplicada.



