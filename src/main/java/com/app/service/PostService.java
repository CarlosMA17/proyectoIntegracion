package com.app.service;

import java.util.List;

import com.app.dtos.postdto.PostResponseDto;

public interface PostService {
	List<PostResponseDto> getPostByTag(String tagName);
	
}
