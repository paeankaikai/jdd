package com.portal.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JkReleaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public JkReleaseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andGuidIsNull() {
            addCriterion("GUID is null");
            return (Criteria) this;
        }

        public Criteria andGuidIsNotNull() {
            addCriterion("GUID is not null");
            return (Criteria) this;
        }

        public Criteria andGuidEqualTo(String value) {
            addCriterion("GUID =", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotEqualTo(String value) {
            addCriterion("GUID <>", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThan(String value) {
            addCriterion("GUID >", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThanOrEqualTo(String value) {
            addCriterion("GUID >=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThan(String value) {
            addCriterion("GUID <", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThanOrEqualTo(String value) {
            addCriterion("GUID <=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLike(String value) {
            addCriterion("GUID like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotLike(String value) {
            addCriterion("GUID not like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidIn(List<String> values) {
            addCriterion("GUID in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotIn(List<String> values) {
            addCriterion("GUID not in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidBetween(String value1, String value2) {
            addCriterion("GUID between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotBetween(String value1, String value2) {
            addCriterion("GUID not between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andStstusIsNull() {
            addCriterion("STSTUS is null");
            return (Criteria) this;
        }

        public Criteria andStstusIsNotNull() {
            addCriterion("STSTUS is not null");
            return (Criteria) this;
        }

        public Criteria andStstusEqualTo(Integer value) {
            addCriterion("STSTUS =", value, "ststus");
            return (Criteria) this;
        }

        public Criteria andStstusNotEqualTo(Integer value) {
            addCriterion("STSTUS <>", value, "ststus");
            return (Criteria) this;
        }

        public Criteria andStstusGreaterThan(Integer value) {
            addCriterion("STSTUS >", value, "ststus");
            return (Criteria) this;
        }

        public Criteria andStstusGreaterThanOrEqualTo(Integer value) {
            addCriterion("STSTUS >=", value, "ststus");
            return (Criteria) this;
        }

        public Criteria andStstusLessThan(Integer value) {
            addCriterion("STSTUS <", value, "ststus");
            return (Criteria) this;
        }

        public Criteria andStstusLessThanOrEqualTo(Integer value) {
            addCriterion("STSTUS <=", value, "ststus");
            return (Criteria) this;
        }

        public Criteria andStstusIn(List<Integer> values) {
            addCriterion("STSTUS in", values, "ststus");
            return (Criteria) this;
        }

        public Criteria andStstusNotIn(List<Integer> values) {
            addCriterion("STSTUS not in", values, "ststus");
            return (Criteria) this;
        }

        public Criteria andStstusBetween(Integer value1, Integer value2) {
            addCriterion("STSTUS between", value1, value2, "ststus");
            return (Criteria) this;
        }

        public Criteria andStstusNotBetween(Integer value1, Integer value2) {
            addCriterion("STSTUS not between", value1, value2, "ststus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNull() {
            addCriterion("CREATE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCreateNameIsNotNull() {
            addCriterion("CREATE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateNameEqualTo(String value) {
            addCriterion("CREATE_NAME =", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotEqualTo(String value) {
            addCriterion("CREATE_NAME <>", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThan(String value) {
            addCriterion("CREATE_NAME >", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME >=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThan(String value) {
            addCriterion("CREATE_NAME <", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLessThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME <=", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameLike(String value) {
            addCriterion("CREATE_NAME like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotLike(String value) {
            addCriterion("CREATE_NAME not like", value, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameIn(List<String> values) {
            addCriterion("CREATE_NAME in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotIn(List<String> values) {
            addCriterion("CREATE_NAME not in", values, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameBetween(String value1, String value2) {
            addCriterion("CREATE_NAME between", value1, value2, "createName");
            return (Criteria) this;
        }

        public Criteria andCreateNameNotBetween(String value1, String value2) {
            addCriterion("CREATE_NAME not between", value1, value2, "createName");
            return (Criteria) this;
        }

        public Criteria andCheckByIsNull() {
            addCriterion("CHECK_BY is null");
            return (Criteria) this;
        }

        public Criteria andCheckByIsNotNull() {
            addCriterion("CHECK_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCheckByEqualTo(String value) {
            addCriterion("CHECK_BY =", value, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByNotEqualTo(String value) {
            addCriterion("CHECK_BY <>", value, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByGreaterThan(String value) {
            addCriterion("CHECK_BY >", value, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_BY >=", value, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByLessThan(String value) {
            addCriterion("CHECK_BY <", value, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByLessThanOrEqualTo(String value) {
            addCriterion("CHECK_BY <=", value, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByLike(String value) {
            addCriterion("CHECK_BY like", value, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByNotLike(String value) {
            addCriterion("CHECK_BY not like", value, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByIn(List<String> values) {
            addCriterion("CHECK_BY in", values, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByNotIn(List<String> values) {
            addCriterion("CHECK_BY not in", values, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByBetween(String value1, String value2) {
            addCriterion("CHECK_BY between", value1, value2, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckByNotBetween(String value1, String value2) {
            addCriterion("CHECK_BY not between", value1, value2, "checkBy");
            return (Criteria) this;
        }

        public Criteria andCheckNameIsNull() {
            addCriterion("CHECK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCheckNameIsNotNull() {
            addCriterion("CHECK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckNameEqualTo(String value) {
            addCriterion("CHECK_NAME =", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotEqualTo(String value) {
            addCriterion("CHECK_NAME <>", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameGreaterThan(String value) {
            addCriterion("CHECK_NAME >", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_NAME >=", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameLessThan(String value) {
            addCriterion("CHECK_NAME <", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameLessThanOrEqualTo(String value) {
            addCriterion("CHECK_NAME <=", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameLike(String value) {
            addCriterion("CHECK_NAME like", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotLike(String value) {
            addCriterion("CHECK_NAME not like", value, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameIn(List<String> values) {
            addCriterion("CHECK_NAME in", values, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotIn(List<String> values) {
            addCriterion("CHECK_NAME not in", values, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameBetween(String value1, String value2) {
            addCriterion("CHECK_NAME between", value1, value2, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckNameNotBetween(String value1, String value2) {
            addCriterion("CHECK_NAME not between", value1, value2, "checkName");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("CHECK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("CHECK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("CHECK_TIME =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("CHECK_TIME <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("CHECK_TIME >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CHECK_TIME >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("CHECK_TIME <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("CHECK_TIME <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("CHECK_TIME in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("CHECK_TIME not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("CHECK_TIME between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("CHECK_TIME not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andExcelPathIsNull() {
            addCriterion("EXCEL_PATH is null");
            return (Criteria) this;
        }

        public Criteria andExcelPathIsNotNull() {
            addCriterion("EXCEL_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andExcelPathEqualTo(String value) {
            addCriterion("EXCEL_PATH =", value, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathNotEqualTo(String value) {
            addCriterion("EXCEL_PATH <>", value, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathGreaterThan(String value) {
            addCriterion("EXCEL_PATH >", value, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathGreaterThanOrEqualTo(String value) {
            addCriterion("EXCEL_PATH >=", value, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathLessThan(String value) {
            addCriterion("EXCEL_PATH <", value, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathLessThanOrEqualTo(String value) {
            addCriterion("EXCEL_PATH <=", value, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathLike(String value) {
            addCriterion("EXCEL_PATH like", value, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathNotLike(String value) {
            addCriterion("EXCEL_PATH not like", value, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathIn(List<String> values) {
            addCriterion("EXCEL_PATH in", values, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathNotIn(List<String> values) {
            addCriterion("EXCEL_PATH not in", values, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathBetween(String value1, String value2) {
            addCriterion("EXCEL_PATH between", value1, value2, "excelPath");
            return (Criteria) this;
        }

        public Criteria andExcelPathNotBetween(String value1, String value2) {
            addCriterion("EXCEL_PATH not between", value1, value2, "excelPath");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andGuidLikeInsensitive(String value) {
            addCriterion("upper(GUID) like", value.toUpperCase(), "guid");
            return this;
        }

        public Criteria andCreateByLikeInsensitive(String value) {
            addCriterion("upper(CREATE_BY) like", value.toUpperCase(), "createBy");
            return this;
        }

        public Criteria andCreateNameLikeInsensitive(String value) {
            addCriterion("upper(CREATE_NAME) like", value.toUpperCase(), "createName");
            return this;
        }

        public Criteria andCheckByLikeInsensitive(String value) {
            addCriterion("upper(CHECK_BY) like", value.toUpperCase(), "checkBy");
            return this;
        }

        public Criteria andCheckNameLikeInsensitive(String value) {
            addCriterion("upper(CHECK_NAME) like", value.toUpperCase(), "checkName");
            return this;
        }

        public Criteria andExcelPathLikeInsensitive(String value) {
            addCriterion("upper(EXCEL_PATH) like", value.toUpperCase(), "excelPath");
            return this;
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}