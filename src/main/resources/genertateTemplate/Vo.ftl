package ${packagePath};

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ${poName}VO实体
 * @author 系统生成
 */
@ApiModel("${poName}VO")
public class ${poName}VO implements Serializable {
    private static final long serialVersionUID = 1L;
<#list poColumnList as obj>

    @ApiModelProperty("${obj.remarks}")
    private String ${obj.name};
</#list>
<#list poColumnList as obj>

    public String get${obj.firstUpperName}() {
        return ${obj.name};
    }

    public void set${obj.firstUpperName}(String ${obj.name}) {
        this.${obj.name} = ${obj.name};
    }
</#list>

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
