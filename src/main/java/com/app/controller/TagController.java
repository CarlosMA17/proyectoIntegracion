package com.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.api.ApiResponseDto;
import com.app.dtos.postdto.PostResponseDto;
import com.app.dtos.tagdto.TagResponseDtoPosts;
import com.app.service.TagServiceImpl;


@RestController
@RequestMapping("/api/v1")
public class TagController {
    @Autowired private TagServiceImpl tagService;
    
    private final String TAG_RESOURCE = "/tags";
    private final String TAG_RESOURCE_ID = TAG_RESOURCE + "/{tagId}";
    
	@GetMapping(value = TAG_RESOURCE_ID, 
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponseDto<TagResponseDtoPosts>> getPostByTag(@PathVariable Long tagId) {
		
		TagResponseDtoPosts posts = tagService.getPostByTagId(tagId);
		
		ApiResponseDto<TagResponseDtoPosts> response = new ApiResponseDto<>("posts fetched successfully",
				HttpStatus.OK.value(), posts);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
    
}
