package cn.cloudwalk.ebank.core.domain.model.resource;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public enum ResourceEntityType {
    ALL("全部", Boolean.TRUE),
    DIRECTORY("文件夹", Boolean.FALSE),
    FILE("文件", Boolean.FALSE);

    private final String name;

    private final boolean isOnlyQuery;

    ResourceEntityType(String name, boolean isOnlyQuery) {
        this.name = name;
        this.isOnlyQuery = isOnlyQuery;
    }

    public String getName() {
        return name;
    }

    public boolean isOnlyQuery() {
        return isOnlyQuery;
    }
}
