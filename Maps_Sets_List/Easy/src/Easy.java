/*
Você tem uma List<String> de nomes com duplicatas.
Use (1) HashSet para remover duplicatas sem ordem,
(2) TreeSet para remover e ordenar alfabeticamente,
(3) LinkedHashSet para remover mantendo a ordem de inserção.
Compare as três saídas e documente quando usar cada uma.

Usar:
HashSet - Remover duplicatas da lista e manter ordem.
TreeSet - Remover duplicatas e reordenar alfabeticamente.
LinkedHashSet - Remover mantendo a ordem de inserção.
Data: 09/05/2026
 */

import java.util.Scanner;
import java.util.*;

public class Easy {
    public static void main(String[] args) {

    // Lista padrão com Duplicatas e afins
     List<String> nomesDuplicados = new ArrayList<>();
     nomesDuplicados.add("Juan");
     nomesDuplicados.add("Pedro");
     nomesDuplicados.add("Maria");
     nomesDuplicados.add("Jose");
     nomesDuplicados.add("André");
     nomesDuplicados.add("Juan");
     nomesDuplicados.add("Pedro");
     nomesDuplicados.add("Maria2");

    System.out.println("----- Bem vindo a Central -----");
        System.out.println("Escolha uma opção:\n" +
                "1- Remover duplicatas da Lista\n" +
                "2- Remover um especifico e as duplicatas e ordenar alfabeticamente\n" +
                "3- remover mantendo a ordem de inserção\n" +
                "4- Mostrar a lista atualizada\n" +
                "5- Sair da Central\n\n" +
                "Lista: " +
                nomesDuplicados);

        //Recebe e passa para o switch a opcao escolhida
        Scanner input = new Scanner(System.in);
        int opcao = input.nextInt();
        input.nextLine();

        switch(opcao) {
            //Metodo 1
            case 1:
                //hashSet para remover duplicatas sem ordem
                Set<String> nomesSemDuplicados = new HashSet<>(nomesDuplicados);
                System.out.println("------------");
                System.out.println("Lista: " + nomesSemDuplicados);
                break;
            //Metodo 2
            case 2:
                //TreeSet para remover e ordenar alfabeticamente
                Set<String> nomesRemoveAndOrdenar = new TreeSet<>(nomesDuplicados);

                //Escolhe o nome para remover, remove, mostra a lista sem o nome
                System.out.println("Escolha um nome para remover: ");
                String nome = input.nextLine();
                nomesRemoveAndOrdenar.remove(nome);
                System.out.println("------------");

                System.out.println("Lista: "+ nomesRemoveAndOrdenar);
                break;
            //Metodo 3
            case 3:
                //LinkedHash remover mantendo a ordem de inserção
                Set<String> nomesRemoveComOrdem = new LinkedHashSet<>(nomesDuplicados);
                System.out.println("Listas: " + nomesRemoveComOrdem);
                break;
            //Metodo 4
            case 4:
                System.out.println(nomesDuplicados);
                break;
            //Metodo Sair menu
            case 5:
                break;
        }
        input.close();
    }
}
