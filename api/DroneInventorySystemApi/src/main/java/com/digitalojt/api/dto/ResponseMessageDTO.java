package com.digitalojt.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * レスポンスメッセージをJSON形式で返却する
 *
 */
@Data
@AllArgsConstructor
public class ResponseMessageDTO {

	private String message;
	private int status;
	private String token;
}