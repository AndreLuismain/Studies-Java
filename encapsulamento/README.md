# Encapsulamento, Invariantes de Domínio e Java Records

Estudo comparativo entre **Classes Tradicionais Encapsuladas** e **Java Records (`record`)**, abordando proteção de invariantes, construtores compactos e operações imutáveis com precisão monetária (`BigDecimal`).

---

## Exercícios Implementados

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`Easy.java`](./Encapsulamento_Records_vs_Classes/src/Easy.java)** | Easy | Implementa `record Point(double x, double y)` com método de cálculo de distância euclidiana (`Math.hypot`) e algoritmo de busca do par de pontos mais próximo em uma lista. | `record`, Métodos de Domínio em Records, Geometria Computacional Básica. |
| **[`Medium.java`](./Encapsulamento_Records_vs_Classes/src/Medium.java)** | Medium | Implementa a classe `Person` com atributos privados (`name`, `age`, `email`) e validação rigorosa nos *setters* (rejeição de nome vazio, idade fora do intervalo `[0, 150]` e e-mail sem `@` via `IllegalArgumentException`). | Encapsulamento Estrito, Proteção de Invariantes, *Fail-Fast Validation*. |
| **[`Hard.java`](./Encapsulamento_Records_vs_Classes/src/Hard.java)** | Hard | Implementa o Value Object `record Money(BigDecimal amount, String currency)` com **construtor compacto** que proíbe quantias negativas, validação de compatibilidade cambial em `add(Money)`, multiplicação e comparação em um carrinho de compras. | *Value Object Pattern*, Construtor Compacto de Record, Aritmética Monetária com `BigDecimal`. |

---

## Como Compilar e Executar

```powershell
cd encapsulamento/Encapsulamento_Records_vs_Classes/src
javac Easy.java Medium.java Hard.java
java Easy
java Medium
java Hard
```
