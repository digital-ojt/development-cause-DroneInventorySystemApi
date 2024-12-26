package com.digitalojt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalojt.api.entity.CategoryInfo;

/**
 * 分類情報Repository
 * 
 * @author your name
 *
 */

public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer> 
{
	// 削除フラグでフィルタリング
	List<CategoryInfo> findByDeleteFlag(String deleteFlag);
	// カテゴリ名でフィルタリング
    List<CategoryInfo> findByCategoryName(String categoryName);
}