# Equipe CodeGirls

Alunas:

Bárbara Silva Costa 
RA: 10438935

Ana Clara 
RA:

Nicole
RA:

 # SUMÁRIO:

- Sobre o jogo
– Estrutura criada
– VOs feitos
– Startup e Deltas feitos
– Strategies implementadas
– Config, ConsoleApp e Main prontos
– Persistência / GameEngine ainda em desenvolvimento  

# REFATORAÇÃO DO STARTUP GAME - Sobre o jogo:

Neste repositório há o projeto de refatoração do Startup Game: um jogo em console do qual cada jogador administra uma startup ao longo de 8 rodadas.

Em cada rodada, o jogador precisa tomar algumas decisões, como:

- Marketing
- Equipe
- Produto
- Investidores
- Cortar Custos

Essas decisões irão impactar em diferentes áreas, como: : Caixa, Receita Base, Reputação e Moral da startup!

O Objetivo do jogo é calcular, ao final das 8 rodadas, um score e, posteriormente, apresentar um ranking.

Já o objetivo deste projeto, como solicitado, é aplicar boas práticas de POO, por meio de padrões Strategy e uso de Value Objects.


# ESTRUTURA DO PROJETO:

startup-game-refactor/
  resources/
    .gitkeep 
    game.properties
    schema.sql

  src/
    Main.java
    .gitkeep

    actions/
      CortarCustos.java
      DecisaoFactory.java
      DecisaoStrategy.java
      EquipeStrategy.java
      InvestidoresStrategy.java
      MarketingStrategy.java
      ProdutoStrategy.java

    config/
      Config.java
    
    engine/
      GameEngine.java
      ScoreService.java

    model/
      Deltas.java
      Startup.java
      vo/
        Dinheiro.java
        Humor.java
        Percentual.java
        Deltas.java
        Startup.java

    persistente/
      DataSourceProvider.java
      DecisaoAplicadaRepository.java
      RodadaRepository.java
      StartupRepository.java

    ui/
      ConsoleApp.java
    
# VOs feitos:

# Startup e Deltas feitos

# Strategies implementadas


# Config, ConsoleApp e Main prontos


# Persistência / GameEngine ainda em desenvolvimento  


# Configurações do jogo:
total.rodadas=8
max.decisoes.por.rodada=3

Esses valores serão lidos pela classe Config.

# Principais componentes implementados:

- Strategy:
CortarCustos.java
DecisaoFactory.java
DecisaoStrategy.java
EquipeStrategy.java
InvestidoresStrategy.java
MarketingStrategy.java
ProdutoStrategy.java

Cada Strategy vai retornar um Deltas.


- Value Objects (VO):
model.vo.Dinheiro.java
model.vo.Humor.java
model.vo.Percentual.java


- Modelo da Startup:
model.Startup.java
model.Deltas.java

-Interface do Console:
ui.ConsoleApp

A classe Main serve exclusivamente para delegar o ConsoleApp.









Obs:
As classes abaixo já existem, mas ainda estão em desenvolvimento:

engine.GameEngine

engine.ScoreService

A responsabilidade da GameEngine será:

controlar o loop de rodadas

aplicar as decisions usando as strategies

atualizar Startup com base em Deltas

registrar histórico

calcular o ranking final

A ScoreService será responsável por encapsular a lógica de cálculo de score final.


# Banco de Dados (H2):

O projeto irá utilizar H2, com URL padrão.

A URL está em persistence/DataSourceProvider.java

O resources/ schema.sql precisará conter as tabelas: startup, rodada e decisao_aplicada.

# Como compilar e rodar este projeto:

Na pasta startup-game-refactor: 
Digite para compilar: javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })

Para Executar, digite: 
java -cp "out;resources" Main


# Versionamento: usando Git:
o Repositório se encontra neste link: https://github.com/bahscosta/startup-game-refactor

Atualização 21/11: README inicial criado com base nas implementações já concluídas.

