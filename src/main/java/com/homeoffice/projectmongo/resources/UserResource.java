package com.homeoffice.projectmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.homeoffice.projectmongo.domain.User;

@RestController //anotacao de indicacao de controlador REST
@RequestMapping(value="/users") //caminho relativo do endpoint
public class UserResource {
	
	@RequestMapping(method=RequestMethod.GET) //metodo do endpoint
	public ResponseEntity<List<User>> findAll() { //objeto que encapsula estruturas para respostas HTTP com o ResponseEntity do spring
		User maria = new User("1", "Maria Silva", "maria@gmail.com");
		User alex = new User("2", "Alex Alves", "alex@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list);
	}

}
