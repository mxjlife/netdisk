package com.mxjlife.netdisk.pojo.base;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * description 返回一页数据
 *
 * @author mxj
 * email mxjlife@163.com
 * date 2019/1/3 11:02
 */
@Data
public class PageInfo<T> {

    /**
     * 当前页
     */
    private int currentPage = 1;
    /**
     * 每页显示多少条记录
     */
    private int pageSize = 20;
    // 请求参数
    /**
     * 业务请求参数
     */
    private T params;

    /**
     * 查询关键字
     */
    private String keyWord;

    /**
     * 查询列表开始时间
     */
    private Date startTime;
    /**
     * 查询列表结束时间
     */
    private Date endTime;

    /**
     * 查询列表排序依据
     */
    private String orderBy;

    /**
     * 查询列表排序排序类型, 1: asc, 2: desc
     */
    private String orderType;

    // 查询数据库
    /**
     * 本页的数据列表
     */
    private List<T> dataList;
    /**
     * 总记录数
     */
    private int totalCount;

    // 计算
    /**
     * 总页数
     */
    private int pageCount;


    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        int count = totalCount / this.pageSize;
        int a = totalCount % this.pageSize;
        // 总页数
        this.pageCount = a == 0 ? count : count + 1;
    }

    public int getStartIndex() {
        // 当前页开始索引
        return (this.currentPage - 1) * this.pageSize;
    }


}
