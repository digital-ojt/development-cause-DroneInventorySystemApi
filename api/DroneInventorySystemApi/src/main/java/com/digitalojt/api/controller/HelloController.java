package com.digitalojt.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ユーザエンドポイントとなるControllerクラス
 * HTTPリクエストを受け付けて「Hello World!」と返す
 * 
 * @author your name
 *
 */

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String home() {
		return "Hello World!";
	}

}