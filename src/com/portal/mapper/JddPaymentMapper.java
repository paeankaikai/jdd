package com.portal.mapper;

import com.portal.bean.JddPayment;
import com.portal.bean.JddPaymentQuery;

import java.util.List;

public interface JddPaymentMapper {

    int deleteByPrimaryKey(String guid);

    int insert(JddPayment record);

    int insertSelective(JddPayment record);

    int updateByPrimaryKeySelective(JddPayment record);

    int updateByPrimaryKey(JddPayment record);
    
	List<JddPayment> selectByWhere(JddPaymentQuery query);

	List<JddPayment> selectByWhereGroupBy(JddPaymentQuery query);
}