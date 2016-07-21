package com.portal.bean;

import java.util.ArrayList;
import java.util.List;

public class JddReleaseDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public JddReleaseDetailExample() {
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

        public Criteria andReleaseIdIsNull() {
            addCriterion("RELEASE_ID is null");
            return (Criteria) this;
        }

        public Criteria andReleaseIdIsNotNull() {
            addCriterion("RELEASE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseIdEqualTo(String value) {
            addCriterion("RELEASE_ID =", value, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdNotEqualTo(String value) {
            addCriterion("RELEASE_ID <>", value, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdGreaterThan(String value) {
            addCriterion("RELEASE_ID >", value, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("RELEASE_ID >=", value, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdLessThan(String value) {
            addCriterion("RELEASE_ID <", value, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdLessThanOrEqualTo(String value) {
            addCriterion("RELEASE_ID <=", value, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdLike(String value) {
            addCriterion("RELEASE_ID like", value, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdNotLike(String value) {
            addCriterion("RELEASE_ID not like", value, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdIn(List<String> values) {
            addCriterion("RELEASE_ID in", values, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdNotIn(List<String> values) {
            addCriterion("RELEASE_ID not in", values, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdBetween(String value1, String value2) {
            addCriterion("RELEASE_ID between", value1, value2, "releaseId");
            return (Criteria) this;
        }

        public Criteria andReleaseIdNotBetween(String value1, String value2) {
            addCriterion("RELEASE_ID not between", value1, value2, "releaseId");
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

        public Criteria andJddCountIsNull() {
            addCriterion("JDD_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andJddCountIsNotNull() {
            addCriterion("JDD_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andJddCountEqualTo(Integer value) {
            addCriterion("JDD_COUNT =", value, "jddCount");
            return (Criteria) this;
        }

        public Criteria andJddCountNotEqualTo(Integer value) {
            addCriterion("JDD_COUNT <>", value, "jddCount");
            return (Criteria) this;
        }

        public Criteria andJddCountGreaterThan(Integer value) {
            addCriterion("JDD_COUNT >", value, "jddCount");
            return (Criteria) this;
        }

        public Criteria andJddCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("JDD_COUNT >=", value, "jddCount");
            return (Criteria) this;
        }

        public Criteria andJddCountLessThan(Integer value) {
            addCriterion("JDD_COUNT <", value, "jddCount");
            return (Criteria) this;
        }

        public Criteria andJddCountLessThanOrEqualTo(Integer value) {
            addCriterion("JDD_COUNT <=", value, "jddCount");
            return (Criteria) this;
        }

        public Criteria andJddCountIn(List<Integer> values) {
            addCriterion("JDD_COUNT in", values, "jddCount");
            return (Criteria) this;
        }

        public Criteria andJddCountNotIn(List<Integer> values) {
            addCriterion("JDD_COUNT not in", values, "jddCount");
            return (Criteria) this;
        }

        public Criteria andJddCountBetween(Integer value1, Integer value2) {
            addCriterion("JDD_COUNT between", value1, value2, "jddCount");
            return (Criteria) this;
        }

        public Criteria andJddCountNotBetween(Integer value1, Integer value2) {
            addCriterion("JDD_COUNT not between", value1, value2, "jddCount");
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

        public Criteria andRuleChildIsNull() {
            addCriterion("RULE_CHILD is null");
            return (Criteria) this;
        }

        public Criteria andRuleChildIsNotNull() {
            addCriterion("RULE_CHILD is not null");
            return (Criteria) this;
        }

        public Criteria andRuleChildEqualTo(String value) {
            addCriterion("RULE_CHILD =", value, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildNotEqualTo(String value) {
            addCriterion("RULE_CHILD <>", value, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildGreaterThan(String value) {
            addCriterion("RULE_CHILD >", value, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildGreaterThanOrEqualTo(String value) {
            addCriterion("RULE_CHILD >=", value, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildLessThan(String value) {
            addCriterion("RULE_CHILD <", value, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildLessThanOrEqualTo(String value) {
            addCriterion("RULE_CHILD <=", value, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildLike(String value) {
            addCriterion("RULE_CHILD like", value, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildNotLike(String value) {
            addCriterion("RULE_CHILD not like", value, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildIn(List<String> values) {
            addCriterion("RULE_CHILD in", values, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildNotIn(List<String> values) {
            addCriterion("RULE_CHILD not in", values, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildBetween(String value1, String value2) {
            addCriterion("RULE_CHILD between", value1, value2, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andRuleChildNotBetween(String value1, String value2) {
            addCriterion("RULE_CHILD not between", value1, value2, "ruleChild");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("SOURCE =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("SOURCE <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("SOURCE >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("SOURCE <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("SOURCE <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("SOURCE like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("SOURCE not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("SOURCE in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("SOURCE not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("SOURCE between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("SOURCE not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceCompIsNull() {
            addCriterion("SOURCE_COMP is null");
            return (Criteria) this;
        }

        public Criteria andSourceCompIsNotNull() {
            addCriterion("SOURCE_COMP is not null");
            return (Criteria) this;
        }

        public Criteria andSourceCompEqualTo(String value) {
            addCriterion("SOURCE_COMP =", value, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompNotEqualTo(String value) {
            addCriterion("SOURCE_COMP <>", value, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompGreaterThan(String value) {
            addCriterion("SOURCE_COMP >", value, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_COMP >=", value, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompLessThan(String value) {
            addCriterion("SOURCE_COMP <", value, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_COMP <=", value, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompLike(String value) {
            addCriterion("SOURCE_COMP like", value, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompNotLike(String value) {
            addCriterion("SOURCE_COMP not like", value, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompIn(List<String> values) {
            addCriterion("SOURCE_COMP in", values, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompNotIn(List<String> values) {
            addCriterion("SOURCE_COMP not in", values, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompBetween(String value1, String value2) {
            addCriterion("SOURCE_COMP between", value1, value2, "sourceComp");
            return (Criteria) this;
        }

        public Criteria andSourceCompNotBetween(String value1, String value2) {
            addCriterion("SOURCE_COMP not between", value1, value2, "sourceComp");
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

        public Criteria andReleaseIdLikeInsensitive(String value) {
            addCriterion("upper(RELEASE_ID) like", value.toUpperCase(), "releaseId");
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

        public Criteria andReasonLikeInsensitive(String value) {
            addCriterion("upper(REASON) like", value.toUpperCase(), "reason");
            return this;
        }

        public Criteria andRuleChildLikeInsensitive(String value) {
            addCriterion("upper(RULE_CHILD) like", value.toUpperCase(), "ruleChild");
            return this;
        }

        public Criteria andSourceLikeInsensitive(String value) {
            addCriterion("upper(SOURCE) like", value.toUpperCase(), "source");
            return this;
        }

        public Criteria andSourceCompLikeInsensitive(String value) {
            addCriterion("upper(SOURCE_COMP) like", value.toUpperCase(), "sourceComp");
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