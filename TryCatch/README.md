# Tratamento de Exceções, AutoCloseable e Escopo Transacional

Módulo dedicado ao gerenciamento seguro de recursos com **`try-with-resources`**, implementação customizada da interface **`AutoCloseable`** para controle de transações (`commit`/`rollback`) e modelagem de hierarquias de exceções **`Checked` vs. `Unchecked`** em domínios financeiros.

---

## Exercícios Implementados (`Easy/src/`)

| Arquivo | Nível | Objetivo e Lógica Implementada | Conceitos Aplicados |
|---|:---:|---|---|
| **[`Easy.java`](./Easy/src/Easy.java)** | Easy | Compara a leitura de arquivos de texto (`mensagem.txt` e `Arquivo1.txt`) via bloco `try-catch-finally` manual versus `try-with-resources` com múltiplos recursos (`FileReader` e `BufferedReader`), evidenciando o fechamento automático em ordem inversa. | `try-with-resources`, `BufferedReader`, Fechamento Determinístico de Recursos I/O. |
| **[`Medium.java`](./Easy/src/Medium.java)** | Medium | Implementa `DatabaseConnection` e `TransactionScope` assinando a interface `AutoCloseable`. Caso o bloco `try` termine sem invocação explícita de `commit()` (por lançamento de exceção), o método `close()` executa automaticamente o **ROLLBACK** da transação. | Padrão RAII / `AutoCloseable`, Escopo de Transação Automático (`Commit` vs. `Rollback`). |
| **[`Hard.java`](./Easy/src/Hard.java)** | Hard | Modela a hierarquia de erros de um gateway de pagamentos: `PaymentException` (*unchecked* base) com as especializações `InsufficientFundsException` (armazenando `missingAmount`), `CardExpiredException` e `FraudSuspectedException`, além de `NetworkException` (*checked*), demonstrando captura ordenada da mais específica para a mais genérica. | Hierarquia de Exceções de Domínio, *Checked* (`Exception`) vs. *Unchecked* (`RuntimeException`). |

---

## Como Compilar e Executar

```powershell
cd TryCatch/Easy/src
javac Easy.java Medium.java Hard.java
java Easy
java Medium
java Hard
```
