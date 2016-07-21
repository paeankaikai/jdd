package com.portal.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public UserInfoExample() {
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

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(BigDecimal value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(BigDecimal value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(BigDecimal value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(BigDecimal value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<BigDecimal> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<BigDecimal> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andJkSumIsNull() {
            addCriterion("JK_SUM is null");
            return (Criteria) this;
        }

        public Criteria andJkSumIsNotNull() {
            addCriterion("JK_SUM is not null");
            return (Criteria) this;
        }

        public Criteria andJkSumEqualTo(BigDecimal value) {
            addCriterion("JK_SUM =", value, "jkSum");
            return (Criteria) this;
        }

        public Criteria andJkSumNotEqualTo(BigDecimal value) {
            addCriterion("JK_SUM <>", value, "jkSum");
            return (Criteria) this;
        }

        public Criteria andJkSumGreaterThan(BigDecimal value) {
            addCriterion("JK_SUM >", value, "jkSum");
            return (Criteria) this;
        }

        public Criteria andJkSumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("JK_SUM >=", value, "jkSum");
            return (Criteria) this;
        }

        public Criteria andJkSumLessThan(BigDecimal value) {
            addCriterion("JK_SUM <", value, "jkSum");
            return (Criteria) this;
        }

        public Criteria andJkSumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("JK_SUM <=", value, "jkSum");
            return (Criteria) this;
        }

        public Criteria andJkSumIn(List<BigDecimal> values) {
            addCriterion("JK_SUM in", values, "jkSum");
            return (Criteria) this;
        }

        public Criteria andJkSumNotIn(List<BigDecimal> values) {
            addCriterion("JK_SUM not in", values, "jkSum");
            return (Criteria) this;
        }

        public Criteria andJkSumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JK_SUM between", value1, value2, "jkSum");
            return (Criteria) this;
        }

        public Criteria andJkSumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JK_SUM not between", value1, value2, "jkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumIsNull() {
            addCriterion("USED_JK_SUM is null");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumIsNotNull() {
            addCriterion("USED_JK_SUM is not null");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumEqualTo(BigDecimal value) {
            addCriterion("USED_JK_SUM =", value, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumNotEqualTo(BigDecimal value) {
            addCriterion("USED_JK_SUM <>", value, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumGreaterThan(BigDecimal value) {
            addCriterion("USED_JK_SUM >", value, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("USED_JK_SUM >=", value, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumLessThan(BigDecimal value) {
            addCriterion("USED_JK_SUM <", value, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("USED_JK_SUM <=", value, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumIn(List<BigDecimal> values) {
            addCriterion("USED_JK_SUM in", values, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumNotIn(List<BigDecimal> values) {
            addCriterion("USED_JK_SUM not in", values, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("USED_JK_SUM between", value1, value2, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUsedJkSumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("USED_JK_SUM not between", value1, value2, "usedJkSum");
            return (Criteria) this;
        }

        public Criteria andUserComIsNull() {
            addCriterion("USER_COM is null");
            return (Criteria) this;
        }

        public Criteria andUserComIsNotNull() {
            addCriterion("USER_COM is not null");
            return (Criteria) this;
        }

        public Criteria andUserComEqualTo(String value) {
            addCriterion("USER_COM =", value, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComNotEqualTo(String value) {
            addCriterion("USER_COM <>", value, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComGreaterThan(String value) {
            addCriterion("USER_COM >", value, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComGreaterThanOrEqualTo(String value) {
            addCriterion("USER_COM >=", value, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComLessThan(String value) {
            addCriterion("USER_COM <", value, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComLessThanOrEqualTo(String value) {
            addCriterion("USER_COM <=", value, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComLike(String value) {
            addCriterion("USER_COM like", value, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComNotLike(String value) {
            addCriterion("USER_COM not like", value, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComIn(List<String> values) {
            addCriterion("USER_COM in", values, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComNotIn(List<String> values) {
            addCriterion("USER_COM not in", values, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComBetween(String value1, String value2) {
            addCriterion("USER_COM between", value1, value2, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserComNotBetween(String value1, String value2) {
            addCriterion("USER_COM not between", value1, value2, "userCom");
            return (Criteria) this;
        }

        public Criteria andUserPositionIsNull() {
            addCriterion("USER_POSITION is null");
            return (Criteria) this;
        }

        public Criteria andUserPositionIsNotNull() {
            addCriterion("USER_POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andUserPositionEqualTo(String value) {
            addCriterion("USER_POSITION =", value, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionNotEqualTo(String value) {
            addCriterion("USER_POSITION <>", value, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionGreaterThan(String value) {
            addCriterion("USER_POSITION >", value, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionGreaterThanOrEqualTo(String value) {
            addCriterion("USER_POSITION >=", value, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionLessThan(String value) {
            addCriterion("USER_POSITION <", value, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionLessThanOrEqualTo(String value) {
            addCriterion("USER_POSITION <=", value, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionLike(String value) {
            addCriterion("USER_POSITION like", value, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionNotLike(String value) {
            addCriterion("USER_POSITION not like", value, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionIn(List<String> values) {
            addCriterion("USER_POSITION in", values, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionNotIn(List<String> values) {
            addCriterion("USER_POSITION not in", values, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionBetween(String value1, String value2) {
            addCriterion("USER_POSITION between", value1, value2, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserPositionNotBetween(String value1, String value2) {
            addCriterion("USER_POSITION not between", value1, value2, "userPosition");
            return (Criteria) this;
        }

        public Criteria andUserUnitIsNull() {
            addCriterion("USER_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andUserUnitIsNotNull() {
            addCriterion("USER_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andUserUnitEqualTo(String value) {
            addCriterion("USER_UNIT =", value, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitNotEqualTo(String value) {
            addCriterion("USER_UNIT <>", value, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitGreaterThan(String value) {
            addCriterion("USER_UNIT >", value, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitGreaterThanOrEqualTo(String value) {
            addCriterion("USER_UNIT >=", value, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitLessThan(String value) {
            addCriterion("USER_UNIT <", value, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitLessThanOrEqualTo(String value) {
            addCriterion("USER_UNIT <=", value, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitLike(String value) {
            addCriterion("USER_UNIT like", value, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitNotLike(String value) {
            addCriterion("USER_UNIT not like", value, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitIn(List<String> values) {
            addCriterion("USER_UNIT in", values, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitNotIn(List<String> values) {
            addCriterion("USER_UNIT not in", values, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitBetween(String value1, String value2) {
            addCriterion("USER_UNIT between", value1, value2, "userUnit");
            return (Criteria) this;
        }

        public Criteria andUserUnitNotBetween(String value1, String value2) {
            addCriterion("USER_UNIT not between", value1, value2, "userUnit");
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

        public Criteria andUserIdLikeInsensitive(String value) {
            addCriterion("upper(USER_ID) like", value.toUpperCase(), "userId");
            return this;
        }

        public Criteria andUserNameLikeInsensitive(String value) {
            addCriterion("upper(USER_NAME) like", value.toUpperCase(), "userName");
            return this;
        }

        public Criteria andUserComLikeInsensitive(String value) {
            addCriterion("upper(USER_COM) like", value.toUpperCase(), "userCom");
            return this;
        }

        public Criteria andUserPositionLikeInsensitive(String value) {
            addCriterion("upper(USER_POSITION) like", value.toUpperCase(), "userPosition");
            return this;
        }

        public Criteria andUserUnitLikeInsensitive(String value) {
            addCriterion("upper(USER_UNIT) like", value.toUpperCase(), "userUnit");
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