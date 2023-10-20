package getInfo;

import entity.AuthorTable;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.responsesPositive.ResponseAuthorSave;
import models.responsesPositive.ResponseGetAllBooksXML;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.Specification;
import steps.assertForTest.AssertGetBooksXML;
import utils.DateFormatter;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Epic("GetTests")
@Story("getAllBooksXML")
public class GetAllBooksXMLTest {


    @DisplayName("Получить список всех книг в XML формате")
    @Description("В ответ приходит список всех книг автора, статус-код 200")
    @Disabled
    @Test
    public void getAllBooksXMLTest(){
        int year = 1800;
        int month = 3;
        int day = 4;
        ResponseAuthorSave author1 = Specification.reqSpecSaveAuthor(randomAlphabetic(3), randomAlphabetic(3), randomAlphabetic(3), year, month, day, 201);
        long id = author1.getAuthorId();
        String birthDate = DateFormatter.formatDate(year,month,day);

        String bookTitle = "BookNewBook";
        Specification.reqSpecSaveBook(bookTitle, id, 201);

        ResponseGetAllBooksXML listBooksXML = Specification.reqSpecGetAllBooksXML(id, 200);
        AuthorTable author = listBooksXML.getBooks().get(0).getAuthor();

        AssertGetBooksXML.verifyGetAllBooksXMLResponse(listBooksXML, bookTitle, author, birthDate);
    }
}
