package com.digitalojt.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digitalojt.api.entity.StockInfo;

/**
 * 在庫情報Repository
 * 
 * @author your name
 *
 */

public interface StockInfoRepository extends JpaRepository<StockInfo, Integer> 
{
    // 名前で検索
    List<StockInfo> findByName(String name);
    
    // 分類IDで検索
    @Query("SELECT s FROM StockInfo s WHERE s.categoryInfo.id = :categoryId")
    List<StockInfo> findByCategoryId(@Param("categoryId") int categoryId);
    
    // 分類IDと名前と個数で検索
    @Query("SELECT s FROM StockInfo s WHERE "
            + "(:categoryId IS NULL OR s.categoryInfo.id = :categoryId) AND "
            + "(:name IS NULL OR s.name LIKE :name%) AND "
            + "(:amountMin IS NULL OR s.amount >= :amountMin) AND "
            + "(:amountMax IS NULL OR s.amount <= :amountMax)")
    List<StockInfo> findByCategoryIdAndNameAndAmountRange(
            @Param("categoryId") Integer categoryId,
            @Param("name") String name,
            @Param("amountMin") Integer amountMin,
            @Param("amountMax") Integer amountMax);
}