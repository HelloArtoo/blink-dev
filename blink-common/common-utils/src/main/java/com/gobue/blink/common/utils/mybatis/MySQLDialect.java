package com.gobue.blink.common.utils.mybatis;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.Iterator;
import java.util.List;

public class MySQLDialect extends Dialect {
	
	final static String LIMIT_SQL_PATTERN = "%s limit %s offset %s";
	
	final static String LIMIT_SQL_PATTERN_FIRST = "%s limit %s";
	
	final static String ORDER_BY_PATTERN = "%s %s";

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		if (offset == 0) {
			return String.format(LIMIT_SQL_PATTERN_FIRST, sql, limit);
		}
		
		return String.format(LIMIT_SQL_PATTERN, sql, limit, offset);
	}

	@Override
	public String getOrderByString(String sql, Sort sort) {
		if(sort == null )
			return sql;
		
		List<String> orderByClause = Lists.newArrayList();
		for (Iterator<Order> iter = sort.iterator(); iter.hasNext();) {
			Order order = iter.next();
			String clause = String.format(ORDER_BY_PATTERN, order.getProperty(), order.getDirection().toString());
			orderByClause.add(clause);
		}
		
		if(orderByClause.isEmpty()){
			return sql;
		}
		
		String orderBySql = Joiner.on(",").skipNulls().join(orderByClause);

		StringBuffer buffer = new StringBuffer(sql);
		buffer.append(" ORDER BY ").append(orderBySql);
		
		return buffer.toString();
	}
	
	

}
