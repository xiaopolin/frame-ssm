package com.xpl.framework;

import com.xpl.api.constant.ErrorCodeConstant;
import com.xpl.api.vo.ResultView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    private static final Logger logger = LogManager.getLogger(BaseController.class.getName());

    @ExceptionHandler(value = {RuntimeException.class})
    public ResultView<?> handlerException(Exception e) {
        ResultView<?> result = new ResultView<>();

        logger.error("系统异常:" + e);


        result.setCode(ErrorCodeConstant.ERRORCODE_EXCEPTION);
        result.setMsg(ErrorCodeConstant.ERRORMSG_EXCEPTION);
        return result;
    }


}
