package com.minimarket.catalogservice.service;

import com.minimarket.catalogservice.dto.ItemRequestDto;
import com.minimarket.catalogservice.dto.ItemResponseDto;
import com.minimarket.catalogservice.dto.ItemUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {

    Page<ItemResponseDto> getItems(String category,
                                   String name,
                                   Boolean isAvailable,
                                   BigDecimal minPrice,
                                   BigDecimal maxPrice,
                                   Pageable pageable);

    ItemResponseDto getItemById(Long id);
    ItemResponseDto createItem(ItemRequestDto requestDto);
    ItemResponseDto updateItem(Long id, ItemRequestDto requestDto);

    ItemResponseDto patchItem(Long id, ItemUpdateRequestDto itemUpdateRequestDto);
    void deleteItem(Long id);
}
