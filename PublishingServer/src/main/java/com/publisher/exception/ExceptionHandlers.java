package com.publisher.exception;

import com.publisher.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.MalformedURLException;


@ControllerAdvice
@Slf4j
public class ExceptionHandlers  //  extends BaseExceptionHandler
{

    private  static final Logger log= LoggerFactory.getLogger(ExceptionHandlers.class);

    public ExceptionHandlers() {
      //  super(log);
    }

    @ExceptionHandler(MalformedURLException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(final  MalformedURLException ex) {

         log.error(ex.getMessage() +" subscriber url is not valid", ex);
        return new ErrorResponse("subscriber url is not valid", ex.getMessage());
    }
}
