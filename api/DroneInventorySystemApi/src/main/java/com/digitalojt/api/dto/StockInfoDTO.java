package com.digitalojt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 在庫情報のDTO(データ転送オブジェクト)
 *
 * @author yure name
 */

@Data
@AllArgsConstructor
public class StockInfoDTO {
 
	// 分類名
	private String categoryName;
    // 在庫名
    private String name;
    // 在庫数
    private int amount;
    // センター
    private String centerName;
    // 説明
    private String description;
}
