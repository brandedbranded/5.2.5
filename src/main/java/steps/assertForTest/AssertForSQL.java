package steps.assertForTest;

import entity.BookTable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertForSQL {
    public static void sizeVerification(int size, List<BookTable> listOfBooks){
        assertEquals(size,listOfBooks.size());
    }
}
