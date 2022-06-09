package com.homeoffice.projectmongo.repository;

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
}
