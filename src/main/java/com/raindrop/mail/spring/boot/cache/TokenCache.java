package com.raindrop.mail.spring.boot.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @name: com.raindrop.mail.spring.boot.cache.TokenCache.java
 * @description: 邮箱激活令牌
 * @author: Wang Liang
 * @create Time: 2018/6/28 20:12
 */
public class TokenCache {

	public static Map<String, String> tokenMap;
	
	static {
		initTokenMap();
	}

	private static void initTokenMap() {
		tokenMap = new HashMap<>();
		tokenMap.put("wangliang@qq.com", "111111");
		tokenMap.put("lvjia@qq.com", "222222");
	}

}
