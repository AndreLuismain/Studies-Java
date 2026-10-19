# Herança vs. Composição e Injeção de Dependências

Módulo prático que demonstra os limites da **Herança de Classes (`extends`)** frente ao princípio **"Prefira Composição sobre Herança"** (*Composition over Inheritance*), aplicando delegação de interfaces, *Strategy Pattern* em tempo de execução e *Builder Pattern*.

---

## Exercícios Implementados

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`Easy.java`](./Easy/src/Easy.java)** | Easy | Analisa a falha semântica do relacionamento *Is-A* ("É-UM") ao tentar estender `RobotDog` a partir de `Dog extends Animal`, demonstrando por que comportamentos ortogonais devem ser modelados via interfaces. | Relacionamento *Is-A* vs. *Can-Do*, Fragilidade de Hierarquias de Herança. |
| **[`Medium.java`](./Easy/src/Medium.java)** | Medium | Modela um veículo anfíbio (`AmphibiousVehicle`) sem herança múltipla, implementando as interfaces `Drivable` e `Sailable` e compondo internamente instâncias de `CarEngine` e `BoatHull` por delegação. | Composição e Delegação (*Has-A*), Interfaces Múltiplas, Desacoplamento. |
| **[`Hard.java`](./Easy/src/Hard.java)** | Hard | Constrói um subsistema flexível de `Logger` composto por estratégias injetáveis de formatação (`Formatter`: `JsonFormatter`, `PlainFormatter`) e saída (`Output`: `ConsoleOutput`, `FileOutput`), orquestradas por um `LoggerBuilder` com troca dinâmica em *runtime*. | *Strategy Pattern*, Injeção de Dependência via Construtor, *Builder Pattern*, Polimorfismo por Interface. |

---

## Como Compilar e Executar

```powershell
cd "Herança_vs_Composição/Easy/src"
javac Easy.java Medium.java Hard.java
java Easy
java Medium
java Hard
```
