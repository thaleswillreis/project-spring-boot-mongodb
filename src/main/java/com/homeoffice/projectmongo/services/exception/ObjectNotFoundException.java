package com.homeoffice.projectmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	//sobrecarga do construtor basico, recebendo um String e rapassando para a super classe
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
}
