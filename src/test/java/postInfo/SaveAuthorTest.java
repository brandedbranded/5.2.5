package postInfo;


import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import models.responsesPositive.ResponseAuthorSave;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.assertForTest.AssertSaveAuthor;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static steps.Specification.reqSpecSaveAuthor;

@Epic("PostTests")
@Story("saveNewAuthor")
public class SaveAuthorTest {

    @DisplayName("Сохранение нового автора")
    @Description("Автор сохраняется, статус-код 201, в ответе возвращается id сохранённого автора")
    @Test
    public void saveAuthorTest() {
        String first_name = "Fedor";
        ResponseAuthorSave author = reqSpecSaveAuthor(first_name, randomAlphabetic(3), randomAlphabetic(3), 1900, 1, 11, 201);
        AssertSaveAuthor.verifySaveAuthorResponse(author);
    }
}
