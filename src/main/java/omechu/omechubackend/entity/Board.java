package omechu.omechubackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Board extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private int hit;
    // YoutubeContentBoard
}
