package com.digitalojt.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 管理者情報Entity
 * 
 * @author your name
 *
 */

@Entity
@Data
public class AdminInfo {
    @Id
    private String adminId;
    private String password;
}