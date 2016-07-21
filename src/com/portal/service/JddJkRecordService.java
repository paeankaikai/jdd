package com.portal.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.portal.bean.JddJkrecord;
import com.portal.bean.JddJkrecordQuery;

public interface JddJkRecordService {

	List<JddJkrecord> selectByWhere(JddJkrecordQuery jddJkrecordQuery);
	
	/**
	 * 支出分页查询(支持order by 将要排序的信息放入 List中,没有则传入null)
	 *
	 * @param t
	 * @return
	 */
	PageInfo<JddJkrecord> queryPageListByWhere(Integer page, Integer rows, JddJkrecordQuery jddJkrecordQuery,
			List<String> orderBy);

	/**
	 * 统计已经兑换的吉课金币数和吉点点数
	 * @return
	 */
	JddJkrecord queryExchangeCountInfo();
	
	/**
	 * 支出分页查询(支持order by 将要排序的信息放入 List中,没有则传入null)--带序号
	 *
	 * @param t
	 * @return
	 */
	PageInfo<JddJkrecord> queryPageNum(Integer page, Integer rows, JddJkrecordQuery jddJkrecordQuery,
			List<String> orderBy);

	
	/**
	 * jk兑换jdd,已过时，现在调用存储过程
	 * Description:  <BR>  
	 * @author cyk
	 * @date 2016年6月17日 上午11:17:17
	 * @param jkRecord
	 * @return
	 */
	@Deprecated
	int insertExchange(JddJkrecord jkRecord);

	
}
