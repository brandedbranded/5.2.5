package steps.assertForTest;

import entity.AuthorTable;
import models.responsesPositive.ResponseGetAllBooksXML;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AssertGetBooksXML {
    public static void verifyGetAllBooksXMLResponse(ResponseGetAllBooksXML listBooksXML, String bookTitle, AuthorTable author, String birthDate){
        assertEquals(listBooksXML.getBooks().get(0).getBookTitle(), bookTitle);
        assertEquals(listBooksXML.getBooks().get(0).getAuthor(), author);
        assertNotNull(listBooksXML.getBooks().get(0).getUpdated());
        assertEquals(listBooksXML.getBooks().get(0).getAuthor().getBirthDate(), birthDate);
    }
}
