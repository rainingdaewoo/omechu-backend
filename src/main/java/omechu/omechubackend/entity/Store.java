package omechu.omechubackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Store {

    @Id @GeneratedValue
    @Column(name = "store_id")
    private Long id;

    private String storeName;

    private String address;

    private String storeNaverURL;

    private String phone;

    @OneToMany(mappedBy = "store")
    List<YoutubeContent> youtubeContents = new ArrayList<>();

    public Store() {

    }

    @Builder
    public Store(String storeName, String address, String phone, String storeNaverURL) {
        this.storeName = storeName;
        this.address = address;
        this.phone = phone;
        this.storeNaverURL = storeNaverURL;
    }


}
