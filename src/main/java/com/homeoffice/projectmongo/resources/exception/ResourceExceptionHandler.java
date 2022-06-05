package com.homeoffice.projectmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.homeoffice.projectmongo.services.exception.ObjectNotFoundException;

@ControllerAdvice 							//anotacao que indica que essa é a minha classe de tratamento de erros de requisicoes
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class) //anotacao que indica o tipo de exception que deve ser tratada pela classe
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
}
