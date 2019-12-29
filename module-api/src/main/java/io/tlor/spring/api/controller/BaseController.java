package io.tlor.spring.api.controller;

import io.tlor.spring.api.exception.ErrorEnum;
import io.tlor.spring.api.exception.ServiceException;
import io.tlor.spring.api.vo.CommonRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

@RestController
public class BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected CommonRes _callService(HttpServletRequest request, Callable<Object> service) {

        CommonRes res = new CommonRes();
        try {

            Object resData = service.call();
            if (resData != null) {
                res.setSuccessData(resData);
            } else {
                res.setSuccessData();
            }

        } catch (ServiceException se) {
            res.setErrorEnum(se.getErrorEnum());
        } catch (Exception e) {
            res.setErrorEnum(ErrorEnum.DEFAULT_ERROR);
        }
        return res;
    }
}
