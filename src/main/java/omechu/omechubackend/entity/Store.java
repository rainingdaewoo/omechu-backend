package omechu.omechubackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Store extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    private String storeName;

    private String address;

    private String storeNaverURL;

    private String category;

    private String phone;

    private String hashtag;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    List<YoutubeContent> youtubeContents = new ArrayList<>();

    public Store() {
    }

    @Builder
    public Store(String storeName, String address, String phone, String storeNaverURL, String category, String hashtag) {
        this.storeName = storeName;
        this.address = address;
        this.phone = phone;
        this.storeNaverURL = storeNaverURL;
        this.category = category;
        this.hashtag = hashtag;
    }

    public StoreEditor.StoreEditorBuilder toStoreEditor() {
        return StoreEditor.builder()
                .storeName(storeName)
                .address(address)
                .storeNaverURL(storeNaverURL)
                .category(category)
                .phone(phone)
                .hashtag(hashtag);
    }

    public void editStore(StoreEditor storeEditor) {
        this.storeName = storeEditor.getStoreName();
        this.address = storeEditor.getAddress();
        this.category = storeEditor.getCategory();
        this.phone = storeEditor.getPhone();
        this.storeNaverURL = storeEditor.getStoreNaverURL();
        this.hashtag = storeEditor.getHashtag();
    }


}
