package io.tlor.spring.api.vo;

import io.tlor.spring.api.exception.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonRes {

    private HttpStatus status;
    private String resultCode;
    private String resultMsg;
    private Object data;

    public void setSuccessData() {
        setStatus(HttpStatus.OK);
        setResultCode(ErrorEnum.SUCCESS.getCode());
        setResultMsg(ErrorEnum.SUCCESS.getMessage());
    }

    public void setSuccessData(Object data) {
        setStatus(HttpStatus.OK);
        setResultCode(ErrorEnum.SUCCESS.getCode());
        setResultMsg(ErrorEnum.SUCCESS.getMessage());
        if(data != null) {
            setData(data);
        }
    }

    public void setErrorEnum(ErrorEnum errorEnum) {
        setStatus(errorEnum.getHttpStatus());
        setResultCode(errorEnum.getCode());
        setResultMsg(errorEnum.getMessage());
    }

    public void setErrorEnum(ErrorEnum errorEnum, Object data) {
        setStatus(errorEnum.getHttpStatus());
        setResultCode(errorEnum.getCode());
        setResultMsg(errorEnum.getMessage());
        if(data != null) {
            setData(data);
        }
    }
}