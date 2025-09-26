import org.example.Book;
import org.example.Library;
import org.example.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;
    private Reader reader1;
    private Reader reader2;
    private Reader reader3;

    @BeforeEach // для создания тестовых данных
    public void initData() {
        library = new Library();

        book1 = new Book("Мастер и Маргарита","Михаил Булгаков",1940);
        book2 = new Book("Собачье сердце","Михаил Булгаков",1968);
        book3 = new Book("Палата №6","Михаил Булгаков",1892);

        reader1 = new Reader("Маргарита");
        reader2 = new Reader("Сулейман");
        reader3 = new Reader("Юрчи");
    }

    @Test // аннотации пишутся над методом
    public void addReaderTestSuccess() {
        library.addReader(reader1);
        assertEquals(1,library.getReaders().size());
        library.addReader(reader2);
        assertEquals(2,library.getReaders().size());
        library.addReader(reader3);
        assertEquals(3,library.getReaders().size());
    }

    @Test
    public void addReaderTestFail() {
        library.addReader(null);
        assertEquals(0,library.getReaders().size());
    }

    @Test
    public void addBookTestSuccess() {
        library.addBook(book1);
        assertEquals(1,library.getBooks().size());
        library.addBook(book2);
        assertEquals(2,library.getBooks().size());
        library.addBook(book3);
        assertEquals(3,library.getBooks().size());

    }
    @Test
    public void addBookTestFail() {
        library.addBook(null);
        assertEquals(0,library.getBooks().size());
    }

    @Test
    public void cannotIssueAlreadyIssuedBook() {
        library.addBook(book1);
        library.addReader(reader1);
        library.addReader(reader2);
        assertTrue(library.issueBook("Мастер и Маргарита",reader1),"Книга1 должна быть выдана."); //первым идет выполнение условия, вторым текст, если условие не выполнено
        assertFalse(library.issueBook("Мастер и Маргарита", reader2), "Книга1 уже выдана другому пользователю.");
        assertTrue(reader1.getBook().contains(book1), "Книга1 должна появиться у читателя1");
        assertFalse(reader2.getBook().contains(book1), "Книги1 не должно быть у читателя2");
    }

    @Test
    public void issueBookNotFound() {
        library.addReader((reader1));
        library.addBook(book1);
        assertFalse(library.issueBook("Идиот", reader1), "Несуществующая книга была найдена.");
        assertFalse(library.issueBook("Мастер и Маргарита", reader2), "Несуществующему читателю выдали книгу.");
        assertFalse(library.issueBook(null,reader1), "некорректная обработка null для книги.");
        assertFalse(library.issueBook("Мастер и Маргарита",null), "некорректная обработка null для читателя.");
        assertFalse(library.issueBook(null,null), "некорректная обработка null для книги и читателя.");
        assertFalse(library.issueBook("Идиот", reader2), "Несуществующая книга выдана несуществующему читателю.");

    }


    @Test
    public void returnBookSuccess(){ //доделать тесты
        library.addReader((reader1));
        library.addReader((reader2));
        library.addReader((reader3));
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.issueBook("Мастер и Маргарита",reader1);
        library.issueBook("Собачье сердце",reader2);
        assertTrue(library.returnBook(book1,reader1),"Книга1 не сдана читателем1");
        library.issueBook("Мастер и Маргарита",reader1);
        assertFalse(library.returnBook(book1,reader2),"Книга1, выданная читателю1, сдана читателем2");
        assertFalse(library.returnBook(book2,reader1),"Книга2 сдана другим читателем");
        assertFalse(library.returnBook(book3,reader1),"Невыданная Книга3 сдана читателем1");

    }

    @Test
    public void cannotReturnAlreadyReturnedBook() {
        library.addReader((reader1));
        library.addReader((reader2));
        library.addBook(book1);
        library.addBook(book2);
        library.issueBook("Мастер и Маргарита",reader1);
        library.issueBook("Собачье сердце",reader2);
        library.returnBook(book1,reader1);
        assertFalse(library.returnBook(book1,reader1),"Возвращенная книга1 сдана читателем1");
        assertFalse(library.returnBook(book1,reader2),"Возвращенная книга1 сдана читателем2, которому и не принадлежала");

    }
    @Test
    public void returnBookFailed() {
        library.addReader((reader1));
        library.addReader((reader2));
        library.addBook(book1);
        library.addBook(book2);
        library.issueBook("Мастер и Маргарита",reader1);
        library.issueBook("Собачье сердце",reader2);
        assertFalse(library.returnBook(null,reader1),"некорректная обработка null для книги.");
        assertFalse(library.returnBook(book2,null),"некорректная обработка null для читателя.");
        assertFalse(library.returnBook(book3,reader1),"Несуществующая книга в этой библиотеке возвращена читателем 1.");
        assertFalse(library.returnBook(book1,reader3),"Книга1 возвращена несуществующим в этой библиотеке читателем 3.");
    }

}
