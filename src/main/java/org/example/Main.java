package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        library.addBook(new Book("Мастер и Маргарита","Михаил Булгаков",1940));
        library.addBook(new Book("Собачье сердце","Михаил Булгаков",1968));
        library.addBook(new Book("Записки на манжетах","Михаил Булгаков",1923));
        library.addBook(new Book("Мертвые души","Николай Гоголь",1842));
        library.addBook(new Book("Ревизор","Николай Гоголь",1836));
        library.addBook(new Book("Палата №6","Михаил Булгаков",1892));

        library.addReader(new Reader("Изабелла"));


        System.out.println("Введите имя: ");
        String reader = scanner.nextLine();
        library.addReader(new Reader(reader));

        System.out.println("===========================================================");

        System.out.println("Введите автора: ");
        String author = scanner.nextLine();
        System.out.println("Введите название книги: ");
        String book = scanner.nextLine();
        System.out.println("Введите год издания: ");
        int year = scanner.nextInt();
        library.addBook(new Book(book,author,year));

        System.out.println("===========================================================");
        scanner.close();




    }
}
