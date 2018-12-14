package ${packagePath};

<#list importList as importStr>
    <#if importStr?? >
import ${importStr};
    </#if>
</#list>
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * ${poName}实体
 * @author 系统生成
 */
public class ${poName} implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    public static final String TABLE_NAME = "${tableName}";
<#list poColumnList as obj>

    /**
     * ${obj.remarks}
     */
    <#if obj.name =='id'>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    </#if>
    private ${obj.type} ${obj.name};
</#list>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ${poName} that = (${poName}) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
<#list poColumnList as obj>

    public ${obj.type} get${obj.firstUpperName}() {
        return ${obj.name};
    }

    public void set${obj.firstUpperName}(${obj.type} ${obj.name}) {
        this.${obj.name} = ${obj.name};
    }
</#list>

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
