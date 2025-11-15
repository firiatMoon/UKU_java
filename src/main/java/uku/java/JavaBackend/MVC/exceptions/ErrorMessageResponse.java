package uku.java.JavaBackend.MVC.exceptions;

import java.time.LocalDateTime;

public record ErrorMessageResponse(
        String message,
        String detailedMessage,
        LocalDateTime timestamp) {

}
