/*
Crie uma classe Person com private String name, private int age, private String email.
No setter de name: rejeite nulo/vazio. No setter de age: rejeite < 0 ou > 150.
No setter de email: valide que contém "@". Lance IllegalArgumentException com mensagem clara em cada caso.
05/05/2026
 */


//classe Person
class Person{
    private String name;
    private int age;
    private String email;

    //Construtor
    public Person(String name, int age, String email) {
        setName(name);
        setAge(age);
        setEmail(email);
    }
    //Setters para analise de entrada e cuspir erro especifico(Erro 1)
    public String getName() {
        return name;
    }
    //Erro 2
    public int getAge() {
        return age;
    }
    //Erro 3
    public String getEmail() {
        return email;
    }

    public void setAge(int age) {
        // Adicionado o > 150 que faltava
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Erro: A idade deve estar entre 0 e 150.");
        }
        this.age = age;
    }

    public void setName(String name) {
        // Usa .isEmpty() em vez de == "". (O .trim() ajuda a ignorar espaços em branco "   ")
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O nome não pode ser nulo ou vazio.");
        }
        this.name = name; // Se passou no if, salva o dado.
    }

    public void setEmail(String email) {
        // Valida se não é nulo antes do contains para evitar NullPointerException
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Erro: O email fornecido é inválido.");
        }
        this.email = email;
    }
}

//Classe Main
public class Medium {
    public static void main(String[] args) {
        System.out.println("Criando a Pessoa 1...");
        // Vai dar certo!
        Person pessoa1 = new Person("João", 18, "joao@gmail.com");
        System.out.println("Pessoa 1 criada com sucesso: " + pessoa1.getName());

        System.out.println("\nCriando a Pessoa 2...");
        // Isso aqui vai fazer o programa "explodir" logo de cara e lançar a IllegalArgumentException,
        // pois o email não tem arroba. A linha debaixo do println nem chegará a ser executada.
        Person pessoa2 = new Person("Maria", 19, "emailSemArroba");

        System.out.println("Pessoa 2 criada com sucesso: " + pessoa2.getName());
    }
}
