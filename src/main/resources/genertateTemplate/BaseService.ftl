package ${packagePath};

<#list importList as str>
import ${str};
</#list>

/**
 * 业务层接口
 * @ClassName:  Base${poName}Service
 * @Description: TODO
 * @author: 系统自动生成
 */
public interface Base${poName}Service extends GenericService<${poName}, Long> {

}