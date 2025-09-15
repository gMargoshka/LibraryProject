package org.example;

import java.util.ArrayList;
import java.util.List;


public class Library {
    private List<Reader> readers;
    private List<Book> books;

    public Library() {
        this.readers = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    public void addReader(Reader reader) {
        if (reader != null) {
            readers.add(reader);
        }
        //System.out.println("Читатель добавлен в нашу библиотеку.");
    }
    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
        //System.out.println("Книга добавлена в нашу библиотеку.");
    }

    public boolean issueBook(String title, Reader reader) {
        if (reader == null || title == null) {
            return false;
        }
        Book book = checkBookByTitle(title);
        Reader libraryReader = checkReaderByName(reader.getName());
        if (book != null && libraryReader != null && book.getStatus() == BookStatus.FREE) {
            book.setStatus(BookStatus.IN_PROGRESS);
            reader.addBook(book);
            System.out.println("Книга выдана читателю!");
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book, Reader reader) {
        Reader libraryReader = checkReaderByName(reader.getName());
        Book libraryBook = checkBook(book);
        if (libraryBook != null && libraryReader != null) {
            book.setStatus(BookStatus.FREE);
            reader.deleteBook(libraryBook);
            System.out.println("Спасибо, что сдали книгу!");
            return true;
        }
        return false;
    }
    public List<Reader> getReaders(){
        return readers;
    }
    public List<Book> getBooks(){
        return books;
    }

    public Book checkBookByTitle(String title) {
        for (Book book: books) {
            if (title != null && title.equals(book.getTitle())) {
                return book;
            }
        }
        return null;
    }

    public Book checkBook(Book book) {
        for (Book libraryBook : books) {
            if (book.equals(libraryBook)) {
                return libraryBook;
            }
        }
        return null;
    }

    public Reader checkReaderByName(String name) {
        for (Reader libraryReader : readers) {
            if (name.equals(libraryReader.getName())) {
                return libraryReader;
            }
        }
        return null;
    }



}
