package com.example.mindPet.Service;

import com.example.mindPet.Model.Post;
import com.example.mindPet.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> listar() {
        return repository.findAll();
    }

    public Post guardar(Post post) {
        return repository.save(post);
    }

    public Post actualizar(int id, Post post) {
        post.setId(id);
        return repository.save(post);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}