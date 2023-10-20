package postInfo;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import models.responseNegative.ResponseNegative;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import steps.Specification;
import steps.assertForTest.AssertNegativeResponse;

@Epic("PostTests")
@Story("saveNewBook-negative")
public class SaveBookNegativeTest {

    @DisplayName("Сохранение новой книги с id неизвестного автора")
    @Description("Книга не сохраняется, статус-код 409, error 1004")
    @Test
    public void saveBookUnknownAuthorTest() {
        ResponseNegative response = Specification.reqSpecSaveBookNegative("BookUnknown", 222, 409);
        AssertNegativeResponse.verifyNegativeResponse(response, "1004", "Указанный автор не существует в таблице");
    }

    @DisplayName("Сохранение новой книги с отрицательным id")
    @Description("Книга не сохраняется, статус-код 409, error 1004")
    @ParameterizedTest(name = "id = {0}")
    @ValueSource(longs = {-1, -2,})
    public void saveBookNegativeId(long id) {
        ResponseNegative response = Specification.reqSpecSaveBookNegative("Title", id, 409);
        AssertNegativeResponse.verifyNegativeResponse(response, "1004", "Указанный автор не существует в таблице");

    }

    @DisplayName("Сохранение новой книги с пустым bookTitle")
    @Description("Книга не сохраняется, статус-код 400, error 1001")
    @ParameterizedTest(name = "bookTitle = {0}")
    @NullSource
    public void saveBookNullTitle(String bookTitle) {
        ResponseNegative response = Specification.reqSpecSaveBookNegative(bookTitle, 4, 400);
        AssertNegativeResponse.verifyNegativeResponse(response, "Валидация не пройдена", "Не передан обязательный параметр: bookTitle");

    }
}
