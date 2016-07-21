package com.portal.mapper;

import com.portal.bean.JkReleaseDetail;
import com.portal.bean.JkReleaseDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkReleaseDetailMapper {
 
    int deleteByPrimaryKey(String guid);

    int insert(JkReleaseDetail record);

    int insertSelective(JkReleaseDetail record);

 
    int updateByPrimaryKeySelective(JkReleaseDetail record);

    int updateByPrimaryKey(JkReleaseDetail record);
    
    
    List<JkReleaseDetail> selectByExample(JkReleaseDetailExample exampe);
}