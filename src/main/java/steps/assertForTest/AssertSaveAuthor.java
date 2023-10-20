package steps.assertForTest;

import entity.AuthorTable;
import io.restassured.response.ValidatableResponse;
import models.responsesPositive.ResponseAuthorSave;
import repository.AuthorRepository;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class AssertSaveAuthor {

    public static void verifySaveAuthorResponse(ResponseAuthorSave author) {
        assertNotNull(author.getAuthorId());
        assertTrue(author.getAuthorId() > 0);
        AuthorRepository authorRepository = new AuthorRepository();
        //authorRepository.findAuthorByName(first_name);
        List<AuthorTable> authorList = authorRepository.findAuthorById(author.getAuthorId());
        assertEquals(authorList.get(0).getId(),author.getAuthorId());
    }

    public static void verifyBirthDate(ValidatableResponse allBooks, long id, String birthDate) {
        allBooks.body("author.find{it.id==" + id + "}.birthDate", equalTo(birthDate));
    }
}
