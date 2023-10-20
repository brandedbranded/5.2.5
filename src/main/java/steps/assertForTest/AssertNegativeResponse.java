package steps.assertForTest;

import models.responseNegative.ResponseNegative;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertNegativeResponse {
    public static void verifyNegativeResponse(ResponseNegative response, String errorCode, String errorMessage) {
        assertEquals(response.getErrorCode(), errorCode, "Неверный errorCode");
        assertEquals(response.getErrorMessage(), errorMessage, "Неверный errorMessage");
    }
}
