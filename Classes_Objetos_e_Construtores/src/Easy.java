/*
Crie uma classe Book com fields title, author e year.
Implemente um construtor com os três campos,
um construtor que omite o ano (use 0 como padrão),
e um toString() legível.
Instancie dois livros no main e imprima-os.
Data: 04/05/2026
*/


class Book {
    String title;
    String author;
    int year;
    //construtor com ano
    Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
    //Construtor sem ano
    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.year = 0;
    }
    //Para printar formatado
    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", author=" + author + ", year=" + year + '}';
    }
}
//Main
public class Easy {
    public static void main(String[] args) {
        //teste 1
        Book book = new Book("Java Programming", "Java Programming");
        //teste 2
        Book book1 = new Book("Java Programming2", "Java Programming2", 0);
        //printando
        System.out.println(book);
        System.out.println(book1);
    }
}