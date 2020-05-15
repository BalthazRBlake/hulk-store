package org.dev.fhhf.hulkstore.exception;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.dev.fhhf.hulkstore.model.AppError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({NoSuchElementException.class, 
    				   ParseDateFormatException.class,
    				   NotEnoughStockException.class})
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {

        ModelAndView mav = new ModelAndView();
        AppError error = new AppError(ex.getMessage(), "404");
        
    	if(ex.getClass().equals(ParseDateFormatException.class)) {
    		error = new AppError(ex.getMessage(), "403");
    	}else
    	if(ex.getClass().equals(NotEnoughStockException.class)) {
    		error = new AppError(ex.getMessage(), "401");
    	}
    	
        mav.addObject("error", error);
        mav.setViewName("error");
        return mav;
    }
}
