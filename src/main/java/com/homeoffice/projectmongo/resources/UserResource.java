package com.homeoffice.projectmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.homeoffice.projectmongo.domain.User;
import com.homeoffice.projectmongo.services.UserService;

@RestController //anotacao de indicacao de controlador REST
@RequestMapping(value="/users") //caminho relativo do endpoint
public class UserResource {		//controlador REST acessa a camada de servico
	
	@Autowired 	//a injecao do servico em resources
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET) //metodo do endpoint
	public ResponseEntity<List<User>> findAll() { //objeto que encapsula estruturas para respostas HTTP com o ResponseEntity do spring
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

}
