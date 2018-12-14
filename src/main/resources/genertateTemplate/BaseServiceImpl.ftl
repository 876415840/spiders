package ${packagePath};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.finup.store.service.GenericServiceSupport;
import org.springframework.stereotype.Service;
<#list importList as str>
import ${str};
</#list>

/**
 * 业务层实现类接口
 * @ClassName:  Base${poName}ServiceImpl
 * @Description:
 * @author: 系统自动生成
 */
@Service
public class Base${poName}ServiceImpl extends GenericServiceSupport<${poName}, Long> implements Base${poName}Service {
    private static final Logger logger = LoggerFactory.getLogger(Base${poName}ServiceImpl.class);

}
