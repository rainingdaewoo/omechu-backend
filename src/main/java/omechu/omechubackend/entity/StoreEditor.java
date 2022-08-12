package omechu.omechubackend.entity;

import lombok.Builder;
import lombok.Getter;

/**
 * 수정할 수 있는 필드만 수정하게 따로 클래스 생성.
 */
@Getter
public class StoreEditor {

    private final String storeName;

    private final String address;

    private final String storeNaverURL;

    private final String category;

    private final String phone;

    private final String hashtag;



    @Builder
    public StoreEditor(String storeName, String address, String category, String storeNaverURL, String phone, String hashtag) {
        this.storeName = storeName;
        this.address = address;
        this.category = category;
        this.storeNaverURL = storeNaverURL;
        this.phone = phone;
        this.hashtag = hashtag;
    }
}
