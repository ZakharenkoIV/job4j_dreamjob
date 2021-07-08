package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();

        System.out.println("-----------");
        System.out.println("create post");
        store.savePost(new Post(0, "Java Job 1"));
        store.savePost(new Post(0, "Java Job 2"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        System.out.println("-----------");
        System.out.println("update post");
        store.savePost(new Post(2, "Java Job 2*"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }

        System.out.println("-----------");
        System.out.println("findById post");
        Post p = store.findByPostId(1);
        if (p != null) {
            System.out.println(p.getId() + " " + p.getName());
        } else {
            System.out.println("Пост с введённым ID не найден");
        }
    }
}