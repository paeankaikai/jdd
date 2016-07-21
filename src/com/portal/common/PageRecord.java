package com.portal.common;

import java.io.Serializable;
import java.util.List;

public class PageRecord
  implements Serializable
{
  private static final long serialVersionUID = -5536675518695146874L;
  //查询到的总数
  private int totalRecord;
  //查询获取的记录结果集
  private List<?> recordList;

  public int getTotalRecord()
  {
    return this.totalRecord;
  }

  public void setTotalRecord(int totalRecord)
  {
    this.totalRecord = totalRecord;
  }

  public List<?> getRecordList()
  {
    return this.recordList;
  }

  public void setRecordList(List<?> recordList)
  {
    this.recordList = recordList;
  }
}