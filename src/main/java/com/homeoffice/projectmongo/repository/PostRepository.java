package com.homeoffice.projectmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.homeoffice.projectmongo.domain.Post;

@Repository		//camada repository acessa o BD
public interface PostRepository extends MongoRepository<Post, String> { 
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
