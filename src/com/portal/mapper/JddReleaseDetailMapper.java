package com.portal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.portal.bean.JddReleaseDetail;
import com.portal.bean.JddReleaseDetailExample;
import com.portal.bean.JddReleaseDetailQuery;

public interface JddReleaseDetailMapper {
 
    int deleteByPrimaryKey(String guid);

    int insert(JddReleaseDetail record);

    int insertSelective(JddReleaseDetail record);

   int updateByPrimaryKeySelective(JddReleaseDetail record);

    int updateByPrimaryKey(JddReleaseDetail record);
    
    
    List<JddReleaseDetail> selectByExample(JddReleaseDetailExample example);
    
    /**
     * 将规则、来源都转换了
     * Description:  <BR>  
     * @author cyk
     * @date 2016年6月13日 下午4:20:21
     * @param example
     * @return
     */
    List<JddReleaseDetail> selectByExample2(JddReleaseDetailExample example);

    JddReleaseDetail selectByPrimaryKey(String guid);

    int updateByExampleSelective(@Param("record") JddReleaseDetail record, @Param("example") JddReleaseDetailExample example);

    int updateByExample(@Param("record") JddReleaseDetail record, @Param("example") JddReleaseDetailExample example);

	List<JddReleaseDetail> selectByWhere(JddReleaseDetailQuery query);

	List<JddReleaseDetail> selectByWhereGroupBy(JddReleaseDetailQuery query);
    
}