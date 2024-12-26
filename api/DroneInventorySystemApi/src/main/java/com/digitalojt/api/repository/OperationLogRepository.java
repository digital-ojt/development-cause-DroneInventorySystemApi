package com.digitalojt.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalojt.api.entity.OperationLog;

/**
 * 操作ログリポジトリー
 * 
 * @author your name
 *
 */

public interface OperationLogRepository extends JpaRepository<OperationLog, Integer> {
}
