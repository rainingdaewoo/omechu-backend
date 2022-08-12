package omechu.omechubackend.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import omechu.omechubackend.entity.QStore;
import omechu.omechubackend.entity.Store;
import omechu.omechubackend.request.PostSearch;

import java.util.List;

import static omechu.omechubackend.entity.QStore.store;


@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Store> getStoreList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(store)
                .where(
                        keywordContainsStoreName(postSearch.getKeyword()),

                        categoryEq(postSearch.getCategory()))
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
        return store.storeName.contains(keyword);
    }

    private BooleanExpression keywordContainsHashTag(String keyword) {
        if(keyword == null || keyword == "") {
            return null;
        }
        return store.storeName.contains(keyword);
    }
    private BooleanExpression categoryEq(String category) {
        if(category == null || category == "") {
            return null;
        }
        return store.category.eq(category);
    }


}
