package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;
import java.util.List;

public interface Store {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    Collection<User> findAllUsers();

    void savePost(Post post);

    void saveCandidate(Candidate candidate);

    void saveUser(User user);

    Post findByPostId(int id);

    Candidate findByCandidateId(int id);

    User findByUserId(int id);
    
    User findByUserEmail(String email);

    List<City> getAllCities();
}