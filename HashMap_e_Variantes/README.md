# HashMap, LinkedHashMap e TreeMap (Busca por Prefixo & Cache)

Exploração avançada de tabelas de dispersão e árvores rubro-negras (`TreeMap` / `SortedMap`) no Java, incluindo janelas de intervalo (`subMap`), cache LRU nativo via `LinkedHashMap` e motor de *Autocomplete* por prefixos.

---

## 📋 Exercícios Implementados (`src/`)

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`src/Easy.java`](./src/Easy.java)** | Easy | Agenda telefônica construída sobre `TreeMap<String, String>` demonstrando ordenação natural, ordenação reversa (`Comparator.reverseOrder()`) e recorte de faixa alfabética eficiente com `subMap("D", "M")`. | Árvore Rubro-Negra (`TreeMap`), `SortedMap.subMap`, `Comparator`. |
| **[`src/Medium.java`](./src/Medium.java)** | Medium | Implementa `SimpleCache<K, V> extends LinkedHashMap<K, V>` configurado com `accessOrder = true` e sobrescrita de `removeEldestEntry()`, obtendo um Cache LRU funcional em poucas linhas de código. | `LinkedHashMap` em Ordem de Acesso, Sobrescrita de `removeEldestEntry`. |
| **[`src/Hard.java`](./src/Hard.java)** | Hard | Motor de *Autocomplete* indexado em `TreeMap` que recupera sugestões por prefixo via `subMap(prefix, prefix + Character.MAX_VALUE)` e realiza benchmark comparativo contra busca linear (`List.stream().filter(...)`) em um vocabulário de 10.000 palavras. | Busca Logarítmica por Prefixo em Árvores Ordenadas vs. Busca Linear $O(n)$. |

---

## 🚀 Como Compilar e Executar

```powershell
cd HashMap_e_Variantes/src
javac Easy.java Medium.java Hard.java
java Easy
java Medium
java Hard
```
