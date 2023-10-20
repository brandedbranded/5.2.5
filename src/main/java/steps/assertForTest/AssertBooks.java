package steps.assertForTest;

import entity.BookTable;
import io.restassured.response.ValidatableResponse;
import repository.BookRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;


public class AssertBooks {

    public static void verifyBodyGetBooks(ValidatableResponse allBooks, long id, String bookTitle) {
        LocalDate lc = LocalDate.now(ZoneId.of("UTC"));
        allBooks.assertThat()
                .body("find{it.bookTitle=='" + bookTitle + "'}.bookTitle", equalTo(bookTitle))
                .body("author.find{it.id==" + id + "}.id", equalTo((int) id));
        assertNotNull(allBooks.extract().jsonPath().getString("updated"));
        assertTrue(allBooks.extract().jsonPath().getString("updated").contains(lc.toString()));

        BookRepository bookRepository = new BookRepository();
        List<BookTable> bookList = bookRepository.findBookByTitle(bookTitle);
        assertEquals(bookTitle, bookList.get(0).getBookTitle());
    }
}
