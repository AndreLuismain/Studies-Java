# Interfaces, Classes Abstratas, Template Method e Composição Funcional

Módulo dedicado às diferenças arquiteturais entre **Interfaces** e **Classes Abstratas**, demonstrando o padrão **Template Method** e a composição funcional de validadores genéricos via *Default Methods*.

---

## 📋 Exercícios Implementados

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`Easy.java`](./untitled/src/Easy.java)** | Easy | Define o contrato `Drawable` implementado tanto por formas geométricas (`Circle`, `Rectangle`, `Triangle`) quanto por entidades de domínios distintos (`Dog`), processados uniformemente por `drawAll(List<Drawable>)`. | Contratos de Interface, Polimorfismo entre Domínios Não-Relacionados. |
| **[`Medium.java`](./untitled/src/Medium.java)** | Medium | Implementa a classe abstrata `Shape` com estado compartilhado (`color`), método abstrato `calculateArea()` e método concreto `describe()` que invoca `calculateArea()` internamente. | Classes Abstratas, *Template Method Pattern*, Reuso de Estado e Comportamento. |
| **[`Hard.java`](./untitled/src/Hard.java)** | Hard | Cria a interface funcional `@FunctionalInterface Validator<T>` equipada com *default methods* combinadores (`and()`, `or()`, `negate()`), compondo pipelines de validação de strings (`notBlank.and(minLength(3)).and(emailFormat)`). | `@FunctionalInterface`, *Default Methods* em Interfaces, *Combinator Pattern*, Lambdas. |

---

## 🚀 Como Compilar e Executar

```powershell
cd Interfaces_e_Classes_Abstratas/untitled/src
javac Easy.java Medium.java Hard.java
java Easy
java Medium
java Hard
```
