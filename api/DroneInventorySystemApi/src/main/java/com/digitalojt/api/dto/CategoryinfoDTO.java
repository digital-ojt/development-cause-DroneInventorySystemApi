package com.digitalojt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分類情報のDTO(データ転送オブジェクト)
 *
 * @author yure name
 */

@Data
@AllArgsConstructor
public class CategoryinfoDTO {

	// 分類ID
	private int categoryId;
    // 分類名
	private String categoryName;

     
}
