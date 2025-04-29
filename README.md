# Projetos e Entregas – MC322  

Este repositório contém as entregas da disciplina **MC322**, documentando o desenvolvimento dos projetos e registrando as implementações realizadas.  

---

## Lab01 - Robôs e Ambiente  

Implementação inicial de um Simulador de Robôs, composta por três arquivos principais:  

- **Robo.java** – Define a classe `Robo`, suas propriedades e métodos.  
- **Ambiente.java** – Implementa a classe `Ambiente`, responsável por suas características e comportamentos.  
- **Main.java** – Classe principal que cria e manipula os objetos conforme as regras do enunciado.  

---

## Lab02 - Simulação Avançada de Robôs  

Expansão do simulador de robôs, com novos tipos e funcionalidades avançadas.  

### Arquivos principais:  
- **Robo.java** – Classe base para todos os robôs, com métodos de movimentação e detecção de obstáculos.  
- **RoboTerrestre.java** – Especialização de `Robo` para robôs terrestres, com restrições de velocidade máxima.  
- **RoboAereo.java** – Especialização de `Robo` para robôs aéreos, com controle de altitude.  
- **RoboSubterraneo.java** – Especialização de `RoboAereo` para robôs subterrâneos, com altitude mínima.  
- **RoboLaser.java** – Especialização de `RoboTerrestre` com capacidade de disparar lasers.  
- **RoboCriador.java** – Robô que cria outros robôs no ambiente.  
- **RoboCorredor.java** – Especialização de `RoboTerrestre` com restrições de velocidade mínima e máxima.  
- **Ambiente.java** – Define o ambiente tridimensional e gerencia os robôs ativos.  
- **Main.java** – Executa a simulação, criando robôs, movimentando-os e verificando interações.  

---

## Lab03 - Simulador de Robôs com Sensores e Obstáculos  

Aprimoramento do simulador com sensores e obstáculos, introduzindo novos relacionamentos entre classes.  

### Arquivos principais:  
- **Ambiente.java** – Atualizado para gerenciar obstáculos e robôs com `ArrayList`.  
- **Obstaculo.java** – Representa obstáculos com tipo enumerado.  
- **TipoObstaculo.java** – Enum que define tipos de obstáculos (PEDRA, ARVORE, BURACO, LAGO).  
- **Sensor.java** – Classe base para sensores.  
- **SensorIluminacao.java** – Sensor para monitorar iluminação.  
- **SensorPressao.java** – Sensor para monitorar pressão.  
- **Main.java** – Atualizado com menu interativo para controle dos robôs.  
- **Robo.java** – Classe base para todos os robôs.  
- **RoboTerrestre.java** – Especialização de `Robo` para robôs terrestres.  
- **RoboAereo.java** – Especialização de `Robo` para robôs aéreos.  
- **RoboSubterraneo.java** – Especialização de `RoboAereo` para robôs subterrâneos.  
- **RoboLaser.java** – Robô terrestre com capacidade de disparar lasers.  
- **RoboCriador.java** – Robô que cria outros robôs no ambiente.  
- **RoboCorredor.java** – Robô terrestre com restrições de velocidade mínima e máxima.  
- **Diagrama.png** – Diagrama de classes.  

### Funcionalidades Implementadas:  
1. **Obstáculos no Ambiente**:  
   - Diferentes tipos de obstáculos com propriedades específicas.  
   - Verificação de colisões.  
   - Métodos para adicionar/remover obstáculos.  

2. **Sensores**:  
   - Sistema de sensores agregados aos robôs.  
   - Sensores de Iluminação e Pressão.  
   - Métodos para monitorar o ambiente.  

3. **Menu Interativo**:  
   - Visualização do status de robôs e ambiente.  
   - Controle de movimentos básicos.  
   - Relatórios dos sensores.  
   - Criação e destruição de robôs e obstáculos.  

4. **Melhorias nas Classes Existentes**:  
   - Robôs atualizados para considerar obstáculos.  
   - Ambiente com métodos para verificação de limites.  
   - Relacionamentos entre classes implementados (agregação, composição).  

---

## Estrutura do Projeto  

```plaintext
MC322/
├── src/
│   ├── Lab01/
│   │   ├── Main.java
│   │   ├── Robo.java
│   │   └── Ambiente.java
│   ├── Lab02/
│   │   ├── Main.java
│   │   ├── Robo.java
│   │   ├── RoboTerrestre.java
│   │   ├── RoboAereo.java
│   │   ├── RoboSubterraneo.java
│   │   ├── RoboLaser.java
│   │   ├── RoboCriador.java
│   │   ├── RoboCorredor.java
│   │   └── Ambiente.java
│   ├── Lab03/
│   │   ├── Main.java
│   │   ├── Robo.java
│   │   ├── RoboTerrestre.java
│   │   ├── RoboAereo.java
│   │   ├── RoboSubterraneo.java
│   │   ├── RoboLaser.java
│   │   ├── RoboCriador.java
│   │   ├── RoboCorredor.java
│   │   ├── Ambiente.java
│   │   ├── Obstaculo.java
│   │   ├── TipoObstaculo.java
│   │   ├── Sensor.java
│   │   ├── SensorIluminacao.java
│   │   └── SensorPressao.java
├── bin/
└── README.md
```
## Como Executar 

1. Chegue até a pasta
   ```bash
   cd src/LabXX
   ```

2. Compile:  
   ```bash
   javac *.java
   ```

3. Execute:  
   ```bash
   java Main
   ```
