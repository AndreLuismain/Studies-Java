# Exercícios Acadêmicos de Programação Orientada a Objetos (`Exercise_POO`)

Exercícios práticos voltados à consolidação de herança, sobrescrita de métodos fundamentais da classe `Object` (`toString`, `equals`) e tratamento de entrada interativa via console (`Scanner` e `InputMismatchException`).

---

## Exercícios Implementados (`src/`)

| Arquivo | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|---|---|
| **[`src/B.java`](./src/B.java)** | Implementa a superclasse `A` (atributos protegidos `m` e `n`) e a subclasse `B extends A` (atributo `k`), sobrescrevendo os métodos `m1()`, `toString()` e `equals(Object)` para comparação estrutural segura entre instâncias. | Herança (`extends`), `super(...)`, Sobrescrita de `equals()` e `toString()`. |
| **[`src/GuessingGame.java`](./src/GuessingGame.java)** | Jogo interativo de adivinhação numérica no terminal com geração pseudo-aleatória (`Random`), contagem de tentativas, dicas direcionais (maior/menor) e tratamento resiliente de entradas não-numéricas (`InputMismatchException`). | Encapsulamento de Estado, `java.util.Random`, `Scanner`, Tratamento de Exceções de Entrada. |

---

## Como Compilar e Executar

```powershell
cd Exercise_POO/src
javac B.java GuessingGame.java
java B
java GuessingGame
```
