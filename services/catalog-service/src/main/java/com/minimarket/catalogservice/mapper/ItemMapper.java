package com.minimarket.catalogservice.mapper;

import com.minimarket.catalogservice.dto.ItemRequestDto;
import com.minimarket.catalogservice.dto.ItemResponseDto;
import com.minimarket.catalogservice.dto.ItemUpdateRequestDto;
import com.minimarket.catalogservice.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemResponseDto toItemResponseDto(Item item) {
        ItemResponseDto dto = new ItemResponseDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setPrice(item.getPrice());
        dto.setDescription(item.getDescription());
        dto.setIsAvailable(item.getIsAvailable());
        dto.setImageUrl(item.getImageUrl());
        dto.setCategory(item.getCategory());
        return dto;
    }
    public Item toItem(ItemRequestDto dto) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setDescription(dto.getDescription());
        item.setIsAvailable(dto.getIsAvailable());
        item.setImageUrl(dto.getImageUrl());
        item.setCategory(dto.getCategory());
        return item;
    }
    public void updateEntity(Item item, ItemUpdateRequestDto dto) {}

}
