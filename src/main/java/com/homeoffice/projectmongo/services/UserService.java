package com.homeoffice.projectmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeoffice.projectmongo.domain.User;
import com.homeoffice.projectmongo.dto.UserDTO;
import com.homeoffice.projectmongo.repository.UserRepository;
import com.homeoffice.projectmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService { // a camada de servico acessa o repositorio

	@Autowired // a injecao de dependencia do spring instancia automaticamente o objeto no
				// servico
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	// metodo de consulta
	// substituindo a verificacao de valor "null" com um "if" por um Optional (Java
	// 9+)
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	// metodo insert
	public User insert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findById(id); // aproveitando o metodo "findById acima para tratar a exception
		repo.deleteById(id); // Obs: usar deleteById() no lugar do delete() no Spring Boot 2+
	}

	public User update(User obj) {
		User newObj = findById(obj.getId()); 	//busca o dado original no banco pelo id
		updateData(newObj, obj);				//chama o metodo para atualizar o novo obj a partir do antigo
		return repo.save(newObj);				//salva as alteracoes
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
	}

	/*
	 * Não coloquei o metodo "fromDTO" no pacote "DTO" pq em caso de instanciacao de
	 * "User" atraves do banco de dados, eu aproveito a dependencia do BD em
	 * UserService, isso facilita uma futura manutencao no codigo com acesso a dados
	 * do BD.
	 */
	// metodo fromDTO
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
