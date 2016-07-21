package com.portal.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.portal.bean.JddPayment;
import com.portal.bean.JddPaymentQuery;


public interface JddPaymentService {
	/**
	 * 有选择的保存，选择不为null的字段作为插入字段
	 *
	 * @param t
	 * @return
	 */
	Integer saveSelective(JddPayment t);

	PageInfo<JddPayment> queryPageListByWhereGroupBy(Integer page,
			Integer rows, JddPaymentQuery query, List<String> orderBy);

	PageInfo<JddPayment> queryPageListByWhere(Integer page, Integer rows,
			JddPaymentQuery query, List<String> orderBy);

	List<JddPayment> selectByWhereGroupBy(JddPaymentQuery query);

	List<JddPayment> selectByWhere(JddPaymentQuery query);

}