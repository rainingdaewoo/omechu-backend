package omechu.omechubackend.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.Request;
import omechu.omechubackend.request.PostSearch;
import omechu.omechubackend.response.QRequestResponseDto;
import omechu.omechubackend.response.RequestResponseDto;
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
    public Page<RequestResponseDto> getList(PostSearch postSearch, Pageable pageable) {
        List<RequestResponseDto> content = jpaQueryFactory
                                            .select(new QRequestResponseDto(
                                                    request.id.as("requestId"),
                                                    request.title,
                                                    request.content,
                                                    request.category,
                                                    request.state,
                                                    request.createdDate,
                                                    request.user.username
                                            ))
                                            .from(request)
                                            .offset(pageable.getOffset())
                                            .limit(pageable.getPageSize())
                                            .orderBy( request.id.desc() )
                                            .fetch();

        long total = jpaQueryFactory.select(Wildcard.count)
                .from(request)
                .fetch().get(0);
        return new PageImpl<>(content, pageable, total);
    }
}
