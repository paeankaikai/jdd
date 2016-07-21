package com.portal.mapper;

import java.util.Map;

public interface CommonMapper {

	
/**
 * 批量审批jdd	
 * Description:  <BR>  
 * @author cyk
 * @date 2016年6月14日 下午6:58:28
 * @param map
 * @return
 */
public	Map<String, Object> jddbaserecord(Map<String, Object> map);


/**
 * 登录判断用户
 * 初始化账户userInfo,首次登录送20点是否已送,保存在发放表里面
 * Description:  <BR>  
 * @author cyk
 * @date 2016年6月14日 下午6:59:00
 * @param map
 * @return
 */
public	Map<String, Object> jdduserinit(Map<String, Object> map);




/**
 *  --jk兑换jdd
  -- 成功返回1，失败返回0
  --参数说明：用户id、用户名称、jksum、jddsum、备注、返回值、返回信息
 * Description:  <BR>  
 * @author cyk
 * @date 2016年6月23日 下午2:42:44
 * @param map
 * @return
 */
public Map<String,Object> exchange(Map<String,Object> map);



}
