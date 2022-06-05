package com.homeoffice.projectmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeoffice.projectmongo.domain.User;
import com.homeoffice.projectmongo.repository.UserRepository;
import com.homeoffice.projectmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService { 	//a camada de servico acessa o repositorio
	
	@Autowired	//a injecao de dependencia do spring instancia automaticamente o objeto no servico
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	//substituindo a verificacao de valor "null" com um "if" por um Optional (Java 9+)
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		}
}
