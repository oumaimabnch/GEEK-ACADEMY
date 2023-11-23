package com.gdev.geekacademybackend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import com.gdev.geekacademybackend.exceptions.AppException;
import com.gdev.geekacademybackend.models.File;
import com.gdev.geekacademybackend.models.Post;
import com.gdev.geekacademybackend.services.FileStorageService;
import com.gdev.geekacademybackend.services.PostService;
import com.gdev.geekacademybackend.services.UserService;
import com.gdev.geekacademybackend.services.dto.FileResponse;
import com.gdev.geekacademybackend.services.dto.PostDto;
import com.gdev.geekacademybackend.services.dto.PostResponse;
import com.gdev.geekacademybackend.services.payload.ApiResponse;
import com.gdev.geekacademybackend.services.payload.UserSummary;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = { "http://localhost:4200" })
public class PostController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService storageService;

    @GetMapping
    public List<PostResponse> getAllPosts() {

        return postService.getAllPosts().stream().map(post -> {
            PostResponse postResponse = modelMapper.map(post, PostResponse.class);
            UserSummary userSummary = userService.getUserSummary(post.getCreatedBy());
            postResponse.setAuthor(userSummary);

            File file = post.getJointFile();
            FileResponse fileResponse = new FileResponse(file);
            postResponse.setJointFile(fileResponse);
            return postResponse;
        })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        Post post = postService.getPostById(id);

        // convert entity to DTO
        PostDto postResponse = modelMapper.map(post, PostDto.class);
        postResponse.setJointFile(post.getJointFile());

        return ResponseEntity.ok().body(postResponse);
    }

    @RolesAllowed({ "INSTRUCTOR", "ADMIN" })
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PostDto> createPost(@RequestPart("post") PostDto postRequest,
            @RequestPart("jointFile") MultipartFile file) {

        // convert DTO to entity
        Post postDto = modelMapper.map(postRequest, Post.class);

        try {
            File jointFile = storageService.store(file);
            postDto.setJointFile(jointFile);

        } catch (Exception e) {
            throw new AppException("Could not upload the file: " + file.getOriginalFilename() + "!");
        }

        Post post = postService.createPost(postDto);

        // convert entity to DTO
        PostDto postResponse = modelMapper.map(post, PostDto.class);

        return new ResponseEntity<PostDto>(postResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id, @RequestBody PostDto postRequest) {

        // convert DTO to Entity
        Post postDto = modelMapper.map(postRequest, Post.class);

        Post post = postService.updatePost(id, postDto);

        // entity to DTO
        PostDto postResponse = modelMapper.map(post, PostDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Post deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}