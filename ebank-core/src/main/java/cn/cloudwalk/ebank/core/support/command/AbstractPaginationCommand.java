package cn.cloudwalk.ebank.core.support.command;

/**
 * Created by liwenhe on 2016/9/28.
 *
 * @author 李文禾
 */
public abstract class AbstractPaginationCommand extends AbstractCommand {

    private final static int DEFAULT_PAGE       = 1;

    private final static int DEFAULT_PAGE_SIZE  = 10;

    private Integer page;

    private Integer pageSize;

    public Integer getPage() {
        page = (null == page) ? DEFAULT_PAGE : page;
        return page;
    }

    public Integer getPageSize() {
        pageSize = (null == pageSize) ? DEFAULT_PAGE_SIZE : pageSize;
        return pageSize;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
