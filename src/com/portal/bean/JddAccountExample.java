package com.portal.bean;

import java.util.ArrayList;
import java.util.List;

public class JddAccountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public JddAccountExample() {
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

        public Criteria andYearNoIsNull() {
            addCriterion("YEAR_NO is null");
            return (Criteria) this;
        }

        public Criteria andYearNoIsNotNull() {
            addCriterion("YEAR_NO is not null");
            return (Criteria) this;
        }

        public Criteria andYearNoEqualTo(String value) {
            addCriterion("YEAR_NO =", value, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoNotEqualTo(String value) {
            addCriterion("YEAR_NO <>", value, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoGreaterThan(String value) {
            addCriterion("YEAR_NO >", value, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoGreaterThanOrEqualTo(String value) {
            addCriterion("YEAR_NO >=", value, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoLessThan(String value) {
            addCriterion("YEAR_NO <", value, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoLessThanOrEqualTo(String value) {
            addCriterion("YEAR_NO <=", value, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoLike(String value) {
            addCriterion("YEAR_NO like", value, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoNotLike(String value) {
            addCriterion("YEAR_NO not like", value, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoIn(List<String> values) {
            addCriterion("YEAR_NO in", values, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoNotIn(List<String> values) {
            addCriterion("YEAR_NO not in", values, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoBetween(String value1, String value2) {
            addCriterion("YEAR_NO between", value1, value2, "yearNo");
            return (Criteria) this;
        }

        public Criteria andYearNoNotBetween(String value1, String value2) {
            addCriterion("YEAR_NO not between", value1, value2, "yearNo");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andLyNumIsNull() {
            addCriterion("LY_NUM is null");
            return (Criteria) this;
        }

        public Criteria andLyNumIsNotNull() {
            addCriterion("LY_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andLyNumEqualTo(Integer value) {
            addCriterion("LY_NUM =", value, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyNumNotEqualTo(Integer value) {
            addCriterion("LY_NUM <>", value, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyNumGreaterThan(Integer value) {
            addCriterion("LY_NUM >", value, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("LY_NUM >=", value, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyNumLessThan(Integer value) {
            addCriterion("LY_NUM <", value, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyNumLessThanOrEqualTo(Integer value) {
            addCriterion("LY_NUM <=", value, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyNumIn(List<Integer> values) {
            addCriterion("LY_NUM in", values, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyNumNotIn(List<Integer> values) {
            addCriterion("LY_NUM not in", values, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyNumBetween(Integer value1, Integer value2) {
            addCriterion("LY_NUM between", value1, value2, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyNumNotBetween(Integer value1, Integer value2) {
            addCriterion("LY_NUM not between", value1, value2, "lyNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumIsNull() {
            addCriterion("LY_COST_NUM is null");
            return (Criteria) this;
        }

        public Criteria andLyCostNumIsNotNull() {
            addCriterion("LY_COST_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andLyCostNumEqualTo(Integer value) {
            addCriterion("LY_COST_NUM =", value, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumNotEqualTo(Integer value) {
            addCriterion("LY_COST_NUM <>", value, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumGreaterThan(Integer value) {
            addCriterion("LY_COST_NUM >", value, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("LY_COST_NUM >=", value, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumLessThan(Integer value) {
            addCriterion("LY_COST_NUM <", value, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumLessThanOrEqualTo(Integer value) {
            addCriterion("LY_COST_NUM <=", value, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumIn(List<Integer> values) {
            addCriterion("LY_COST_NUM in", values, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumNotIn(List<Integer> values) {
            addCriterion("LY_COST_NUM not in", values, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumBetween(Integer value1, Integer value2) {
            addCriterion("LY_COST_NUM between", value1, value2, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andLyCostNumNotBetween(Integer value1, Integer value2) {
            addCriterion("LY_COST_NUM not between", value1, value2, "lyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyNumIsNull() {
            addCriterion("CY_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCyNumIsNotNull() {
            addCriterion("CY_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCyNumEqualTo(Integer value) {
            addCriterion("CY_NUM =", value, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyNumNotEqualTo(Integer value) {
            addCriterion("CY_NUM <>", value, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyNumGreaterThan(Integer value) {
            addCriterion("CY_NUM >", value, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("CY_NUM >=", value, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyNumLessThan(Integer value) {
            addCriterion("CY_NUM <", value, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyNumLessThanOrEqualTo(Integer value) {
            addCriterion("CY_NUM <=", value, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyNumIn(List<Integer> values) {
            addCriterion("CY_NUM in", values, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyNumNotIn(List<Integer> values) {
            addCriterion("CY_NUM not in", values, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyNumBetween(Integer value1, Integer value2) {
            addCriterion("CY_NUM between", value1, value2, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyNumNotBetween(Integer value1, Integer value2) {
            addCriterion("CY_NUM not between", value1, value2, "cyNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumIsNull() {
            addCriterion("CY_COST_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCyCostNumIsNotNull() {
            addCriterion("CY_COST_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCyCostNumEqualTo(Integer value) {
            addCriterion("CY_COST_NUM =", value, "cyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumNotEqualTo(Integer value) {
            addCriterion("CY_COST_NUM <>", value, "cyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumGreaterThan(Integer value) {
            addCriterion("CY_COST_NUM >", value, "cyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("CY_COST_NUM >=", value, "cyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumLessThan(Integer value) {
            addCriterion("CY_COST_NUM <", value, "cyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumLessThanOrEqualTo(Integer value) {
            addCriterion("CY_COST_NUM <=", value, "cyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumIn(List<Integer> values) {
            addCriterion("CY_COST_NUM in", values, "cyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumNotIn(List<Integer> values) {
            addCriterion("CY_COST_NUM not in", values, "cyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumBetween(Integer value1, Integer value2) {
            addCriterion("CY_COST_NUM between", value1, value2, "cyCostNum");
            return (Criteria) this;
        }

        public Criteria andCyCostNumNotBetween(Integer value1, Integer value2) {
            addCriterion("CY_COST_NUM not between", value1, value2, "cyCostNum");
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

        public Criteria andYearNoLikeInsensitive(String value) {
            addCriterion("upper(YEAR_NO) like", value.toUpperCase(), "yearNo");
            return this;
        }

        public Criteria andUserIdLikeInsensitive(String value) {
            addCriterion("upper(USER_ID) like", value.toUpperCase(), "userId");
            return this;
        }

        public Criteria andUserNameLikeInsensitive(String value) {
            addCriterion("upper(USER_NAME) like", value.toUpperCase(), "userName");
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