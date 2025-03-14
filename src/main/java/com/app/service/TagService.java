package com.app.service;

import java.util.List;

import com.app.dtos.postdto.PostResponseDto;
import com.app.dtos.tagdto.TagResponseDtoPosts;

public interface TagService {	
	TagResponseDtoPosts getPostByTagId(Long tagId);
	
}
