package com.gobue.blink.common.utils.mybatis;

import org.springframework.data.domain.Sort;

public abstract class Dialect {

	public abstract String getLimitString(String sql, int offset, int limit);
	
	public abstract String getOrderByString(String sql, Sort sort);

	public String getCountString(String sql) {
		return "select count(1) from (" + sql + ") tmp_count";
	}
}
