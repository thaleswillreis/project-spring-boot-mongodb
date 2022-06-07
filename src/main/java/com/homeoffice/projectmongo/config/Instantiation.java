package com.homeoffice.projectmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.homeoffice.projectmongo.domain.Post;
import com.homeoffice.projectmongo.domain.User;
import com.homeoffice.projectmongo.dto.AuthorDTO;
import com.homeoffice.projectmongo.dto.CommentDTO;
import com.homeoffice.projectmongo.repository.PostRepository;
import com.homeoffice.projectmongo.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner {

	//Repositories injetados para salvar os dados no BD
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Configuracao de formato de data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		//limpeza das colecoes no mongoDB
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		//instaciando os usuarios
		User thassia = new User(null, "Thassia Reis", "minininha@gmail.com");
		User thuria = new User(null, "Thuria Reis", "mininazona@gmail.com");
		User cecilia = new User(null, "Cecilia Reis", "sibita@gmail.com");
		User juscelina = new User(null, "Juscelina Cortez", "maezinha@gmail.com");
		User thales = new User(null, "Thales Will", "mininozinho@gmail.com");
		User dayane = new User(null, "Dayane Faisca", "faisquinha@gmail.com");
		
		//salvando os dados dos usuarios (para gerar um id) antes de copiar os dados para o AuthorDTO
		userRepository.saveAll(Arrays.asList(thassia, thuria, cecilia, juscelina, thales, dayane));
		
		//instaciando os posts
		Post post1 = new Post(null, sdf.parse("02/06/2022"), "Partiu viagem", "Vou viajar para onde o mininuzinho. Abraços!", new AuthorDTO(thassia));
		Post post2 = new Post(null, sdf.parse("03/06/2022"), "Acordei de carro novo!", "Finalmente consequi comprar o carro que eu queria! Estou feliz!", new AuthorDTO(thassia));
		
		//instanciando os comentarios
		CommentDTO c1 = new CommentDTO("Oba, vou esperar!", sdf.parse("06/06/2022"), new AuthorDTO(thales));
		CommentDTO c2 = new CommentDTO("Estou com saudades!", sdf.parse("07/06/2022"), new AuthorDTO(thales));
		CommentDTO c3 = new CommentDTO("Voce merece ainda mais, Parabéns! :)", sdf.parse("07/06/2022"), new AuthorDTO(thales));
		
		//associando os commentarios
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		//salvando os dados do post
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		thassia.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(thassia);
	}

}
