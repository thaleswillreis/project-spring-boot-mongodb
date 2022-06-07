package com.homeoffice.projectmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.homeoffice.projectmongo.domain.Post;

@Repository		//camada repository acessa o BD
public interface PostRepository extends MongoRepository<Post, String> {  

}
