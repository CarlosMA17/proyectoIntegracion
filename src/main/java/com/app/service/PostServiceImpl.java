package com.app.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.entity.Post;
import com.accesodatos.entity.Tag;
import com.app.dtos.postdto.PostResponseDto;
import com.app.exception.ResourceNotFoundException;
import com.app.mappers.post.PostMapper;
import com.app.mappers.tag.TagMapper;
import com.app.repository.PostRepository;
import com.app.repository.TagRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired private TagRepository tagRepository;
	@Autowired private TagMapper tagMapper;
	@Autowired private PostMapper postMapper;
	

	@Override
	public List<PostResponseDto> getPostByTag(String tagName) {
		
		Tag tag = tagRepository.getByName(tagName);
		return tag.getPosts().stream().map(postMapper::toResponse).toList();
	}

	

}
