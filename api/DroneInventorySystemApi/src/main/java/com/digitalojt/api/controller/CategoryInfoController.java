package com.digitalojt.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.dto.CategoryinfoDTO;
import com.digitalojt.api.service.CategoryInfoService;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報 API Controller
 * 
 * @author your name
 *
 */

@RestController
@RequestMapping("/api/categoryinfo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // どのドメインからでもアクセス可能
public class CategoryInfoController {
	
	// コンストラクタインジェクション
    private final CategoryInfoService categoryInfoService;

    // 全件取得
    @GetMapping("/init")
    public List<CategoryinfoDTO> getAllCategories() {
        return categoryInfoService.getCategoryInfoAll().stream()
                .map(category -> new CategoryinfoDTO(category.getCategoryId(), category.getCategoryName()))
                .collect(Collectors.toList());
    }

	
}