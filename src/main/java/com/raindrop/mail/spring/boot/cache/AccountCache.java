package com.raindrop.mail.spring.boot.cache;

import com.raindrop.mail.spring.boot.model.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * @name: com.raindrop.mail.spring.boot.cache.AccountCache.java
 * @description: 模拟数据库数据
 * @author: Wang Liang
 * @create Time: 2018/6/28 19:37
 */
public class AccountCache {

	public static Map<String, Account> accountMap;

	static {
		initAccountMap();
	}

	private static void initAccountMap() {
		accountMap = new HashMap<>();
		Account account1 = new Account("wangliang@qq.com", "q84518936", "0");
		Account account2 = new Account("lvjia@qq.com", "q84518936", "1");
		accountMap.put("wangliang@qq.com", account1);
		accountMap.put("lvjia@qq.com", account2);
	}

}
