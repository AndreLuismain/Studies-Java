/*
Crie um sistema de fila de tarefas (Task record com
priority e description). Use Deque<Task> queue = new LinkedList<>().
Implemente: addUrgent() que insere no início,
addNormal() que insere no fim, processNext() que remove do início.
Simule 10 tarefas sendo adicionadas e processadas em ordem.
Data: 11/05/2026
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

record Task (String description, int priority){}

//Classe MAIN
public class Medium {
    //Metodo que insere no início
    public static void addUrgent(Deque<Task> queue, Task task) {
        queue.addFirst(task);
        System.out.println("[URGENTE] Adicionada no início: " + task.description());
    }

    //Metodo que insere no fim
    public static void addNormal(Deque<Task> queue, Task task) {
        queue.addLast(task);
        System.out.println("[NORMAL] Adicionada no fim: " + task.description());
    }

    //Metodo que remove do início
    public static void processNext(Deque<Task> queue) {
        if (!queue.isEmpty()) {
            Task processed = queue.removeFirst();
            System.out.println(">> Processando: " + processed.description() + " (Prioridade: " + processed.priority() + ")");
        } else {
            System.out.println("Nenhuma tarefa para processar.");
        }
    }
    //Main
    public static void main(String[] args) {
        Deque<Task> queue = new LinkedList<>();

        System.out.println("--- SIMULAÇÃO DE ADIÇÃO DE 10 TAREFAS (11/05/2026) ---\n");

        // Simulando a chegada de 10 tarefas (algumas normais, outras urgentes que furam a fila)
        addNormal(queue, new Task("Fazer login no sistema", 5));
        addNormal(queue, new Task("Carregar página inicial", 5));
        addUrgent(queue, new Task("Corrigir queda do servidor", 1)); // Fura fila
        addNormal(queue, new Task("Atualizar foto de perfil", 8));
        addUrgent(queue, new Task("Processar pagamento travado", 2)); // Fura fila
        addNormal(queue, new Task("Enviar e-mail de marketing", 9));
        addNormal(queue, new Task("Gerar relatório mensal", 6));
        addUrgent(queue, new Task("Bloquear ataque hacker", 1)); // Fura fila para o topo
        addNormal(queue, new Task("Limpar cache do navegador", 10));
        addNormal(queue, new Task("Fazer backup diário", 7));

        System.out.println("\nFila montada: " + queue.size() + " tarefas.\n");
        System.out.println("PROCESSANDO AS TAREFAS EM ORDEM");

        //Processando as 10 tarefas
        while (!queue.isEmpty()) {
            processNext(queue);
        }

        System.out.println("\nTodas as tarefas foram processadas com sucesso!");
    }
}
