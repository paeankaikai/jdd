package com.portal.mapper;

import com.portal.bean.JddRule;
import com.portal.bean.JddRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JddRuleMapper {
    int countByExample(JddRuleExample example);

    int deleteByExample(JddRuleExample example);

    int deleteByPrimaryKey(String guid);

    int insert(JddRule record);

    int insertSelective(JddRule record);

    List<JddRule> selectByExample(JddRuleExample example);

    JddRule selectByPrimaryKey(String guid);

    int updateByExampleSelective(@Param("record") JddRule record, @Param("example") JddRuleExample example);

    int updateByExample(@Param("record") JddRule record, @Param("example") JddRuleExample example);

    int updateByPrimaryKeySelective(JddRule record);

    int updateByPrimaryKey(JddRule record);
}