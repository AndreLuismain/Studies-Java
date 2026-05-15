/*
Crie record User(String name, String email). Dada uma List<String[]>
onde cada array tem [name, email], use map(arr -> new User(arr[0],
arr[1])) e depois refatore para method reference usando
BiFunction<String,String,User> factory = User::new. Demonstre também
Supplier<List<String>> listFactory = ArrayList::new.

Data: 15/05/2026
 */


import java.util.List;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;

record User(String name, String email) {}

public class Medium3 {
    public static void main(String[] args) {
        // List<String[]>
        List<String[]> usuariosArrays = new ArrayList<>();
        usuariosArrays.add(new String[]{"Alice", "alice@email.com"});
        usuariosArrays.add(new String[]{"Bob", "bob@email.com"});

        // Usando map com lambda
        List<User> usuariosComLambda = usuariosArrays.stream()
                .map(arr -> new User(arr[0], arr[1]))
                .toList();

        System.out.println("Usando Lambda: " + usuariosComLambda);

        // Refatorado com Method Reference e BiFunction
        BiFunction<String, String, User> factory = User::new;

        List<User> usuariosComBiFunction = usuariosArrays.stream()
                .map(arr -> factory.apply(arr[0], arr[1])) // Aplicando a BiFunction
                .toList();

        System.out.println("Usando BiFunction: " + usuariosComBiFunction);

        // Demonstrando o Supplier
        Supplier<List<String>> listFactory = ArrayList::new;

        // Usando o .get() para efetivamente instanciar uma nova ArrayList
        List<String> novaLista = listFactory.get();
        novaLista.add("Teste Supplier");
        System.out.println("Lista gerada pelo Supplier: " + novaLista);
    }
}
