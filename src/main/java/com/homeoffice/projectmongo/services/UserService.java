package com.homeoffice.projectmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeoffice.projectmongo.domain.User;
import com.homeoffice.projectmongo.repository.UserRepository;

@Service
public class UserService { 	//a camada de servico acessa o repositorio
	
	@Autowired	//a injecao de dependencia do spring instancia automaticamente o objeto no servico
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}

}
