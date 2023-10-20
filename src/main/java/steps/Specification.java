package steps;


import models.requests.RequestToken;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.authorExmp.BookSaveAuthorExmp;
import models.requests.RequestAuthorSave;
import models.requests.RequestBookSave;
import models.requests.RequestGetAllBooksXML;
import models.responseNegative.ResponseNegative;
import models.responsesPositive.ResponseAuthorSave;
import models.responsesPositive.ResponseBookSave;
import models.responsesPositive.ResponseGetAllBooksXML;
import utils.DateFormatter;

import static io.restassured.RestAssured.given;

public class Specification {
    public static RequestSpecification reqSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setContentType(ContentType.JSON)
                .setPort(8080)
                .addHeader("Authorization", "Bearer " + getAuthToken())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static RequestSpecification reqSpecXML() {
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setContentType(ContentType.XML)
                .setPort(8080)
                .addHeader("Authorization", "Bearer " + getAuthToken())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static String getAuthToken() {
        RequestToken token = new RequestToken("master_log","qweasdzxc");
        return given().contentType(ContentType.JSON)
                .body(token)
                .when()
                .get("http://localhost:8080/auth/login")
                .then().log().all()
                .extract().jsonPath().getString("jwtToken");
    }

    public static ResponseSpecification respSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static ResponseAuthorSave reqSpecSaveAuthor(String firstName, String lastName, String middleName, int year, int month, int day, int statusCode) {
        String date = DateFormatter.formatDate(year,month,day);

        RequestAuthorSave author = new RequestAuthorSave(firstName, lastName, middleName, date);

        return given().spec(reqSpec())
                .body(author)
                .when()
                .post(EndPoints.saveNewAuthorURL)
                .then().spec(respSpec(statusCode))
                .extract().as(ResponseAuthorSave.class);
    }

    public static ResponseBookSave reqSpecSaveBook(String bookTitle, long authorId, int statusCode) {
        BookSaveAuthorExmp author = new BookSaveAuthorExmp(authorId);
        RequestBookSave book = new RequestBookSave(bookTitle, author);

        return given().spec(reqSpec())
                .body(book)
                .when()
                .post(EndPoints.saveNewBookURL)
                .then().spec(respSpec(statusCode))
                .extract().as(ResponseBookSave.class);
    }

    public static ResponseNegative reqSpecSaveBookNegative(String bookTitle, long authorId, int statusCode) {
        BookSaveAuthorExmp author = new BookSaveAuthorExmp(authorId);
        RequestBookSave book = new RequestBookSave(bookTitle, author);

        return given().spec(reqSpec())
                .body(book)
                .when()
                .post(EndPoints.saveNewBookURL)
                .then().spec(respSpec(statusCode))
                .extract().as(ResponseNegative.class);
    }

    public static ResponseNegative reqSpecGetAllBooksNegative(String id, int statusCode) {
        return given().spec(reqSpec())
                .when()
                .get(EndPoints.getAllBooksURL, id)
                .then().spec(respSpec(statusCode))
                .extract().as(ResponseNegative.class);
    }

    public static ResponseNegative reqSpecGetAllBooksNegativeIdNull(int statusCode) {
        return given().spec(reqSpec())
                .when()
                .get("/library/authors/" + null + "/books")
                .then().spec(respSpec(statusCode))
                .extract().as(ResponseNegative.class);
    }

    public static ResponseGetAllBooksXML reqSpecGetAllBooksXML(long id, int statusCode) {
        RequestGetAllBooksXML author = new RequestGetAllBooksXML();
        author.setAuthorId(id);

        return given().spec(reqSpecXML())
                .body(author)
                .when()
                .post(EndPoints.getAllBooksXMLUrl)
                .then().spec(respSpec(statusCode))
                .extract().as(ResponseGetAllBooksXML.class);
    }

    public static ValidatableResponse reqSpecGetAllBooksResponse(String id, int statusCode) {
        return given().spec(reqSpec())
                .when()
                .get(EndPoints.getAllBooksURL, id)
                .then().spec(respSpec(statusCode));
    }

    /*public static List<ResponseGetAllBooks> reqSpecGetAllBooks(String id, int statusCode) {
        return given().spec(reqSpec())
                .when()
                .get(EndPoints.getAllBooksURL, id)
                .then().spec(respSpec(statusCode))
                .extract().jsonPath().getList(".", ResponseGetAllBooks.class);
    }*/
}
