package org.example;

import java.util.ArrayList;
import java.util.List;

public class Reader {
    private String name;
    private List<Book> books;

    public Reader (String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBook() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
    public void deleteBook(Book book) {
        this.books.remove(book);
    }


}
