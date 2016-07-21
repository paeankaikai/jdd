package com.portal.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.portal.bean.JddBaserecord;
import com.portal.bean.JddBaserecordQuery;

public interface BaseRecordService {

	List<JddBaserecord> selectByWhere(JddBaserecordQuery jddBaserecordQuery);

	List<JddBaserecord> selectByWhereGroupBy(JddBaserecordQuery jddBaserecordQuery);
	
	/**
	 * 支出分页查询(支持order by 将要排序的信息放入 List中,没有则传入null)
	 *
	 * @param t
	 * @return
	 */
	PageInfo<JddBaserecord> queryPageListByWhere(Integer page, Integer rows, JddBaserecordQuery jddBaserecordQuery,
			List<String> orderBy);
	/**
	 * 添加行号
	 * @param page
	 * @param rows
	 * @param jddBaserecordQuery
	 * @param orderBy
	 * @return
	 */
	PageInfo<JddBaserecord> queryPageAndNum(Integer page, Integer rows, JddBaserecordQuery jddBaserecordQuery,
			List<String> orderBy);
	
	/**
	 * 支出分页分组查询(支持order by 将要排序的信息放入 List中,没有则传入null)
	 *
	 * @param t
	 * @return
	 */
	PageInfo<JddBaserecord> queryPageListByWhereGroupBy(Integer page, Integer rows, JddBaserecordQuery jddBaserecordQuery,
			List<String> orderBy);

	/**
	 * 获取收支记录入表中的收入或支出的三级类型
	 * @param ruleType
	 * @return
	 */
	List<String> queryAllRuleChilds(Integer ruleType);
	
	/**
	 * 获取收支记录入表中的来源类型
	 * @param ruleType
	 * @return
	 */
	List<String> queryAllcostSources(Integer ruleType);
	
	/**
	 * 获得某个人的所有获得/支出吉点点的记录的总数
	 * @param record
	 * @return
	 */
	int selectTotalNum(JddBaserecordQuery record);

	/**
	 * 新增
	 * @param jddBaserecord
	 * @return
	 */
	int insert(JddBaserecord jddBaserecord);

}
