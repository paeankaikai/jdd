package com.portal.mapper;

import com.portal.bean.JkRelease;
import com.portal.bean.JkReleaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JkReleaseMapper {
  
    int deleteByPrimaryKey(String guid);

    int insert(JkRelease record);

    int insertSelective(JkRelease record);

 
    int updateByPrimaryKeySelective(JkRelease record);

    int updateByPrimaryKey(JkRelease record);
    
    List<JkRelease> selectByExample(JkReleaseExample example);
    
    
    JkRelease selectByPrimaryKey(String guid);
    
    
}