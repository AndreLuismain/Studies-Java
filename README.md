# Java 21 Core, OOP & Functional Programming Studies (`Studies-Java`)

<div align="center">

[![Java 21](https://img.shields.io/badge/Java-21%20LTS-ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![OOP](https://img.shields.io/badge/Paradigma-POO%20%26%20Funcional-blue.svg?style=for-the-badge)]()
[![ICMC-USP](https://img.shields.io/badge/ICMC--USP-Sistemas%20de%20Informação-red.svg?style=for-the-badge)](https://www.icmc.usp.br/)

</div>

Repositório de laboratório prático e trilha estruturada de aprofundamento em **Java 21 LTS**, desenvolvido por **André Luís** (Sistemas de Informação — **ICMC-USP**).

Cada módulo temático aborda um pilar da linguagem ou de estruturas de dados e está organizado em **três níveis progressivos de complexidade (`Easy`, `Medium`, `Hard`)**, indo desde a mecânica sintática até a implementação de padrões de projeto (*Builder*, *Strategy*, *Template Method*, *Combinator*), estruturas de dados de baixa latência (*LRU Cache* $O(1)$, *Autocomplete* em árvore rubro-negra) e avaliação de Árvores de Sintaxe Abstrata (AST) com *Pattern Matching*.

---

## 🧭 Catálogo Completo e Índice de Navegação

| Módulo / Diretório | Linguagem | Conceitos de Engenharia Aplicados | Exercícios (`Easy` / `Medium` / `Hard`) | Status |
|---|---|---|---|---|
| **[`Classes_Objetos_e_Construtores/`](./Classes_Objetos_e_Construtores/)** | Java 21 | Sobrecarga e encadeamento de construtores (`this()`), geração de `UUID`, imutabilidade defensiva (`final` + `unmodifiableList`) e **Builder Pattern** fluente. | `Book`, `BankAccount`, `Pizza.Builder` | ✅ Concluído |
| **[`encapsulamento/`](./encapsulamento/)** | Java 21 | Proteção de invariantes em *setters* (*fail-fast*), comparativo `class` vs. `record`, construtores compactos e Value Object monetário (`Money` com `BigDecimal`). | `Point`, `Person`, `Money` | ✅ Concluído |
| **[`Herança_vs_Composição/`](./Herança_vs_Composição/)** | Java 21 | Limites do relacionamento *Is-A*, delegação por interfaces (*Has-A*), **Strategy Pattern** e injeção de dependências em tempo de execução (`Logger` + `Formatter` + `Output`). | `RobotDog`, `AmphibiousVehicle`, `LoggerBuilder` | ✅ Concluído |
| **[`Polimorfismo/`](./Polimorfismo/)** | Java 21 | *Dynamic Dispatch* (`@Override`), Princípio Aberto-Fechado (OCP), sobrecarga vs. *Bounded Generics* (`<T extends Number>`) e eliminação de `instanceof` em renderização (`SceneRenderer`). | `Animal`, `Calculator`, `SceneRenderer` | ✅ Concluído |
| **[`Interfaces_e_Classes_Abstratas/`](./Interfaces_e_Classes_Abstratas/)** | Java 21 | Polimorfismo entre domínios distintos (`Drawable`), **Template Method Pattern** em classes abstratas (`Shape`) e **Combinator Pattern** com `@FunctionalInterface Validator<T>` (`and`, `or`, `negate`). | `Drawable`, `Shape`, `Validator<T>` | ✅ Concluído |
| **[`java21/`](./java21/)** | Java 21 | **Data-Oriented Programming**: `sealed interface` (`permits`), `record`, *Pattern Matching for `switch`* exaustivo e avaliador recursivo de **Árvore de Sintaxe Abstrata (AST)** (`Expr`). | `Result<T>`, `Shape`, `Expr (AST)` | ✅ Concluído |
| **[`Maps_Sets_List/`](./Maps_Sets_List/)** | Java 21 | Comparativo `HashSet` ($O(1)$) vs. `TreeSet` ($O(\log n)$) vs. `LinkedHashSet`, contagem de frequência via `Map.merge` e agregações analíticas com `Collectors.groupingBy`, `averagingDouble` e `maxBy`. | `Set Comparison`, `Word Counter`, `Student Analytics` | ✅ Concluído |
| **[`ArrayList_vs_LinkedList/`](./ArrayList_vs_LinkedList/)** | Java 21 | Benchmark de localidade de memória e deslocamento ($50.000$ inserções), fila de prioridade com `Deque<Task>` e implementação do zero de **LRU Cache $O(1)$** (`HashMap` + Lista Duplamente Encadeada). | `Benchmark`, `Task Deque`, `LRUCache<K,V>` | ✅ Concluído |
| **[`HashMap_e_Variantes/`](./HashMap_e_Variantes/)** | Java 21 | Recorte ordenado com `TreeMap.subMap()`, cache LRU conciso via `LinkedHashMap(accessOrder=true)` + `removeEldestEntry()` e motor de **Autocomplete por prefixo** ($10.000$ palavras). | `Phonebook`, `SimpleCache<K,V>`, `Autocomplete` | ✅ Concluído |
| **[`Streams/`](./Streams/)** | Java 21 | Pipelines `filter`/`map`/`flatMap`/`reduce`, ranking NLP de palavras sem *stop words*, encadeamento monádico seguro com `Optional<T>` (`flatMap`, `or`, `orElseThrow`) e composição funcional (`Function::andThen`). | Submódulos: [`filter_map_collect`](./Streams/filter_map_collect/), [`Optional_T`](./Streams/Optional_T/), [`Method References`](./Streams/Method%20References/) | ✅ Concluído |
| **[`TryCatch/`](./TryCatch/)** | Java 21 | Fechamento determinístico em ordem inversa com `try-with-resources`, padrão RAII com `AutoCloseable` (`TransactionScope` com *auto-rollback*) e hierarquia de exceções `Checked` vs. `Unchecked`. | `File I/O`, `TransactionScope`, `PaymentService` | ✅ Concluído |
| **[`Exercise_POO/`](./Exercise_POO/)** | Java 21 | Exercícios de laboratório acadêmico sobre herança (`extends`), sobrescrita de contratos de igualdade (`equals` e `toString`) e jogo interativo em console com tratamento de `InputMismatchException`. | `B.java`, `GuessingGame.java` | ✅ Concluído |

---

## 🛠️ Requisitos e Como Executar Qualquer Exercício

* **JDK 21 LTS** instalado (`java --version`).

Todos os exercícios são independentes e possuem método `main` autocontido com casos de teste demonstrativos. Para compilar e executar qualquer arquivo:

```powershell
# Exemplo 1: Avaliador de Árvore de Sintaxe Abstrata (Java 21 Pattern Matching)
cd java21/Records_Sealed_Classes_Pattern_Matching/src
javac Hard.java
java Hard

# Exemplo 2: Implementação de LRU Cache em O(1)
cd ArrayList_vs_LinkedList/src
javac LRUCache.java
java LRUCache
```

---

## 👤 Autor

**André Luís da Silva Souza**  
Graduando em Sistemas de Informação — **ICMC · Universidade de São Paulo (USP)**  
- GitHub: [@AndreLuismain](https://github.com/AndreLuismain)
- Email: [andreluis.souza@usp.br](mailto:andreluis.souza@usp.br)
