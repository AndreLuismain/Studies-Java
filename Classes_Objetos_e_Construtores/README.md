# Classes, Objetos, Sobrecarga de Construtores e Padrão Builder

Módulo prático de **Programação Orientada a Objetos em Java** focado em instanciação de classes, sobrecarga e encadeamento de construtores (`this(...)`), imutabilidade e implementação do padrão de projeto criacional **Builder**.

---

## 📋 Exercícios Implementados (`src/`)

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`src/Easy.java`](./src/Easy.java)** | Easy | Modela a classe `Book` (`title`, `author`, `year`) com sobrecarga de construtores (completo e com ano padrão `0`) e sobrescrita de `toString()`. | Instanciação, *Constructor Overloading*, Sobrescrita de `Object.toString()`. |
| **[`src/Medium.java`](./src/Medium.java)** | Medium | Modela a classe `BankAccount` com três construtores encadeados via `this(...)`, geração automática de identificador com `UUID.randomUUID()` e validação de saldo em `deposit()` e `withdraw()`. | *Constructor Chaining* (`this()`), Encapsulamento, Geração de `UUID`, Validação de Estado. |
| **[`src/Hard.java`](./src/Hard.java)** | Hard | Implementa uma classe `Pizza` **100% imutável** (`final` fields e `Collections.unmodifiableList`) construída através de uma classe interna estática `Pizza.Builder` com interface fluente (`withCrust()`, `withSauce()`, `addTopping()`, `build()`). | Imutabilidade Defensiva, *Builder Design Pattern*, *Fluent Interface*, *Static Inner Class*. |

---

## 🚀 Como Compilar e Executar

```powershell
cd Classes_Objetos_e_Construtores/src
javac Easy.java Medium.java Hard.java
java Easy
java Medium
java Hard
```
