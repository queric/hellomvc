package com.demo.util;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.9 $
 */
public class PageBean implements java.io.Serializable {
    private static final long serialVersionUID = 693435998917420886L;
    private int currentPage = 1; // 当前页
    private int recordCount = 0; // 总记录数
    private int pageSize = 15; // 一次查询的最大记录数

    /**
     * Creates a new PageBean object.
     */
    public PageBean() {
    }

    /**
     * Creates a new PageBean object.
     *
     * @param currentPage DOCUMENT ME!
     * @param pageSize DOCUMENT ME!
     */
    public PageBean(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getRecordCount() {
        return recordCount;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 获取总记录数
     *
     * @param currentPage DOCUMENT ME!
     */
    public void setCurrentPage(int currentPage) {
        if (currentPage < 1) {
            this.currentPage = 1;
        }
        else {
            this.currentPage = currentPage;
        }
    }

    /**
     * 设置总记录数
     *
     * @param recordsCount DOCUMENT ME!
     */
    public void setRecordCount(int recordsCount) {
        this.recordCount = recordsCount;
    }

    /**
     * 设置一次查询的最大记录数
     *
     * @param pageSize 记录数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("currentPage", currentPage).append("pageSize", pageSize).append("recordCount", recordCount).toString();
    }
}
