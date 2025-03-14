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
import com.app.service.PostServiceImpl;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/api/v1")
public class PostController {
	
    @Autowired private PostServiceImpl postService;
    
    private final String POST_RESOURCE = "/posts";
    
    
    @GetMapping(value = POST_RESOURCE + "/by-tag",
	 		 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<List<PostResponseDto>>> getAll(@PathParam(value = "tagName") String tagName) {
    	List<PostResponseDto> posts = postService.getPostByTag(tagName);
    	ApiResponseDto<List<PostResponseDto>> response= new ApiResponseDto<>("students fetched succesfully", HttpStatus.OK.value(), posts);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
