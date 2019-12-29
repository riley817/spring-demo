package io.tlor.spring.api.exception;

import io.tlor.spring.api.vo.CommonRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody public ResponseEntity<CommonRes> requestBodyBindingError(BindingResult bindingResult) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            logger.error("parameter name=({}), filed error message =({})", fieldError.getField(), fieldError.getDefaultMessage());
        }
        CommonRes res = new CommonRes();
        res.setErrorEnum(ErrorEnum.INVALID_INPUT_VALUE);
        logger.error("- BindException : [{}] {}", res.getResultCode(), res.getResultMsg());
        return new ResponseEntity<>(res, res.getStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServiceException.class)
    @ResponseBody public ResponseEntity<CommonRes> responseServiceError(ServiceException se) {
        CommonRes res = new CommonRes();
        res.setErrorEnum(se.getErrorEnum());
        logger.error("- ServiceException : [{}] {}", res.getResultCode(), res.getResultMsg());
        return new ResponseEntity<>(res, res.getStatus());
    }
}
