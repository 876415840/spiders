package ${packagePath};

<#list importList as importStr>
	<#if importStr?? >
import ${importStr};
	</#if>
</#list>
import tk.mybatis.mapper.common.Mapper;

/**
 *
 * @author 系统生成
 */
@Mapper
public interface ${poName}Mapper {

}
