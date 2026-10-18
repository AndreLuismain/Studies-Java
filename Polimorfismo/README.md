# Polimorfismo, Sobrecarga, Generics Bounded e Dynamic Dispatch

Estudo prático de **Polimorfismo de Subtipagem (Runtime/Dynamic Dispatch)**, **Polimorfismo Ad-hoc (Method Overloading)** e **Polimorfismo Paramétrico (`<T extends Number>`)**.

---

## 📋 Exercícios Implementados

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`Easy.java`](./Polimorfismo1/src/Easy.java)** | Easy | Define a superclasse `Animal` e as subclasses `Dog`, `Cat` e `Duck` sobrescrevendo `speak()`, iterando sobre uma `List<Animal>` heterogênea sem alterar o código cliente ao introduzir novas espécies. | Sobrescrita (`@Override`), *Upcasting*, Princípio Aberto-Fechado (OCP). |
| **[`Medium.java`](./Polimorfismo1/src/Medium.java)** | Medium | Implementa a classe `Calculator` com sobrecarga de `calculate()` para `int`, `double`, `String` e `List<Integer>`, refatorando as operações numéricas com *Bounded Type Parameters* (`<T extends Number>`). | *Method Overloading*, *Bounded Generics* (`<T extends Number>`), Reuso de Código. |
| **[`Hard.java`](./Polimorfismo1/src/Hard.java)** | Hard | Projetor gráfico (`SceneRenderer`) que renderiza `Circle`, `Rectangle` e `Polygon` sobre um `Canvas` via chamada polimórfica `shape.render(canvas)`, eliminando cadeias procedurais de `if-else` e `instanceof`. | *Dynamic Dispatch*, Eliminação de Type-Checking Manual (`instanceof`), Design Extensível. |

---

## 🚀 Como Compilar e Executar

```powershell
cd Polimorfismo/Polimorfismo1/src
javac Easy.java Medium.java Hard.java
java Easy
java Medium
java Hard
```
