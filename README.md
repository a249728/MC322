# Projetos e Entregas – MC322  

Este repositório contém as entregas para a disciplina **MC322**. O objetivo é documentar o desenvolvimento dos projetos e manter um registro das implementações realizadas.  

---

## Lab01 - Robôs e Ambiente  

Este laboratório envolve a implementação dos estágios iniciais de um Simulador de Robôs por três arquivos principais:  

- **Robo.java** – Define a classe `Robo`, suas propriedades e métodos.  
- **Ambiente.java** – Implementa a classe `Ambiente`, responsável por definir suas características e comportamentos.  
- **Main.java** – Classe principal do programa, onde os objetos das classes anteriores são criados e manipulados conforme as regras estabelecidas no enunciado.  

---

## Lab02 - Simulação Avançada de Robôs  

Este laboratório expande o simulador de robôs, introduzindo novos tipos de robôs e funcionalidades avançadas.  

### Arquivos principais:  
- **Robo.java** – Classe base para todos os robôs, com métodos para movimentação e identificação de obstáculos.  
- **RoboTerrestre.java** – Especialização de `Robo` para robôs terrestres, com restrições de velocidade máxima.  
- **RoboAereo.java** – Especialização de `Robo` para robôs aéreos, com controle de altitude.  
- **RoboSubterraneo.java** – Especialização de `RoboAereo` para robôs subterrâneos, com altitude mínima.  
- **RoboLaser.java** – Especialização de `RoboTerrestre` com capacidade de disparar lasers.  
- **RoboCriador.java** – Robô capaz de criar outros robôs no ambiente.  
- **RoboCorredor.java** – Especialização de `RoboTerrestre` com restrições de velocidade mínima e máxima.  
- **Ambiente.java** – Classe que define o ambiente tridimensional e gerencia os robôs ativos.  
- **Main.java** – Classe principal que executa a simulação, criando robôs, movimentando-os e verificando interações.  

---

## Lab03 - Simulador de Robôs com Sensores e Obstáculos

Este laboratório aprimora o simulador com a adição de sensores e obstáculos, implementando novos relacionamentos entre classes.

### Arquivos principais:
- **Ambiente.java** - Atualizado para gerenciar obstáculos e robôs com ArrayList
- **Obstaculo.java** - Nova classe para representar obstáculos com tipo enumerado
- **TipoObstaculo.java** - Enum que define tipos de obstáculos (PEDRA, ARVORE, BURACO, LAGO)
- **Sensor.java** - Classe base para sensores
- **SensorIluminacao.java** - Sensor específico para monitorar iluminação
- **SensorPressao.java** - Sensor específico para monitorar pressão
- **Main.java** - Atualizado com menu interativo para controle dos robôs

### Funcionalidades Implementadas:
1. **Obstáculos no Ambiente**:
   - Diferentes tipos de obstáculos com propriedades específicas
   - Verificação de colisões com obstáculos
   - Métodos para adicionar/remover obstáculos

2. **Sensores**:
   - Sistema de sensores agregados aos robôs
   - Dois tipos específicos: Iluminação e Pressão
   - Métodos de monitoramento do ambiente

3. **Menu Interativo**:
   - Visualização do status de robôs e ambiente
   - Controle de movimentos básicos
   - Utilização e relatório dos sensores
   - Criação e destruição de robôs e obstáculos

4. **Melhorias nas Classes Existentes**:
   - Robôs atualizados para considerar obstáculos
   - Ambiente com métodos para verificação de limites
   - Relacionamentos entre classes implementados (agregação, composição)

### Estrutura do Projeto Atualizada:
MC322/
├── src/
│ ├── Lab01/
│ │ ├── Main.java
│ │ ├── Robo.java
│ │ └── Ambiente.java
│ ├── Lab02/
│ │ ├── Main.java
│ │ ├── Robo.java
│ │ ├── RoboTerrestre.java
│ │ ├── RoboAereo.java
│ │ ├── RoboSubterraneo.java
│ │ ├── RoboLaser.java
│ │ ├── RoboCriador.java
│ │ ├── RoboCorredor.java
│ │ └── Ambiente.java
│ ├── Lab03/
│ │ ├── Main.java
│ │ ├── Robo.java
│ │ ├── RoboTerrestre.java
│ │ ├── RoboAereo.java
│ │ ├── RoboSubterraneo.java
│ │ ├── RoboLaser.java
│ │ ├── RoboCriador.java
│ │ ├── RoboCorredor.java
│ │ ├── Ambiente.java
│ │ ├── Obstaculo.java
│ │ ├── TipoObstaculo.java
│ │ ├── Sensor.java
│ │ ├── SensorIluminacao.java
│ │ └── SensorPressao.java
├── bin/
└── README.md

### Diagrama de Classes (Lab03)
![Diagrama de Classes Lab03](diagrama_classes_lab03.png)
*Diagrama mostra as relações entre as classes principais, incluindo herança, agregação e composição*

### Como Executar:
1. Navegue até a pasta Lab03: `cd src/Lab03`
2. Compile os arquivos: `javac *.java`
3. Execute o programa: `java Main`
4. Use o menu interativo para controlar a simulação

### Tecnologias Utilizadas:
- **IDE**: VSCode 1.85.1
- **Java**: OpenJDK 17.0.8
- **Controle de Versão**: Git/GitHub