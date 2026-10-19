# Estruturas Lineares e Cache LRU: ArrayList, LinkedList e Deque

Análise experimental de complexidade assintótica entre vetores dinâmicos (`ArrayList`) e listas duplamente encadeadas (`LinkedList`), gerenciamento de filas de prioridade com `Deque` e implementação do zero de um **Cache LRU (*Least Recently Used*)** em tempo constante $O(1)$.

---

## Exercícios Implementados (`src/`)

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`src/Easy.java`](./src/Easy.java)** | Easy | Benchmark empírico inserindo `50.000` elementos no índice `0` e acessando o elemento central (`size / 2`) em `ArrayList` vs. `LinkedList`, demonstrando o custo de deslocamento de memória contígua ($O(n)$ vs. $O(1)$) e acesso aleatório ($O(1)$ vs. $O(n)$). | Análise de Performance, Localidade de Cache, `ArrayList` vs. `LinkedList`. |
| **[`src/Medium.java`](./src/Medium.java)** | Medium | Escalonador de tarefas (`record Task`) baseado em `Deque<Task>` (`LinkedList`), suportando inserção prioritária na cabeça (`addFirst` para tarefas urgentes), inserção no fim (`addLast` para tarefas normais) e consumo FIFO (`pollFirst`). | `Deque` (*Double-Ended Queue*), Manipulação de Filas Prioritárias. |
| **[`src/LRUCache.java`](./src/LRUCache.java)** | Hard | Implementação completa de `LRUCache<K, V>` combinando `HashMap<K, Node<K, V>>` para busca em $O(1)$ e uma **Lista Duplamente Encadeada manual** (`head`/`tail`) para promover itens acessados recentemente e descartar (*evict*) o menos utilizado quando a capacidade é atingida. | *LRU Cache Eviction Policy*, Ponteiros Duplamente Encadeados + Tabela Hash em $O(1)$. |

---

## Como Compilar e Executar

```powershell
cd ArrayList_vs_LinkedList/src
javac Easy.java Medium.java LRUCache.java
java Easy
java Medium
java LRUCache
```
