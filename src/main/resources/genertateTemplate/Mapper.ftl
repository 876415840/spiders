package ${packagePath};

<#list importList as importStr>
	<#if importStr?? >
import ${importStr};
	</#if>
</#list>

/**
 *
 * @author 系统生成
 */
@Mapper
public interface ${poName}Mapper {

}
