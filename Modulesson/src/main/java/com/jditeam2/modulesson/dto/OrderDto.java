package com.jditeam2.modulesson.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderDto {

    private Long itemId;
    private int count;

    @Builder
    public OrderDto(Long itemId, int count) {
        this.itemId = itemId;
        this.count = count;
    }
}
