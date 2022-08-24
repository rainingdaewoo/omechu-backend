package omechu.omechubackend.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.Request;
import omechu.omechubackend.request.PostSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static omechu.omechubackend.entity.QRequest.request;

@RequiredArgsConstructor
public class RequestRepositoryImpl implements RequestRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Request> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(request)
                    .limit(postSearch.getSize())
                    .offset(postSearch.getOffset())
                    .orderBy( request.id.desc() )
                    .fetch();
    }

    @Override
    public Page<Request> getList(PostSearch postSearch, Pageable pageable) {
        List<Request> content = jpaQueryFactory.selectFrom(request)
                                        .offset(pageable.getOffset()) /*offset*/
                                        .limit(pageable.getPageSize())
                                        .orderBy( request.id.desc() )
                                        .fetch();

        long total = jpaQueryFactory.select(Wildcard.count)
                .from(request)
                .fetch().get(0);
        return new PageImpl<>(content, pageable, total);
    }
}
