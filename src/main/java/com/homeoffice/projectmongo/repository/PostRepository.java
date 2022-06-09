package com.homeoffice.projectmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.homeoffice.projectmongo.domain.Post;

@Repository		//camada repository acessa o BD
public interface PostRepository extends MongoRepository<Post, String> { 
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }") //'?0' indica o parametro a ser capturado, nesse caso o (String text), 'i' indica Case insensitivity
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } }, { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }") //explicacao em doc1
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}

/* Doc1
 * 
 * Busca (usando Queries MongoDB) de um determinado texto nos Titulos dos posts, conteudo dos posts ou nos comentarios dos posts em um determinado intervalo de data;
 *
 * Expressoes MongoDB utilizadasna expressao aninhada:
 * { $and: [ { <expression1> }, { <expression2> }, { <expression3> } ] } - Operacao logica &
 * { $or: [ { <expression1> }, { <expression2> }, { <expression3> } ] } - Operacao logica Or
 * { field: { $gte: value } } - Operacao de comparacao maior ou iqual
 * { field: { $lte: value } } - Operacao de comparacao menor ou iqual
 * { <field>: { $regex: /pattern/, $options: '<options>' } } 
*/