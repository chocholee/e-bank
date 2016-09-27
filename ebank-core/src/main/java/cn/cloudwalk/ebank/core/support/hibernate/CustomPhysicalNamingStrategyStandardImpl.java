package cn.cloudwalk.ebank.core.support.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Created by liwenhe on 2016/9/21.
 *
 * @author 李文禾
 */
public class CustomPhysicalNamingStrategyStandardImpl extends PhysicalNamingStrategyStandardImpl {

    private String tablePrefix  = "";

    private String columnPrefix = "";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        Assert.notNull(tablePrefix);
        return StringUtils.isEmpty(tablePrefix)
                ? name
                : Identifier.toIdentifier(tablePrefix + "_" + name.getText());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        Assert.notNull(columnPrefix);
        return StringUtils.isEmpty(columnPrefix)
                ? name
                : Identifier.toIdentifier(columnPrefix + "_" + name.getText());
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public void setColumnPrefix(String columnPrefix) {
        this.columnPrefix = columnPrefix;
    }
}
