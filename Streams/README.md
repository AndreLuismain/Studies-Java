# Programação Funcional em Java: Streams, Optional<T> e Method References

Diretório dedicado aos recursos funcionais do Java (`java.util.stream`, `java.util.Optional`, `java.util.function`), dividido em três submódulos práticos.

---

## Índice de Subpastas e Exercícios

### 1. [`filter_map_collect/`](./filter_map_collect/) — Pipelines de Transformação e Agregação
| Arquivo | Nível | Objetivo e Lógica Implementada |
|---|:---:|---|
| **[`Easy.java`](./filter_map_collect/Easy.java)** | Easy | Opera sobre `List<Integer>` (1 a 20) aplicando filtragem de pares, elevação ao quadrado, soma com `reduce(0, Integer::sum)`, busca de curto-circuito (`findFirst`) e predicados (`anyMatch`, `allMatch`). |
| **[`Medium.java`](./filter_map_collect/Medium.java)** | Medium | Analisa um lote de 20+ registros `record Transaction` para extrair via Streams: maior transação por categoria, total gasto por categoria, transações do último mês ordenadas decrescentemente e *Top-3* categorias. |
| **[`Hard.java`](./filter_map_collect/Hard.java)** | Hard | Pipeline único de Processamento de Linguagem Natural (NLP básico) que aplica `flatMap` em linhas de texto, normaliza caracteres, remove *stop words*, agrupa e conta ocorrências (`groupingBy` + `counting`) e retorna as *Top-10* palavras mais frequentes. |

### 2. [`Optional_T/`](./Optional_T/) — Prevenção de `NullPointerException` e Encadeamento Monádico
| Arquivo | Nível | Objetivo e Lógica Implementada |
|---|:---:|---|
| **[`Easy1.java`](./Optional_T/Easy1.java)** | Easy | Refatora buscas que retornavam `null` para `Optional<User1>`, contrastando o anti-pattern `isPresent() + get()` com o uso idiomático de `orElse(...)` e `orElseThrow(...)`. |
| **[`ServiçoDeLocalização.java`](./Optional_T/ServiçoDeLocalização.java)** | Medium | Navegação segura entre modelos aninhados (`findUser(id)` $\rightarrow$ `getAddress(user)` $\rightarrow$ `getCity(address)`) utilizando exclusivamente `flatMap(this::getAddress).flatMap(this::getCity).orElse("Cidade desconhecida")`. |
| **[`ConfigReader.java`](./Optional_T/ConfigReader.java)** | Hard | Leitor hierárquico de configurações (Variáveis de Ambiente $\rightarrow$ Properties Local $\rightarrow$ Defaults) encadeado de forma *lazy* com `optional.or(() -> ...)` e `getRequired(key)` lançando `MissingConfigException`. |

### 3. [`Method References/`](./Method%20References/) — Referências de Método e Composição de Funções
| Arquivo | Nível | Objetivo e Lógica Implementada |
|---|:---:|---|
| **[`Easy3.java`](./Method%20References/Easy3.java)** | Easy | Refatoração de expressões lambda para referências de método (`String::toUpperCase`, `System.out::println`, `String::compareTo`, `Predicate.not(String::isEmpty)`). |
| **[`Medium3.java`](./Method%20References/Medium3.java)** | Medium | Instanciação funcional de objetos com `BiFunction<String, String, User> factory = User::new` e `Supplier<List<String>> listFactory = ArrayList::new`. |
| **[`Hard3.java`](./Method%20References/Hard3.java)** | Hard | Motor genérico `TextPipeline` que compõe dinamicamente uma lista arbitrária de `Function<String, String>` (`trim`, `lowercase`, `removeSpecialChars`, `truncate(n)`) utilizando `stream().reduce(Function.identity(), Function::andThen)`. |

---

## Como Compilar e Executar

```powershell
# Exemplo: Executar o pipeline NLP (Hard.java) em filter_map_collect
cd Streams/filter_map_collect
javac Easy.java Medium.java Hard.java
java Hard
```
