package omechu.omechubackend.repository;

import omechu.omechubackend.entity.Post;
import omechu.omechubackend.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
