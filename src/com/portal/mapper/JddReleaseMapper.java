package com.portal.mapper;

import com.portal.bean.JddRelease;
import com.portal.bean.JddReleaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JddReleaseMapper {
 
    int deleteByPrimaryKey(String guid);

    int insert(JddRelease record);

    int insertSelective(JddRelease record);
    
    List<JddRelease> selectByExample(JddReleaseExample example);
    
    int countByExample(JddReleaseExample example);
 
    int updateByPrimaryKeySelective(JddRelease record);

    int updateByPrimaryKey(JddRelease record);
    
    JddRelease selectByPrimaryKey(String guid);
}