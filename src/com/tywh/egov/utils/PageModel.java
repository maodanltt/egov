package com.tywh.egov.utils;

import java.util.ArrayList;
import java.util.List;

//封装分页对象
public class PageModel<T> {

    private Integer pageNo;

    private Integer pageSize;

    private Integer totalRecords;

    private List<T> dataList;

    public PageModel(String pageNo) {
        if (pageNo != null) {
            this.pageNo = Integer.parseInt(pageNo);
        } else {
            this.pageNo = 1;
        }
        this.pageSize = 3;
        dataList = new ArrayList<>();
    }


    public Integer getPageNo() {
        return pageNo;
    }


    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getTotalPages() {
        return totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
    }

    public List<T> getDataList() {
        return dataList;
    }

}
