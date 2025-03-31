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

### Funcionalidades Implementadas:  
1. **Criação de robôs**: Robôs de diferentes tipos podem ser criados e adicionados ao ambiente.  
2. **Movimentação**: Robôs podem se mover no ambiente respeitando suas restrições (ex.: limites de velocidade, altitude, etc.).  
3. **Interação entre robôs**: Robôs podem interagir, como o disparo de lasers para destruir outros robôs.  
4. **Verificação de limites**: O sistema verifica se os robôs estão dentro dos limites do ambiente.  

### Estrutura do Projeto:  
A estrutura do projeto segue as boas práticas de organização de arquivos em Java:  
```
MC322/
├── src/
│   ├── Lab01/
│   │   ├── Main.java
│   │   ├── Robo.java
│   │   └── Ambiente.java
│   └── Lab02/
│       ├── Main.java
│       ├── Robo.java
│       ├── RoboTerrestre.java
│       ├── RoboAereo.java
│       ├── RoboSubterraneo.java
│       ├── RoboLaser.java
│       ├── RoboCriador.java
│       ├── RoboCorredor.java
│       └── Ambiente.java
├── bin/                
└── README.md           # Arquivo de instruções
```

---

### Instruções para Compilação e Execução  

#### Pré-requisitos  
- **Java JDK** versão 8 ou superior.  
- Um terminal ou IDE como **Eclipse** ou **IntelliJ IDEA**.  
  ```

### Contato  
Caso tenha dúvidas ou sugestões, entre em contato:  
- **Ana Luiza Paro Costa**: [a249728@dac.unicamp.br](mailto:a249728@dac.unicamp.br)  
- **Leonardo Carvalho De Luca**: [l288820@dac.unicamp.br](mailto:l288820@dac.unicamp.br)  