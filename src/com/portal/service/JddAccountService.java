package com.portal.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.portal.bean.JddAccount;

public interface JddAccountService {

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
	List<JddAccount> queryAll();
	/**
	 * 根据id查询
	 *
	 * @return
	 */
	JddAccount queryById(String id);
	
	/**
	 * 根据userId查询
	 *
	 * @return
	 */
	JddAccount queryByUserId(String userId);

	/**
	 * 根据条件查询数据列表
	 *
	 * @param record
	 * @return
	 */
	List<JddAccount> queryListByWhere(JddAccount record);

	/**
	 * 根据条件查询一条数据
	 *
	 * @param record
	 * @return
	 */
	JddAccount queryOne(JddAccount record);

	/**
	 * 新增数据
	 *
	 * @param t
	 * @return
	 */
	Integer save(JddAccount t);

	/**
	 * 有选择的保存，选择不为null的字段作为插入字段
	 *
	 * @param t
	 * @return
	 */
	Integer saveSelective(JddAccount t);

	/**
	 * 更新数据
	 *
	 * @param t
	 * @return
	 */
	Integer update(JddAccount t);

	/**
	 * 有选择的更新，选择不为null的字段作为插入字段
	 *
	 * @param t
	 * @return
	 */
	Integer updateSelective(JddAccount t);
	
	/**
	 * 分页查询(支持order by 将要排序的信息放入 List中,没有则传入null)
	 *
	 * @param t
	 * @return
	 */
	PageInfo<JddAccount> queryPageListByWhere(Integer page, Integer rows, JddAccount record,
			List<String> orderBy);
	
	
	/**
	 * 分页查询(根据剩余的吉点点数量排序)
	 *
	 * @param t
	 * @return
	 */
	PageInfo<JddAccount> queryPageNum(Integer page, Integer rows, JddAccount record,
			List<String> orderBy);

}