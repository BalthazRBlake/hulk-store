package org.dev.fhhf.hulkstore.exception;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.dev.fhhf.hulkstore.model.AppError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class, ParseDateFormatException.class})
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {

        ModelAndView mav = new ModelAndView();
        AppError error;
        error = ex.getClass().equals(NoSuchElementException.class) ? 
        					  new AppError(ex.getMessage(), "404") :
        					  new AppError(ex.getMessage(), "403");
        mav.addObject("error", error);
        mav.setViewName("error");
        return mav;
    }
}
