package com.digitalojt.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalojt.api.entity.CategoryInfo;
import com.digitalojt.api.repository.CategoryInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 分類情報のサービスクラス
 *
 * @author yure name
 */

@Service
@RequiredArgsConstructor
public class CategoryInfoService {

	// コンストラクタインジェクション
	private final CategoryInfoRepository categoryInfoRepository;

	// category_idから検索
	public CategoryInfo getCategoryInfoByCategoryId(int categoryId) {
		return categoryInfoRepository.findById(categoryId).orElse(null);
	}
	
	// category_nameから検索
	public List<CategoryInfo> getCategoryInfoByCategoryName(String categoryName) {
		return categoryInfoRepository.findByCategoryName(categoryName);
	}

	// 全件検索
	public List<CategoryInfo> getCategoryInfoAll() {
		return categoryInfoRepository.findByDeleteFlag("0");
	}

	// 新規登録
	public CategoryInfo saveCategoryInfo(CategoryInfo categoryInfo) {
		return categoryInfoRepository.save(categoryInfo);
	}
	
}
