# Coleções Java: Maps, Sets, Lists e Agrupamentos Analíticos

Estudo comparativo das implementações de conjuntos (`Set`), mapas (`Map`) e coletores de agrupamento (`Collectors.groupingBy`) na biblioteca padrão do Java.

---

## 📋 Exercícios Implementados

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`Easy.java`](./Easy/src/Easy.java)** | Easy | Remove duplicatas de uma `List<String>` comparando o comportamento de `HashSet` ($O(1)$ sem garantia de ordem), `TreeSet` ($O(\log n)$ ordenado alfabeticamente) e `LinkedHashSet` (preservação da ordem de inserção). | `HashSet`, `TreeSet`, `LinkedHashSet`, Complexidade de Estruturas de Dados. |
| **[`Medium.java`](./Easy/src/Medium.java)** | Medium | Contador de frequência de palavras sobre texto higienizado (case-insensitive e sem pontuação) utilizando `getOrDefault()` e `map.merge(word, 1, Integer::sum)`, extraindo o *Top-5* termos mais frequentes. | `HashMap`, `Map.merge`, Method Reference `Integer::sum`, Ordenação por `Map.Entry.comparingByValue`. |
| **[`Hard.java`](./Easy/src/Hard.java)** | Hard | Processa uma base de mais de 15 registros `record Student(String name, String grade, double score)` em pipelines únicas de Stream para: (1) agrupar alunos por conceito, (2) calcular média de nota por conceito (`averagingDouble`) e (3) localizar o melhor aluno de cada conceito (`maxBy`). | `Collectors.groupingBy`, *Downstream Collectors* (`averagingDouble`, `maxBy`), `Optional`. |

---

## 🚀 Como Compilar e Executar

```powershell
cd Maps_Sets_List/Easy/src
javac Easy.java Medium.java Hard.java
java Easy
java Medium
java Hard
```
