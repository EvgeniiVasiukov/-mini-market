package com.minimarket.catalogservice.impl;

import com.minimarket.catalogservice.dto.ItemRequestDto;
import com.minimarket.catalogservice.dto.ItemResponseDto;
import com.minimarket.catalogservice.dto.ItemUpdateRequestDto;
import com.minimarket.catalogservice.entity.Item;
import com.minimarket.catalogservice.exception.InvalidItemException;
import com.minimarket.catalogservice.exception.ItemNotFoundException;
import com.minimarket.catalogservice.mapper.ItemMapper;
import com.minimarket.catalogservice.repository.ItemRepository;
import com.minimarket.catalogservice.service.ItemService;
import com.minimarket.catalogservice.specification.ItemSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Page<ItemResponseDto> getItems(String category, String name, Boolean isAvailable, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Specification<Item> spec = Specification.where(null);
        if (category != null && !category.isBlank()) {
        spec = spec.and(ItemSpecifications.containsCategory(category));}
        if (name != null && !name.isBlank()) {
            spec = spec.and(ItemSpecifications.containsName(name));
        }
        if (isAvailable != null) {
            spec = spec.and(ItemSpecifications.isAvailable(isAvailable));
        }
        if (minPrice != null && maxPrice != null) {
            spec = spec.and(ItemSpecifications.priceBetween(minPrice, maxPrice));
        }
        else if (minPrice != null) {
            spec = spec.and(ItemSpecifications.priceGreaterThanEqual(minPrice));
        }
        else if (maxPrice != null) {
            spec = spec.and(ItemSpecifications.priceLessThanEqual(maxPrice));
        }
        return itemRepository.findAll(spec, pageable).map(itemMapper::toItemResponseDto);
    }

    @Override
    public ItemResponseDto getItemById(Long id) {
        return itemRepository.findById(id).map(itemMapper::toItemResponseDto)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public ItemResponseDto createItem(ItemRequestDto requestDto) {
        Item item = itemMapper.toItem(requestDto);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toItemResponseDto(savedItem);
    }

    @Override
    public ItemResponseDto updateItem(Long id, ItemRequestDto requestDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        item.setName(requestDto.getName());
        item.setPrice(requestDto.getPrice());
        item.setIsAvailable(requestDto.getIsAvailable());
        item.setCategory(requestDto.getCategory());
        item.setDescription(requestDto.getDescription());

        Item savedItem = itemRepository.save(item);
        return itemMapper.toItemResponseDto(savedItem);
    }

    @Override
    public ItemResponseDto patchItem(Long id, ItemUpdateRequestDto dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        if (dto.getName() != null) {
            item.setName(dto.getName());
        }
        if (dto.getPrice() != null && dto.getPrice().compareTo(BigDecimal.ZERO) > 0) {
            item.setPrice(dto.getPrice());
        }
        if (dto.getPrice() != null && dto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidItemException("Price must be more than 0");
        }
        if (dto.getIsAvailable() != null) {
            item.setIsAvailable(dto.getIsAvailable());
        }
        if (dto.getCategory() != null) {
            item.setCategory(dto.getCategory());
        }
        if (dto.getDescription() != null) {
            item.setDescription(dto.getDescription());
        }
        Item savedItem = itemRepository.save(item);
        return itemMapper.toItemResponseDto(savedItem);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
        itemRepository.delete(item);
    }
}

