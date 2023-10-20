package hibernate;

import entity.BookTable;
import org.junit.jupiter.api.Test;
import repository.BookRepository;

import java.util.List;

import static steps.assertForTest.AssertForSQL.sizeVerification;

public class BookTest {
    @Test
    public void insertNewBookTest() {
        BookRepository bookRepository = new BookRepository();
        long authorId = 2;
        String bookSanctuary = "Sanctuary" ;
        String bookCemetery = "Cemetery" ;

        bookRepository.deleteAll();

        bookRepository.insertBook(bookCemetery, authorId);
        bookRepository.insertBook(bookSanctuary, authorId);

        List<BookTable> listOfBooks = bookRepository.findAll();
        sizeVerification(2, listOfBooks);
        System.out.println("Две созданные книги: " + listOfBooks);

        List<BookTable> sanctuaryBook = bookRepository.findBookByTitle(bookSanctuary);
        sizeVerification(1, sanctuaryBook);
        System.out.println("Найденная книга по названию " + bookSanctuary + " : " + sanctuaryBook);

        bookRepository.deleteBookByTitle(bookSanctuary);
        List<BookTable> cemeteryBook = bookRepository.findBookByTitle(bookCemetery);
        sizeVerification(1, cemeteryBook);
        System.out.println("Найденная книга по названию " + bookCemetery + " после удаления другой книги: " + cemeteryBook);
    }
}
