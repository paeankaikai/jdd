package com.portal.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JddPresentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public JddPresentExample() {
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

        public Criteria andFromUserIdIsNull() {
            addCriterion("FROM_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIsNotNull() {
            addCriterion("FROM_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFromUserIdEqualTo(String value) {
            addCriterion("FROM_USER_ID =", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotEqualTo(String value) {
            addCriterion("FROM_USER_ID <>", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThan(String value) {
            addCriterion("FROM_USER_ID >", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_USER_ID >=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThan(String value) {
            addCriterion("FROM_USER_ID <", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLessThanOrEqualTo(String value) {
            addCriterion("FROM_USER_ID <=", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdLike(String value) {
            addCriterion("FROM_USER_ID like", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotLike(String value) {
            addCriterion("FROM_USER_ID not like", value, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdIn(List<String> values) {
            addCriterion("FROM_USER_ID in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotIn(List<String> values) {
            addCriterion("FROM_USER_ID not in", values, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdBetween(String value1, String value2) {
            addCriterion("FROM_USER_ID between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserIdNotBetween(String value1, String value2) {
            addCriterion("FROM_USER_ID not between", value1, value2, "fromUserId");
            return (Criteria) this;
        }

        public Criteria andFromUserNameIsNull() {
            addCriterion("FROM_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFromUserNameIsNotNull() {
            addCriterion("FROM_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFromUserNameEqualTo(String value) {
            addCriterion("FROM_USER_NAME =", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameNotEqualTo(String value) {
            addCriterion("FROM_USER_NAME <>", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameGreaterThan(String value) {
            addCriterion("FROM_USER_NAME >", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_USER_NAME >=", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameLessThan(String value) {
            addCriterion("FROM_USER_NAME <", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameLessThanOrEqualTo(String value) {
            addCriterion("FROM_USER_NAME <=", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameLike(String value) {
            addCriterion("FROM_USER_NAME like", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameNotLike(String value) {
            addCriterion("FROM_USER_NAME not like", value, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameIn(List<String> values) {
            addCriterion("FROM_USER_NAME in", values, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameNotIn(List<String> values) {
            addCriterion("FROM_USER_NAME not in", values, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameBetween(String value1, String value2) {
            addCriterion("FROM_USER_NAME between", value1, value2, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andFromUserNameNotBetween(String value1, String value2) {
            addCriterion("FROM_USER_NAME not between", value1, value2, "fromUserName");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNull() {
            addCriterion("TO_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNotNull() {
            addCriterion("TO_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andToUserIdEqualTo(String value) {
            addCriterion("TO_USER_ID =", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotEqualTo(String value) {
            addCriterion("TO_USER_ID <>", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThan(String value) {
            addCriterion("TO_USER_ID >", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("TO_USER_ID >=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThan(String value) {
            addCriterion("TO_USER_ID <", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThanOrEqualTo(String value) {
            addCriterion("TO_USER_ID <=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLike(String value) {
            addCriterion("TO_USER_ID like", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotLike(String value) {
            addCriterion("TO_USER_ID not like", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIn(List<String> values) {
            addCriterion("TO_USER_ID in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotIn(List<String> values) {
            addCriterion("TO_USER_ID not in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdBetween(String value1, String value2) {
            addCriterion("TO_USER_ID between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotBetween(String value1, String value2) {
            addCriterion("TO_USER_ID not between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserNameIsNull() {
            addCriterion("TO_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andToUserNameIsNotNull() {
            addCriterion("TO_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andToUserNameEqualTo(String value) {
            addCriterion("TO_USER_NAME =", value, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameNotEqualTo(String value) {
            addCriterion("TO_USER_NAME <>", value, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameGreaterThan(String value) {
            addCriterion("TO_USER_NAME >", value, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("TO_USER_NAME >=", value, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameLessThan(String value) {
            addCriterion("TO_USER_NAME <", value, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameLessThanOrEqualTo(String value) {
            addCriterion("TO_USER_NAME <=", value, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameLike(String value) {
            addCriterion("TO_USER_NAME like", value, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameNotLike(String value) {
            addCriterion("TO_USER_NAME not like", value, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameIn(List<String> values) {
            addCriterion("TO_USER_NAME in", values, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameNotIn(List<String> values) {
            addCriterion("TO_USER_NAME not in", values, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameBetween(String value1, String value2) {
            addCriterion("TO_USER_NAME between", value1, value2, "toUserName");
            return (Criteria) this;
        }

        public Criteria andToUserNameNotBetween(String value1, String value2) {
            addCriterion("TO_USER_NAME not between", value1, value2, "toUserName");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("COUNT is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("COUNT =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("COUNT <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("COUNT >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("COUNT >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("COUNT <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("COUNT <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("COUNT in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("COUNT not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("COUNT between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("COUNT not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("REASON is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("REASON is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("REASON =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("REASON <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("REASON >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("REASON >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("REASON <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("REASON <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("REASON like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("REASON not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("REASON in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("REASON not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("REASON between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("REASON not between", value1, value2, "reason");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria andGuidLikeInsensitive(String value) {
            addCriterion("upper(GUID) like", value.toUpperCase(), "guid");
            return this;
        }

        public Criteria andFromUserIdLikeInsensitive(String value) {
            addCriterion("upper(FROM_USER_ID) like", value.toUpperCase(), "fromUserId");
            return this;
        }

        public Criteria andFromUserNameLikeInsensitive(String value) {
            addCriterion("upper(FROM_USER_NAME) like", value.toUpperCase(), "fromUserName");
            return this;
        }

        public Criteria andToUserIdLikeInsensitive(String value) {
            addCriterion("upper(TO_USER_ID) like", value.toUpperCase(), "toUserId");
            return this;
        }

        public Criteria andToUserNameLikeInsensitive(String value) {
            addCriterion("upper(TO_USER_NAME) like", value.toUpperCase(), "toUserName");
            return this;
        }

        public Criteria andReasonLikeInsensitive(String value) {
            addCriterion("upper(REASON) like", value.toUpperCase(), "reason");
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