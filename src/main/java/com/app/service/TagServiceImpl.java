package com.app.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accesodatos.entity.Tag;
import com.app.dtos.postdto.PostResponseDto;
import com.app.dtos.tagdto.TagResponseDtoPosts;
import com.app.exception.ResourceNotFoundException;
import com.app.mappers.post.PostMapper;
import com.app.mappers.tag.TagMapper;
import com.app.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired private TagRepository tagRepo;
	@Autowired private TagMapper tagMapper;

	private Tag validateAndGetTag(Long id) {
		return tagRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("ta with id %d not found", id)));
	}
	
	@Override
	public TagResponseDtoPosts getPostByTagId(Long tagId) {
		
		Tag tag = validateAndGetTag(tagId);
		return tagMapper.toResponsePosts(tag);
	}

}
