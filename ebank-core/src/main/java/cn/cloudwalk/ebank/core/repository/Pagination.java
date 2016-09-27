package cn.cloudwalk.ebank.core.repository;

import java.util.List;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public class Pagination<T> {

    private int     page;               // 页

    private int     pageSize;           // 页数据大小

    private int     count;              // 数据总数量

    private List<T> data;               // 数据

    public Pagination(int page, int pageSize, int count, List<T> data) {
        this.page = page;
        this.pageSize = pageSize;
        this.count = count;
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCount() {
        return count;
    }

    public List<T> getData() {
        return data;
    }
}
