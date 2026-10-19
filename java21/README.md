# Recursos Modernos do Java 21: Records, Sealed Classes e Pattern Matching

Exploração prática de **Programação Orientada a Dados (Data-Oriented Programming)** no **Java 21 LTS**, combinando **Interfaces Seladas (`sealed` / `permits`)**, **Records** e **Pattern Matching Exaustivo para `switch`** na construção de Tipos Algébricos de Dados (ADTs) e Árvores de Sintaxe Abstrata (AST).

---

## Exercícios Implementados

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`Easy.java`](./Records_Sealed_Classes_Pattern_Matching/src/Easy.java)** | Easy | Modela o tipo mônada/resultado `sealed interface Result<T> permits Success, Failure` para divisão matemática segura sem lançamento de exceções, tratando o retorno via `switch` com *pattern matching*. | *Algebraic Data Types (ADTs)*, `sealed interface`, Tratamento Funcional de Erros. |
| **[`Medium.java`](./Records_Sealed_Classes_Pattern_Matching/src/Medium.java)** | Medium | Define a hierarquia fechada `sealed interface Shape permits Circle, Rectangle, Triangle` e calcula áreas em `describeShape(Shape)` com verificação de exaustividade em tempo de compilação (sem ramo `default`). | Exaustividade de Compilador no `switch`, Desestruturação de `record`. |
| **[`Hard.java`](./Records_Sealed_Classes_Pattern_Matching/src/Hard.java)** | Hard | Implementa um avaliador recursivo (`eval`) e formatador (`prettyPrint`) de uma **Árvore de Sintaxe Abstrata (AST)** para expressões matemáticas (`sealed interface Expr permits Num, Add, Mul, Neg`). | Árvore de Sintaxe Abstrata (AST), Recursão Estrutural com Pattern Matching. |

---

## Como Compilar e Executar

Requer **JDK 21+**:

```powershell
cd java21/Records_Sealed_Classes_Pattern_Matching/src
javac Easy.java Medium.java Hard.java
java Easy
java Medium
java Hard
```
