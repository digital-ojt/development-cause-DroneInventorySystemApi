package com.digitalojt.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作ログEntity
 * 
 * @author your name
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "operation_log")
public class OperationLog {
	// 操作ログID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int logId;
    // 管理者ID
    @Column(name = "admin_id", nullable = false)
    private String adminId;
    // 操作種別
    @Column(name = "operate_type", nullable = false)
    private String operateType;
    // テーブルID
    @Column(name = "table_id", nullable = false)
    private int tableId;
    // 対象ID
    @Column(name = "target_id", nullable = false)
    private int targetId;
    // 操作詳細
    @Column(name = "operation_details")
    private String operationDetails;
    // ステータス
    @Column(name = "status", nullable = false)
    private String status;
    // 削除フラグ
    @Column(name = "delete_flag", nullable = false)
    private String deleteFlag;
    // 作成日時
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
    // 更新日時
    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

}
