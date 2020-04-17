package org.dev.fhhf.hulkstore.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.dev.fhhf.hulkstore.model.AppError;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultErrorController implements ErrorController{

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    AppError error; 
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            error = new AppError("¡La dirección a la que navegaste no existe!", "404");
	            model.addAttribute("error", error);
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	error = new AppError("¡Lo sentimos, algo salió mal!", "500");
	            model.addAttribute("error", error);
	        }
	    }
	    return "error";
	}
}
