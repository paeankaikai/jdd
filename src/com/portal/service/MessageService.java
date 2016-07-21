package com.portal.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.portal.bean.JddMessage;

public interface MessageService {

	/**
	 * 根据id删除数据
	 *
	 * @param id
	 * @return
	 */
	public Integer deleteById(String id);

	/**
	 * 批量删除
	 *
	 * @param clazz
	 * @param property
	 * @param values
	 * @return
	 */
	public Integer deleteByIds(List<Object> values);

	/**
	 * 根据条件删除数据
	 *
	 * @param record
	 * @return
	 */
	public Integer deleteByWhere(JddMessage record);

	/**
	 * 查询所有数据
	 *
	 * @return
	 */
	public List<JddMessage> queryAll();

	/**
	* 根据id查询数据
	*
	* @param id
	* @return
	*/
	public JddMessage queryById(String id);

	/**
	 * 根据条件查询数据列表
	 *
	 * @param record
	 * @return
	 */
	public List<JddMessage> queryListByWhere(JddMessage record);

	/**
	 * 根据条件查询一条数据
	 *
	 * @param record
	 * @return
	 */
	public JddMessage queryOne(JddMessage record);

	/**
	 * 分页查询数据列表
	 *
	 * @param page
	 * @param rows
	 * @param record
	 * @return
	 */
	public PageInfo<JddMessage> queryPageListByWhere(Integer page, Integer rows, com.portal.bean.JddMessage record);

	/**
	 * 新增数据
	 *
	 * @param t
	 * @return
	 */
	public Integer save(JddMessage t);

	/**
	 * 有选择的保存，选择不为null的字段作为插入字段
	 *
	 * @param t
	 * @return
	 */
	public Integer saveSelective(JddMessage t);

	/**
	 * 更新数据
	 *
	 * @param t
	 * @return
	 */
	public Integer update(JddMessage t);

	/**
	 * 有选择的更新，选择不为null的字段作为插入字段
	 *
	 * @param t
	 * @return
	 */
	public Integer updateSelective(JddMessage t);
}
