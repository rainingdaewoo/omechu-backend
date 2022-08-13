package omechu.omechubackend.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.Store;
import omechu.omechubackend.request.PostSearch;

import java.util.List;

import static omechu.omechubackend.entity.QStore.store;
import static omechu.omechubackend.entity.QYoutubeContent.youtubeContent;


@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Store> getStoreList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(store)
                .join(store.youtubeContents, youtubeContent)
                .where( keywordContainsAndEqCategory(postSearch.getKeyword(), postSearch.getCategory()) )
                .orderBy(store.id.desc())
                .fetch();
    }

    private BooleanExpression keywordContainsStoreName(String keyword) {
        if(keyword == null || keyword == "") {
            return null;
        }
        return store.storeName.contains(keyword);
    }

    private BooleanExpression keywordContainsYoutuber(String keyword) {
        if(keyword == null || keyword == "") {
            return null;
        }
        return youtubeContent.youtuber.contains(keyword);
    }

    private BooleanExpression keywordContainsHashTag(String keyword) {
        if(keyword == null || keyword == "") {
            return null;
        }
        return store.hashtag.contains(keyword);
    }

    private BooleanExpression keywordContainsAddress(String keyword) {
        if(keyword == null || keyword == "") {
            return null;
        }
        return store.address.contains(keyword);
    }
    private BooleanExpression categoryEq(String category) {
        if(category == null || category == "") {
            return null;
        }
        return store.category.eq(category);
    }

    private BooleanExpression keywordContainsAndEqCategory(String keyword, String category) {

        if( (keyword == null || keyword == "") && (category == null || category == "")) {
            return null;
        }

        if(keyword == null || keyword == "") {
            return store.category.eq(category);
        }

        return keywordContainsStoreName(keyword)
                 .or(keywordContainsYoutuber(keyword))
                .or(keywordContainsAddress(keyword))
                 .or(keywordContainsHashTag(keyword))
                 .and(categoryEq(category));
    }


}
