package com.kangda.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by: shouhan  on 15:15 2018/1/15.
 * <p>
 * 分页封装
 */
public class Page<T> implements Serializable {
    /**
     * 当前页
     */
    private Integer pageNo = 1;
    /**
     * 每页的行数
     */
    private Integer pageSize = 10;
    /**
     * 总共的页数
     */
    private Integer totalNo;
    /**
     * 总数据的条数
     */
    private Long totalCount;
    /**
     * 返回数据
     */
    private List<T> result = Collections.emptyList();
    /**
     * 排序的字段
     */
    private String orderBy;
    /**
     * 其他查询条件 格式：filter[name]
     */
    private Map<String, Object> filter;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(Integer totalNo) {
        this.totalNo = totalNo;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }
}
