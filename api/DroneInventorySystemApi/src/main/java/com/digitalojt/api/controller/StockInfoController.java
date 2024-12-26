package com.digitalojt.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.consts.Operation;
import com.digitalojt.api.dto.StockInfoDTO;
import com.digitalojt.api.exception.InvalidInputException;
import com.digitalojt.api.service.OperationLogService;
import com.digitalojt.api.service.StockInfoService;
import com.digitalojt.api.util.InputValidator;

import lombok.RequiredArgsConstructor;

/**
 * 在庫情報 API Controller
 * 
 * @author your name
 *
 */

@RestController
@RequestMapping("/api/stockinfo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // どのドメインからでもアクセス可能
public class StockInfoController {
	
	// コンストラクタインジェクション
	private final StockInfoService stockInfoService;
	private final OperationLogService operationLogService;
	
	// 全件取得
	@GetMapping("/init")
    public List<StockInfoDTO> getStockInfo() {
        return stockInfoService.getStockInfo();
    }
	
	// categoryIdから在庫情報を取得
	@GetMapping("/category")
	public List<StockInfoDTO> getStockInfoByCategory(
			@RequestParam(value = "categoryId", required = false) Integer categoryId)
	{
	    if (categoryId == null) {
	        return stockInfoService.getStockInfo();
	    }
	    return stockInfoService.getStockInfoByCategory(categoryId);
	}
	
	// 検索条件から在庫情報を取得
	@GetMapping("/search")
	public List<StockInfoDTO> searchStockInfo(
	        @RequestParam(value = "categoryId", required = false) Integer categoryId,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam(value = "amountMin", required = false) String amountMin,
	        @RequestParam(value = "amountMax", required = false) String amountMax) {

		System.out.println("categoryId: " + categoryId);
		System.out.println("name: " + name);
		System.out.println("amountMin: " + amountMin);
		System.out.println("amountMax: " + amountMax);
		
		// ログメッセージ
	    String logMessage = String.format("在庫一覧検索　categoryId: %s, name: %s, amountMin: %s, amountMax: %s", 
	                                      categoryId, name, amountMin, amountMax);
	    // 操作ログを記録
	    operationLogService.logOperation("adminId", Operation.OPERATE_TYPE_READ, Operation.TABLE_ID_CENTER_INFO, 1, logMessage, "1", "0");
		
		
		// 検索条件が全て未入力の場合はエラー
		if (categoryId == null && 
				(name == null || name.isEmpty()) && 
				(amountMin == null || amountMin.isEmpty()) && 
				(amountMax == null || amountMax.isEmpty())) 
		{
	        throw new InvalidInputException("invalid.input.allParameters");
	    }
		
		// 入力値のバリデーション
	    if (!InputValidator.isValid(name)) {
	        throw new InvalidInputException("invalid.input.name");
	    }
	    if (amountMin != null) {
	        if (!InputValidator.isValidNumber(amountMin)) {
	            throw new InvalidInputException("invalid.input.amount");
	        }
	    }
	    if (amountMax != null) {
	        if (!InputValidator.isValidNumber(amountMax)) {
	            throw new InvalidInputException("invalid.input.amount");
	        }
	    }

	    return stockInfoService.searchStockInfo(categoryId, name,
	        amountMin != null ? Integer.parseInt(amountMin) : null,
	        amountMax != null ? Integer.parseInt(amountMax) : null);
	}

}