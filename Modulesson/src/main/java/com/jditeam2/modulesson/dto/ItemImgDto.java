package com.jditeam2.modulesson.dto;


import com.jditeam2.modulesson.domain.ItemImg;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ItemImgDto {
    private Long id;

    private String imgName;
    private String originalImgName;
    private String imgUrl;
    private String repImgYn;

    @Builder
    public ItemImgDto(String imgName, String originalImgName, String imgUrl, String repImgYn) {
        this.imgName = imgName;
        this.originalImgName = originalImgName;
        this.imgUrl = imgUrl;
        this.repImgYn = repImgYn;
    }

    public ItemImg toEntity(ItemImgDto dto) {
        ItemImg entity = ItemImg.builder()
                .imgName(dto.getImgName())
                .originalImgName(dto.originalImgName)
                .imgUrl(dto.imgUrl)
                .repImgYn(dto.repImgYn)
                .build();
        return entity;
    }

    public ItemImgDto of(ItemImg entity) {
        ItemImgDto dto = ItemImgDto.builder()
                .imgName(entity.getImgName())
                .originalImgName(entity.getOriginalImgName())
                .imgUrl(entity.getImgUrl())
                .repImgYn(entity.getRepImgYn())
                .build();
        return dto;
    }
}
