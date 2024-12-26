package com.digitalojt.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalojt.api.entity.OperationLog;
import com.digitalojt.api.repository.OperationLogRepository;

/**
 * 操作ログサービス
 * 
 * @author your name
 *
 */

@Service
public class OperationLogService {
	// 操作ログリポジトリーをインジェクション
    @Autowired
    private OperationLogRepository operationLogRepository;
    // 操作ログを登録
    public void logOperation(String adminId, String operateType, int tableId, int targetId, String operationDetails, String status, String deleteFlag) {
        OperationLog log = new OperationLog();
        log.setAdminId(adminId);
        log.setOperateType(operateType);
        log.setTableId(tableId);
        log.setTargetId(targetId);
        log.setOperationDetails(operationDetails);
        log.setStatus(status);
        log.setDeleteFlag(deleteFlag);
        log.setCreateDate(LocalDateTime.now());
        log.setUpdateDate(LocalDateTime.now());
        operationLogRepository.save(log);
    }
}