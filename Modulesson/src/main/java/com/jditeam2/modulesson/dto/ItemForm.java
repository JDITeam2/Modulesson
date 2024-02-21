package com.jditeam2.modulesson.dto;

import com.jditeam2.modulesson.domain.Item;
import com.jditeam2.modulesson.domain.ItemSellStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemForm {
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    private Integer price;

    private String itemDetail;

    @NotNull
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    @Builder
    public ItemForm(String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
    }

    public Item toEntity(ItemForm form) {
        Item entity = Item.builder()
                .itemName(form.itemName)
                .itemDetail(form.itemDetail)
                .stockNumber(form.stockNumber)
                .itemSellStatus(form.itemSellStatus)
                .build();
        return entity;
    }

    public ItemForm of(Item entity) {
        ItemForm form = ItemForm.builder()
                .itemName(entity.getItemName())
                .itemDetail(entity.getItemDetail())
                .stockNumber(entity.getStockNumber())
                .itemSellStatus(entity.getItemSellStatus())
                .build();
        return form;
    }
}
