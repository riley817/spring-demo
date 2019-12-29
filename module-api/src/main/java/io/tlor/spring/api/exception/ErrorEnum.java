package io.tlor.spring.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorEnum {

    SUCCESS(HttpStatus.OK, "S001", "Success."),

    // [ default - C001 ~ C999 ]
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C000", "Error."),
    INVALID_INPUT_VALUE(HttpStatus.OK, "C001", "Invalid Input Value.")
    ;

    private HttpStatus httpStatus;
    private String code;
    private String message;

}
