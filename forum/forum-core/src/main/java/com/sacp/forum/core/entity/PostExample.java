package com.sacp.forum.core.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PostExample() {
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

        public Criteria andSacpIdEqualTo(String value) {
            addCriterion("sacp_id =", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdNotEqualTo(String value) {
            addCriterion("sacp_id <>", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdGreaterThan(String value) {
            addCriterion("sacp_id >", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdGreaterThanOrEqualTo(String value) {
            addCriterion("sacp_id >=", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdLessThan(String value) {
            addCriterion("sacp_id <", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdLessThanOrEqualTo(String value) {
            addCriterion("sacp_id <=", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdLike(String value) {
            addCriterion("sacp_id like", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdNotLike(String value) {
            addCriterion("sacp_id not like", value, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdIn(List<String> values) {
            addCriterion("sacp_id in", values, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdNotIn(List<String> values) {
            addCriterion("sacp_id not in", values, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdBetween(String value1, String value2) {
            addCriterion("sacp_id between", value1, value2, "sacpId");
            return (Criteria) this;
        }

        public Criteria andSacpIdNotBetween(String value1, String value2) {
            addCriterion("sacp_id not between", value1, value2, "sacpId");
            return (Criteria) this;
        }

        public Criteria andBlockIdIsNull() {
            addCriterion("block_id is null");
            return (Criteria) this;
        }

        public Criteria andBlockIdIsNotNull() {
            addCriterion("block_id is not null");
            return (Criteria) this;
        }

        public Criteria andBlockIdEqualTo(Integer value) {
            addCriterion("block_id =", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdNotEqualTo(Integer value) {
            addCriterion("block_id <>", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdGreaterThan(Integer value) {
            addCriterion("block_id >", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("block_id >=", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdLessThan(Integer value) {
            addCriterion("block_id <", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdLessThanOrEqualTo(Integer value) {
            addCriterion("block_id <=", value, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdIn(List<Integer> values) {
            addCriterion("block_id in", values, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdNotIn(List<Integer> values) {
            addCriterion("block_id not in", values, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdBetween(Integer value1, Integer value2) {
            addCriterion("block_id between", value1, value2, "blockId");
            return (Criteria) this;
        }

        public Criteria andBlockIdNotBetween(Integer value1, Integer value2) {
            addCriterion("block_id not between", value1, value2, "blockId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIsNull() {
            addCriterion("classify_id is null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIsNotNull() {
            addCriterion("classify_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassifyIdEqualTo(Integer value) {
            addCriterion("classify_id =", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotEqualTo(Integer value) {
            addCriterion("classify_id <>", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThan(Integer value) {
            addCriterion("classify_id >", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("classify_id >=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThan(Integer value) {
            addCriterion("classify_id <", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdLessThanOrEqualTo(Integer value) {
            addCriterion("classify_id <=", value, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdIn(List<Integer> values) {
            addCriterion("classify_id in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotIn(List<Integer> values) {
            addCriterion("classify_id not in", values, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdBetween(Integer value1, Integer value2) {
            addCriterion("classify_id between", value1, value2, "classifyId");
            return (Criteria) this;
        }

        public Criteria andClassifyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("classify_id not between", value1, value2, "classifyId");
            return (Criteria) this;
        }

        public Criteria andOrderIsNull() {
            addCriterion("orders is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsNotNull() {
            addCriterion("orders is not null");
            return (Criteria) this;
        }

        public Criteria andOrderEqualTo(Integer value) {
            addCriterion("orders =", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrderNotEqualTo(Integer value) {
            addCriterion("orders <>", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThan(Integer value) {
            addCriterion("orders >", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("orders >=", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrderLessThan(Integer value) {
            addCriterion("orders <", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrderLessThanOrEqualTo(Integer value) {
            addCriterion("orders <=", value, "orders");
            return (Criteria) this;
        }

        public Criteria andOrderIn(List<Integer> values) {
            addCriterion("orders in", values, "orders");
            return (Criteria) this;
        }

        public Criteria andOrderNotIn(List<Integer> values) {
            addCriterion("orders not in", values, "orders");
            return (Criteria) this;
        }

        public Criteria andOrderBetween(Integer value1, Integer value2) {
            addCriterion("orders between", value1, value2, "orders");
            return (Criteria) this;
        }

        public Criteria andOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("orders not between", value1, value2, "orders");
            return (Criteria) this;
        }

        public Criteria andViewerNumberIsNull() {
            addCriterion("viewer_number is null");
            return (Criteria) this;
        }

        public Criteria andViewerNumberIsNotNull() {
            addCriterion("viewer_number is not null");
            return (Criteria) this;
        }

        public Criteria andViewerNumberEqualTo(Integer value) {
            addCriterion("viewer_number =", value, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andViewerNumberNotEqualTo(Integer value) {
            addCriterion("viewer_number <>", value, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andViewerNumberGreaterThan(Integer value) {
            addCriterion("viewer_number >", value, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andViewerNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("viewer_number >=", value, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andViewerNumberLessThan(Integer value) {
            addCriterion("viewer_number <", value, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andViewerNumberLessThanOrEqualTo(Integer value) {
            addCriterion("viewer_number <=", value, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andViewerNumberIn(List<Integer> values) {
            addCriterion("viewer_number in", values, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andViewerNumberNotIn(List<Integer> values) {
            addCriterion("viewer_number not in", values, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andViewerNumberBetween(Integer value1, Integer value2) {
            addCriterion("viewer_number between", value1, value2, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andViewerNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("viewer_number not between", value1, value2, "viewerNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberIsNull() {
            addCriterion("reply_number is null");
            return (Criteria) this;
        }

        public Criteria andReplyNumberIsNotNull() {
            addCriterion("reply_number is not null");
            return (Criteria) this;
        }

        public Criteria andReplyNumberEqualTo(Integer value) {
            addCriterion("reply_number =", value, "replyNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberNotEqualTo(Integer value) {
            addCriterion("reply_number <>", value, "replyNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberGreaterThan(Integer value) {
            addCriterion("reply_number >", value, "replyNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("reply_number >=", value, "replyNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberLessThan(Integer value) {
            addCriterion("reply_number <", value, "replyNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberLessThanOrEqualTo(Integer value) {
            addCriterion("reply_number <=", value, "replyNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberIn(List<Integer> values) {
            addCriterion("reply_number in", values, "replyNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberNotIn(List<Integer> values) {
            addCriterion("reply_number not in", values, "replyNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberBetween(Integer value1, Integer value2) {
            addCriterion("reply_number between", value1, value2, "replyNumber");
            return (Criteria) this;
        }

        public Criteria andReplyNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("reply_number not between", value1, value2, "replyNumber");
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

        public Criteria andIdDeleteIsNull() {
            addCriterion("id_delete is null");
            return (Criteria) this;
        }

        public Criteria andIdDeleteIsNotNull() {
            addCriterion("id_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIdDeleteEqualTo(Integer value) {
            addCriterion("id_delete =", value, "idDelete");
            return (Criteria) this;
        }

        public Criteria andIdDeleteNotEqualTo(Integer value) {
            addCriterion("id_delete <>", value, "idDelete");
            return (Criteria) this;
        }

        public Criteria andIdDeleteGreaterThan(Integer value) {
            addCriterion("id_delete >", value, "idDelete");
            return (Criteria) this;
        }

        public Criteria andIdDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_delete >=", value, "idDelete");
            return (Criteria) this;
        }

        public Criteria andIdDeleteLessThan(Integer value) {
            addCriterion("id_delete <", value, "idDelete");
            return (Criteria) this;
        }

        public Criteria andIdDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("id_delete <=", value, "idDelete");
            return (Criteria) this;
        }

        public Criteria andIdDeleteIn(List<Integer> values) {
            addCriterion("id_delete in", values, "idDelete");
            return (Criteria) this;
        }

        public Criteria andIdDeleteNotIn(List<Integer> values) {
            addCriterion("id_delete not in", values, "idDelete");
            return (Criteria) this;
        }

        public Criteria andIdDeleteBetween(Integer value1, Integer value2) {
            addCriterion("id_delete between", value1, value2, "idDelete");
            return (Criteria) this;
        }

        public Criteria andIdDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("id_delete not between", value1, value2, "idDelete");
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