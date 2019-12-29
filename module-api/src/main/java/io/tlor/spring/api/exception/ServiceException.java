package io.tlor.spring.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ErrorEnum errorEnum;
    private Object data;

    public ServiceException(ErrorEnum errorEnum) {
        setErrorEnum(errorEnum);
        setData(null);
    }

    public ServiceException(ErrorEnum errorEnum, Object data) {
        setErrorEnum(errorEnum);
        setData(data);
    }
}
