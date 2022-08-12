package omechu.omechubackend.repository;

import omechu.omechubackend.entity.Store;
import omechu.omechubackend.request.PostSearch;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> getStoreList(PostSearch postSearch);
}
