package com.jditeam2.modulesson.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor
@Table(name = "item_img")
@Entity
public class ItemImg {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_img_id")
    private Long id;

    private String imgName;
    private String originalImgName;
    private String imgUrl;
    private String repImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public ItemImg(String imgName, String originalImgName, String imgUrl, String repImgYn, Item item) {
        this.imgName = imgName;
        this.originalImgName = originalImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
        this.item = item;
    }

    public void updateItemImg(String originalImgName, String imgName, String imgUrl) {
        this.originalImgName = originalImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }
}
