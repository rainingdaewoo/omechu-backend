package omechu.omechubackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import omechu.omechubackend.entity.Like;
import omechu.omechubackend.entity.Store;
import omechu.omechubackend.entity.User;
import omechu.omechubackend.repository.LikeRepository;
import omechu.omechubackend.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;
    private final StoreRepository storeRepository;


    public boolean addLike(User user, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow();

        //중복 좋아요 방지
        if(isNotAlreadyLike(user, store)) {
            likeRepository.save(new Like(store, user));
            return true;
        }

        return false;
    }

    //사용자가 이미 좋아요 한 게시물인지 체크
    private boolean isNotAlreadyLike(User user, Store store) {
        return likeRepository.findByUserAndStore(user, store).isEmpty();
    }
}
