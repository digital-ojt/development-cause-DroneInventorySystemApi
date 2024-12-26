package com.digitalojt.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digitalojt.api.dto.StockInfoDTO;
import com.digitalojt.api.entity.StockInfo;
import com.digitalojt.api.repository.StockInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * 在庫情報のサービスクラス
 *
 * @author yure name
 */

@Service
@RequiredArgsConstructor
public class StockInfoService {

	// コンストラクタインジェクション
	private final StockInfoRepository stockInfoRepository;

	/**
	 * 在庫情報を取得
	 * 
	 * @return 在庫情報リスト
	 */
	 	// 在庫情報を取得
	    public List<StockInfoDTO> getStockInfo() {
	        List<StockInfo> stockInfos = stockInfoRepository.findAll();
	        return stockInfos.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }

	    // 分類IDで在庫情報を取得
	    public List<StockInfoDTO> getStockInfoByCategory(int categoryId) {
	        List<StockInfo> stockInfos = stockInfoRepository.findByCategoryId(categoryId);
	        return stockInfos.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
	    // 検索条件で在庫情報を取得
	    public List<StockInfoDTO> searchStockInfo(Integer categoryId, String name, Integer amountMin, Integer amountMax) {
	        List<StockInfo> stockInfos = stockInfoRepository.findByCategoryIdAndNameAndAmountRange(categoryId, name, amountMin, amountMax);
	        return stockInfos.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }
	    
	    // EntityからDTOへの変換メソッド
	    private StockInfoDTO convertToDTO(StockInfo stockInfo) {
	        return new StockInfoDTO(
	            stockInfo.getCategoryInfo().getCategoryName(),
	            stockInfo.getName(),
	            stockInfo.getAmount(),
	            stockInfo.getCenterInfo().getCenterName(),
	            stockInfo.getDescription()
	        );
	    }
}
