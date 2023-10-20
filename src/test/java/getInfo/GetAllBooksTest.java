package getInfo;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.ValidatableResponse;
import jdk.jfr.Description;
import models.responsesPositive.ResponseAuthorSave;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.Specification;
import steps.assertForTest.AssertBooks;
import steps.assertForTest.AssertSaveAuthor;
import utils.DateFormatter;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Epic("GetTests")
@Story("getAllBooks")
public class GetAllBooksTest {

    @DisplayName("Получить список всех книг с новой созданной книгой")
    @Description("Возвращается список всех книг автора соответствующего введенному id, в списке есть созданная книга, статус-код 200")
    @Test
    public void getAllBooksWithNewBookTest() {
        int year = 1800;
        int month = 3;
        int day = 4;
        ResponseAuthorSave author = Specification.reqSpecSaveAuthor(randomAlphabetic(3), randomAlphabetic(3), randomAlphabetic(3), year, month, day, 201);
        long id = author.getAuthorId();
        String birthDate = DateFormatter.formatDate(year,month,day);

        String bookTitle = "BookNewBook";
        Specification.reqSpecSaveBook("BookNewBook", id, 201);

        ValidatableResponse allBooks = Specification.reqSpecGetAllBooksResponse(String.valueOf(id), 200);
        AssertBooks.verifyBodyGetBooks(allBooks, id, bookTitle);
        AssertSaveAuthor.verifyBirthDate(allBooks, id, birthDate);

    }
}
