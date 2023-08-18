package ru.netology.repository;

import ru.netology.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

// Stub
public class PostRepository {
  AtomicInteger counter = new AtomicInteger(1);
  CopyOnWriteArrayList<Post> posts = new CopyOnWriteArrayList<>();


  public List<Post> all() {
    return posts;
  }

  public Optional<Post> getById(long id) {
      Post post = posts.get((int) id);
      return Optional.ofNullable(post);
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(counter.get());
      posts.add(post);
      counter.incrementAndGet();
      return post;
    }
    if ((post.getId() != 0 && post.getId() <= counter.get())) {
      for (Post post1 : posts) {
        if (post1.getId() == post.getId()) {
          post1.setContent(post.getContent());
          return post;
        }
        post.setId(counter.get());
        posts.add(post);
        counter.incrementAndGet();
      }
    }
    return post;
  }

  public void removeById(long id) {

    posts.remove((int) id);
  }
}
