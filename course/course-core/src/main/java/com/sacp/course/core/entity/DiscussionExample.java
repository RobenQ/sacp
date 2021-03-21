package com.sacp.course.core.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiscussionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DiscussionExample() {
        oredCriteria = new ArrayList<>();
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

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSacpIdIsNull() {
            addCriterion("sacp_id is null");
            return (Criteria) this;
        }

        public Criteria andSacpIdIsNotNull() {
            addCriterion("sacp_id is not null");
            return (Criteria) this;
        }

        public Criteria andSacpIdEqualTo(Integer value) {
            addCriterion("sacp_id =", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdNotEqualTo(Integer value) {
            addCriterion("sacp_id <>", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdGreaterThan(Integer value) {
            addCriterion("sacp_id >", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sacp_id >=", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdLessThan(Integer value) {
            addCriterion("sacp_id <", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdLessThanOrEqualTo(Integer value) {
            addCriterion("sacp_id <=", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdIn(List<Integer> values) {
            addCriterion("sacp_id in", values, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdNotIn(List<Integer> values) {
            addCriterion("sacp_id not in", values, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdBetween(Integer value1, Integer value2) {
            addCriterion("sacp_id between", value1, value2, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sacp_id not between", value1, value2, "sacpId");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameIsNull() {
            addCriterion("member_nickName is null");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameIsNotNull() {
            addCriterion("member_nickName is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameEqualTo(String value) {
            addCriterion("member_nickName =", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameNotEqualTo(String value) {
            addCriterion("member_nickName <>", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameGreaterThan(String value) {
            addCriterion("member_nickName >", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("member_nickName >=", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameLessThan(String value) {
            addCriterion("member_nickName <", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameLessThanOrEqualTo(String value) {
            addCriterion("member_nickName <=", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameLike(String value) {
            addCriterion("member_nickName like", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameNotLike(String value) {
            addCriterion("member_nickName not like", value, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameIn(List<String> values) {
            addCriterion("member_nickName in", values, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameNotIn(List<String> values) {
            addCriterion("member_nickName not in", values, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameBetween(String value1, String value2) {
            addCriterion("member_nickName between", value1, value2, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andMemberNicknameNotBetween(String value1, String value2) {
            addCriterion("member_nickName not between", value1, value2, "memberNickname");
            return (Criteria) this;
        }

        public Criteria andLikesNumberIsNull() {
            addCriterion("likes_number is null");
            return (Criteria) this;
        }

        public Criteria andLikesNumberIsNotNull() {
            addCriterion("likes_number is not null");
            return (Criteria) this;
        }

        public Criteria andLikesNumberEqualTo(Integer value) {
            addCriterion("likes_number =", value, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andLikesNumberNotEqualTo(Integer value) {
            addCriterion("likes_number <>", value, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andLikesNumberGreaterThan(Integer value) {
            addCriterion("likes_number >", value, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andLikesNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("likes_number >=", value, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andLikesNumberLessThan(Integer value) {
            addCriterion("likes_number <", value, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andLikesNumberLessThanOrEqualTo(Integer value) {
            addCriterion("likes_number <=", value, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andLikesNumberIn(List<Integer> values) {
            addCriterion("likes_number in", values, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andLikesNumberNotIn(List<Integer> values) {
            addCriterion("likes_number not in", values, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andLikesNumberBetween(Integer value1, Integer value2) {
            addCriterion("likes_number between", value1, value2, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andLikesNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("likes_number not between", value1, value2, "likesNumber");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Byte value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Byte value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Byte value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Byte value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Byte value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Byte> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Byte> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Byte value1, Byte value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Byte value1, Byte value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
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