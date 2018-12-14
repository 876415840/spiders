package ${packagePath};

<#list importList as str>
import ${str};
</#list>

/**
 * 业务层接口
 * @ClassName:  ${poName}Service   
 * @Description: TODO
 * @author: 系统自动生成
 */
public interface ${poName}Service extends GenericService<${poName}, Long> {

}