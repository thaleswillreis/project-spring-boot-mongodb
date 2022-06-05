package com.homeoffice.projectmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.homeoffice.projectmongo.domain.User;
import com.homeoffice.projectmongo.dto.UserDTO;
import com.homeoffice.projectmongo.services.UserService;

@RestController															 //anotacao de indicacao de controlador REST
@RequestMapping(value="/users") 										//caminho relativo do endpoint
public class UserResource {												//controlador REST acessa a camada de servico
	
	@Autowired															//a injecao do servico em resources
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)							//metodo do endpoint
	public ResponseEntity<List<UserDTO>> findAll() { 					//objeto que encapsula estruturas para respostas HTTP com o ResponseEntity do spring
		List<User> list = service.findAll();
		//convertendo uma lista de User para uma lista de UserDTO co expressao Lambda
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 			//metodo GET do endpoint em /users/{id}
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {	 //@PathVariable casa o id do argumento com o valor id da url
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj)); 				//retornando o obj "convertido" em UserDTO
	}
	
	@RequestMapping(method=RequestMethod.POST) 							//metodo POST do endpoint em /users
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {	//anotacao Spring que envia dados de um formulário de cadastro
		User obj = service.fromDTO(objDto);								//converte DTO para User
		obj = service.insert(obj);										//Chamada do metodo "insert"
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); //Explicacao 1 no fim do cod.
		return ResponseEntity.created(uri).build();
	}
}

/*
 * Explicacao 1:
 * 
 * Instanciacao de um objeto do tipo URI passando um obj.getId como argumento
 * para poder pegar o endereco do novo objeto que inseri.
 * Na linha abaixo eu passo o caminho em "uri" e retorno o metodo "created" do ResponseEntity que me
 * retorna o codigo "201" que eh o codigo HTTP de criacao de um novo recurso.
 * Ou seja, meu retorno nesse metodo será uma resposta vazia "void", com o codigo 201
 * e contendo a localizacao do novo recurso.
 */
