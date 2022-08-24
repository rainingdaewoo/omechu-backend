package omechu.omechubackend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.Post;
import omechu.omechubackend.request.PostSearch;

import java.util.List;

import static omechu.omechubackend.entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(post)
                    .limit(postSearch.getSize())
                    .offset(postSearch.getOffset())
                    .orderBy( post.id.desc() )
                    .fetch();
    }
}
