package com.portal.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.portal.bean.JddPresent;
import com.portal.bean.JddPresentQuery;
import com.portal.bean.JddPresentTotalInfo;


public interface JddPresentService {

	/**
	 * 根据id删除数据
	 *
	 * @param id
	 * @return
	 */
	Integer deleteById(String id);

	/**
	 * 查询所有数据
	 *
	 * @return
	 */
	List<JddPresent> queryAll();

	JddPresent queryById(String id);

	/**
	 * 根据条件查询数据列表
	 *
	 * @param record
	 * @return
	 */
	List<JddPresent> queryListByWhere(JddPresentQuery record);

	/**
	 * 根据条件查询一条数据
	 *
	 * @param record
	 * @return
	 */
	JddPresent queryOne(JddPresent record);

	/**
	 * 新增数据
	 *
	 * @param t
	 * @return
	 */
	Integer save(JddPresent t);

	/**
	 * 有选择的保存，选择不为null的字段作为插入字段
	 *
	 * @param t
	 * @return
	 */
	Integer saveSelective(JddPresent t);

	/**
	 * 更新数据
	 *
	 * @param t
	 * @return
	 */
	Integer update(JddPresent t);

	/**
	 * 有选择的更新，选择不为null的字段作为插入字段
	 *
	 * @param t
	 * @return
	 */
	Integer updateSelective(JddPresent t);
	
	/**
	 * 分页查询(支持order by 将要排序的信息放入 List中,没有则传入null)
	 *
	 * @param t
	 * @return
	 */
	PageInfo<JddPresent> queryPageListByWhere(Integer page, Integer rows, JddPresentQuery record, List<String> orderBy);
	
	/**
	 * 个人微送记录统计信息查询
	 * @param userId
	 * @return
	 */
	JddPresentTotalInfo queryTotalInfo(String userId);

}