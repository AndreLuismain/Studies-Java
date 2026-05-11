/*
Crie uma lista telefônica como TreeMap<String, String> (nome → telefone).
Adicione 10 contatos fora de ordem. Itere e observe que saem em ordem
alfabética. Agora crie uma versão com TreeMap usando comparator reverso
(Comparator.reverseOrder()). Use subMap("D", "M") para filtrar contatos
cujo nome começa entre D e M.
Data: 11/05/2026
 */


//Record Principal
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

//Classe MAIN
public class Easy {
    public static void main(String[] args) {
        //Criando a lista telefônica tipada corretamente como <String, String>
        SortedMap<String, String> lista = new TreeMap<>();

        //Adicionando 10 contatos fora de ordem
        lista.put("Andre", "11-1231232");
        lista.put("Cuan", "11-1231233");
        lista.put("Maria", "11-1231234");
        lista.put("Pedro", "11-1231235");
        lista.put("Darco", "11-123233");
        lista.put("Pedreiro", "11-123123");
        lista.put("Uedreiro", "11-1231239");
        lista.put("Delipe", "11-1231238");
        lista.put("Julia", "11-1231240");
        lista.put("Zeinaldo", "11-1231241");

        //Mostrar lista organizada automaticamente na ordem alfabética natural
        System.out.println("Lista em Ordem Alfabética (Natural):");
        lista.forEach((k, v) -> System.out.println(k + " -> " + v));

        //Lista com ordem invertida
        //Usamos o operador diamante <> e injetamos o comparador
        SortedMap<String, String> listaInvertida = new TreeMap<>(Comparator.reverseOrder());
        //Copiamos todos os dados da primeira lista para cá instantaneamente
        listaInvertida.putAll(lista);

        System.out.println("\nLista em Ordem Invertida (Z a A):");
        listaInvertida.forEach((k, v) -> System.out.println(k + " -> " + v));

        //Filtro para contatos entre D e M
        //Usamos "N" como teto para garantir que nomes iniciados em "M" sejam incluídos
        System.out.println("\nBuscando contatos entre as letras 'D' e 'M':");
        SortedMap<String, String> filtroDeDaM = lista.subMap("D", "N");
        filtroDeDaM.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
