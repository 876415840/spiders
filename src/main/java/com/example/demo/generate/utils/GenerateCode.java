package com.example.demo.generate.utils;



import com.example.demo.generate.freemarker.FreeMarkerUtils;
import com.example.demo.generate.po.GenerateTable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
public class GenerateCode {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateCode.class);

    //单元测试文件相对路径
    public static String coommonJavaTestPath = "/src/test/java/com/example/demo/";
    //java文件相对目录com.example.demo.entity
    public static String coommonJavaPath = "/src/main/java/com/example/demo/";
    //资源文件相对目录
    public static String coommonResourcesPath = "/src/main/resources/";
    public static String comDir = "/com/";
    /**
     * 相对于在项目跟路径下文件夹
     */
    public static String TEMPLATE_ROOT = "/genertateTemplate/";
    /**
     * SQLMAP 模板数据
     */
    public static String TEMPLATE_SQL = "SqlMap.ftl";
    /**
     * API 模板数据
     */
    public static String TEMPLATE_API = "Api.ftl";
    /**
     * APISupport 模板数据
     */
    public static String TEMPLATE_API_SUPPORT = "APISupport.ftl";
    /**
     * po 模板数据
     */
    public static String TEMPLATE_PO = "Po.ftl";
    /**
     * VO 模板数据
     */
    public static String TEMPLATE_VO = "Vo.ftl";
    /**
     * VO 模板数据
     */
    public static String TEMPLATE_MAPPER = "Mapper.ftl";
    /**
     * 业务层 模板数据
     */
    public static String TEMPLATE_SERVICE = "Service.ftl";
    /**
     * 业务层 模板数据
     */
    public static String TEMPLATE_BASE_SERVICE = "BaseService.ftl";
    /**
     * 业务层实现层  模板数据
     */
    public static String TEMPLATE_SERVICE_IMPL = "ServiceImpl.ftl";
    /**
     * 业务层实现层  模板数据
     */
    public static String TEMPLATE_BASE_SERVICE_IMPL = "BaseServiceImpl.ftl";
    /**
     * 单元测试
     */
    public static String TEMPLATE_SERVICE_TEST = "ServiceTest.ftl";
    /**
     * WEB端业务层实现层  模板数据
     */
    public static String TEMPLATE_WEB_SERVICE = "Web_Service.ftl";
    /**
     * WEB端业务层实现层  模板数据
     */
    public static String TEMPLATE_WEB_SERVICE_IMPL = "Web_ServiceImpl.ftl";
    /**
     * 控制层 模板数据
     */
    public static String TEMPLATE_CONTROLLER = "Controller.ftl";
    /**
     * 查询列表  模板数据
     */
    public static String TEMPLATE_PAGE_LIST = "pageList.ftl";
    /**
     * sqlMap 生成的文件路径
     */
    public static String SQLMAP_FILE_PATH = "SQLMAP_FILE_PATH";
    /**
     * PO 生成的文件路径
     */
    public static String PO_FILE_PATH = "PO_FILE_PATH";
    /**
     * VO 生成的文件路径
     */
    public static String VO_FILE_PATH = "VO_FILE_PATH";
    /**
     * Mapper 生成的文件路径
     */
    public static String MAPPER_FILE_PATH = "MAPPER_FILE_PATH";
    /**
     * API 生成的文件路径
     */
    public static String API_FILE_PATH = "API_FILE_PATH";
    /**
     * APISupport 生成文件路径
     */
    public static String API_SUPPORT_FILE_PATH = "API_SUPPORT_FILE_PATH";
    /**
     * service 生成的文件路径
     */
    public static String SERVICE_FILE_PATH = "SERVICE_FILE_PATH";
    /**
     * service实现层 生成的文件路径
     */
    public static String SERVICE_IMPL_FILE_PATH = "SERVICE_IMPL_FILE_PATH";

    /**
     * baseservice 生成的文件路径
     */
    public static String BASE_SERVICE_FILE_PATH = "BASE_SERVICE_FILE_PATH";
    /**
     * BASEservice实现层 生成的文件路径
     */
    public static String BASE_SERVICE_IMPL_FILE_PATH = "BASE_SERVICE_IMPL_FILE_PATH";
    /**
     * service单元测试
     */
    public static String SERVICE_TEST_FILE_PATH = "SERVICE_TEST_FILE_PATH";
    /**
     * WEB 项目 service 生成的文件路径
     */
    public static String WEB_SERVICE_FILE_PATH = "WEB_SERVICE_FILE_PATH";
    /**
     * WEB 项目 service实现类 生成的文件路径
     */
    public static String WEB_SERVICE_IMPL_FILE_PATH = "WEB_SERVICE_IMPL_FILE_PATH";
    /**
     * controller生成的文件路径
     */
    public static String CONTROLLER_FILE_PATH = "CONTROLLER_FILE_PATH";
    /**
     * jsp生成的文件路径
     */
    public static String PAGE_LIST_FILE_PATH = "PAGE_LIST_FILE_PATH";

    public static Map<String, String> filePathMap = new HashMap<String, String>();

    private GenerateTable table;

    public GenerateCode() {

    }

    /**
     * private String childSystemName;
     * private String childSystemAPIName;
     * private String childSystemWeb;
     *
     * @param gt
     */
    public GenerateCode(GenerateTable gt) {
        this.table = gt;
        initFilePath();
    }

    /**
     * 初始化文件路径
     *
     * @param
     */
    private void initFilePath() {

        //项目子系统
        if (StringUtils.isNotBlank(table.getChildSystemName())) {
            //sqlmap文件路径
            filePathMap.put(SQLMAP_FILE_PATH, table.getWorkspacesPath() + table.getChildSystemName() + coommonResourcesPath + "common/sqlmap/po/" + table.getPoName()
                    + GenerateConstants.FileType.XML_SUFFIX);

            String interfaceProjectRoot = table.getWorkspacesPath() + table.getChildSystemName() + coommonJavaPath;
            //PO文件路径
            filePathMap.put(PO_FILE_PATH, interfaceProjectRoot + "entity/" + table.getPoName() + GenerateConstants.FileType.JAVA_SUFFIX);
            //VO文件路径
            filePathMap.put(VO_FILE_PATH, interfaceProjectRoot + "vo/" + table.getPoName() + "VO" + GenerateConstants.FileType.JAVA_SUFFIX);
            //mapper文件路径
            filePathMap.put(MAPPER_FILE_PATH, interfaceProjectRoot + "mapper/" + table.getPoName() + "Mapper" + GenerateConstants.FileType.JAVA_SUFFIX);
            //service文件路径
            filePathMap.put(SERVICE_FILE_PATH, interfaceProjectRoot + "service/" + table.getPoName() + "Service" + GenerateConstants.FileType.JAVA_SUFFIX);
            //serviceImp文件路径
            filePathMap.put(SERVICE_IMPL_FILE_PATH, interfaceProjectRoot + "service/impl/" + table.getPoName() + "ServiceImpl" + GenerateConstants.FileType.JAVA_SUFFIX);


            //baseservice文件路径
            filePathMap.put(BASE_SERVICE_FILE_PATH, interfaceProjectRoot + "service/Base" + table.getPoName() + "Service" + GenerateConstants.FileType.JAVA_SUFFIX);
            //baseserviceImp文件路径
            filePathMap.put(BASE_SERVICE_IMPL_FILE_PATH, interfaceProjectRoot + "service/impl/Base" + table.getPoName() + "ServiceImpl" + GenerateConstants.FileType.JAVA_SUFFIX);
        }
    }

    /**
     * 生成SQLMap文件
     *
     * @return
     * @throws Exception
     */
    public boolean generateSqlMapFile() throws Exception {
        if (filePathMap.containsKey(SQLMAP_FILE_PATH)) {
            if (table != null && table.getParamMaps() != null && table.getParamMaps().size() > 0) {
                String template = GenerateFileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_SQL);
                template = template.replaceAll("#\\{", "00000\\{");
                template = template.replaceAll("%%\\{", "99999\\{");
                String sqlMapContext = FreeMarkerUtils.getResult(template, table.getParamMaps());
                sqlMapContext = sqlMapContext.replaceAll("00000\\{", "#\\{");
                sqlMapContext = sqlMapContext.replaceAll("99999\\{", "\\$\\{");
                String filePath = filePathMap.get(SQLMAP_FILE_PATH);
                boolean status = GenerateFileUtils.writeFile(filePath, sqlMapContext);
                if (status) {
                    LOGGER.info("创建SQLMap文件：【成功】" + filePath);
                } else {
                    LOGGER.info("创建SQLMap文件：【失败】" + filePath);
                }
            } else {
                LOGGER.info("生成sqlmap异常");
            }
        }
        return true;
    }

    /**
     * 生成PO文件
     *
     * @return
     * @throws Exception
     */
    public boolean generatePoFile() throws Exception {
        if (filePathMap.containsKey(PO_FILE_PATH)) {
            if (table != null && table.getParamMaps() != null && table.getParamMaps().size() > 0) {
                String filePath = filePathMap.get(PO_FILE_PATH);
                String template = GenerateFileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_PO);
                Map<String, Object> paramsMap = table.getParamMaps();
                paramsMap.put("packagePath", filePath.substring(filePath.indexOf(comDir) + 1, filePath.lastIndexOf(GenerateConstants.Separator.SLASH))
                        .replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                String sqlMapContext = FreeMarkerUtils.getResult(template, paramsMap);
                boolean status = GenerateFileUtils.writeFile(filePath, sqlMapContext);
                if (status) {
                    LOGGER.info("创建PO文件：【成功】" + filePath);
                } else {
                    LOGGER.info("创建PO文件：【失败】" + filePath);
                }
            } else {
                LOGGER.info("生成PO异常");
            }
        }
        return true;
    }

    /**
     * 生成VO文件
     *
     * @return
     * @throws Exception
     */
    public boolean generateVoFile() throws Exception {
        if (filePathMap.containsKey(VO_FILE_PATH)) {
            if (table != null && table.getParamMaps() != null && table.getParamMaps().size() > 0) {
                String filePath = filePathMap.get(VO_FILE_PATH);
                String poPath = filePathMap.get(PO_FILE_PATH);
                String template = GenerateFileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_VO);
                Map<String, Object> paramsMap = table.getParamMaps();
                paramsMap.put("packagePath", filePath.substring(filePath.indexOf(comDir) + 1, filePath.lastIndexOf(GenerateConstants.Separator.SLASH))
                        .replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                //包的引用路径
                List<String> importList = new ArrayList<String>();
                importList.add(poPath.substring(poPath.indexOf(comDir) + 1, poPath.lastIndexOf(GenerateConstants.Separator.DOT)).replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));

                if (table.getTypeMap().containsKey("Date")) {
                    importList.add("java.util.Date");
                }
                paramsMap.put("importList", importList);
                String sqlMapContext = FreeMarkerUtils.getResult(template, paramsMap);

                boolean status = GenerateFileUtils.writeFile(filePath, sqlMapContext);
                if (status) {
                    LOGGER.info("创建VO文件：【成功】" + filePath);
                } else {
                    LOGGER.info("创建VO文件：【失败】" + filePath);
                }
            } else {
                LOGGER.info("生成VO异常");
            }
        }
        return true;
    }

    /**
     * 生成Mapper文件
     *
     * @return
     * @throws Exception
     */
    public boolean generateMapperFile() throws Exception {
        if (filePathMap.containsKey(MAPPER_FILE_PATH)) {
            if (table != null && table.getParamMaps() != null && table.getParamMaps().size() > 0) {
                String filePath = filePathMap.get(MAPPER_FILE_PATH);
                String poPath = filePathMap.get(PO_FILE_PATH);
                String template = GenerateFileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_MAPPER);
                Map<String, Object> paramsMap = table.getParamMaps();
                paramsMap.put("packagePath", filePath.substring(filePath.indexOf(comDir) + 1, filePath.lastIndexOf(GenerateConstants.Separator.SLASH))
                        .replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                //包的引用路径
                List<String> importList = new ArrayList<String>();
                importList.add(poPath.substring(poPath.indexOf(comDir) + 1, poPath.lastIndexOf(GenerateConstants.Separator.DOT)).replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));

                paramsMap.put("importList", importList);
                String sqlMapContext = FreeMarkerUtils.getResult(template, paramsMap);

                boolean status = GenerateFileUtils.writeFile(filePath, sqlMapContext);
                if (status) {
                    LOGGER.info("创建Mapper文件：【成功】" + filePath);
                } else {
                    LOGGER.info("创建Mapper文件：【失败】" + filePath);
                }
            } else {
                LOGGER.info("生成Mapper异常");
            }
        }
        return true;
    }

    /**
     * 生成Service文件
     *
     * @return
     * @throws Exception
     */
    public boolean generateServiceFile() throws Exception {
        if (filePathMap.containsKey(SERVICE_FILE_PATH)) {
            if (table != null && table.getParamMaps() != null && table.getParamMaps().size() > 0) {
                String filePath = filePathMap.get(SERVICE_FILE_PATH);
                String poPath = filePathMap.get(PO_FILE_PATH);
                Map<String, Object> paramsMap = table.getParamMaps();
                paramsMap.put("packagePath", filePath.substring(filePath.indexOf(comDir) + 1, filePath.lastIndexOf(GenerateConstants.Separator.SLASH))
                        .replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                List<String> importList = new ArrayList<String>();
                importList.add(poPath.substring(poPath.indexOf(comDir) + 1, poPath.lastIndexOf(GenerateConstants.Separator.DOT)).replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                paramsMap.put("importList", importList);
                String template = GenerateFileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_SERVICE);
                String sqlMapContext = FreeMarkerUtils.getResult(template, paramsMap);

                boolean status = GenerateFileUtils.writeFile(filePath, sqlMapContext);
                if (status) {
                    LOGGER.info("创建Service文件：【成功】" + filePath);
                } else {
                    LOGGER.info("创建Service文件：【失败】" + filePath);
                }
            } else {
                LOGGER.info("生成Service异常");
            }
        }
        return true;
    }
    /**
     * 生成ServiceImpl文件
     *
     * @return
     * @throws Exception
     */
    public boolean generateServiceImplFile() throws Exception {
        if (filePathMap.containsKey(SERVICE_IMPL_FILE_PATH)) {
            if (table != null && table.getParamMaps() != null && table.getParamMaps().size() > 0) {
                Map<String, Object> paramsMap = table.getParamMaps();
                String serviceImplPath = filePathMap.get(SERVICE_IMPL_FILE_PATH);
                String servicePath = filePathMap.get(SERVICE_FILE_PATH);
                String poPath = filePathMap.get(PO_FILE_PATH);
                List<String> importList = new ArrayList<String>();
                importList.add(poPath.substring(poPath.indexOf(comDir) + 1, poPath.lastIndexOf(GenerateConstants.Separator.DOT)).replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                importList.add(servicePath.substring(servicePath.indexOf(comDir) + 1, servicePath.lastIndexOf(GenerateConstants.Separator.DOT))
                        .replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));

                paramsMap.put("packagePath", serviceImplPath.substring(serviceImplPath.indexOf(comDir) + 1, serviceImplPath.lastIndexOf(GenerateConstants.Separator.SLASH))
                        .replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                paramsMap.put("importList", importList);
                String template = GenerateFileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_SERVICE_IMPL);
                String sqlMapContext = FreeMarkerUtils.getResult(template, paramsMap);
                //文件路径= 项目环境根目录 + 项目名称 + service目录 + 模块标识 + sevice
                boolean status = GenerateFileUtils.writeFile(serviceImplPath, sqlMapContext);
                if (status) {
                    LOGGER.info("创建ServiceImpl文件：【成功】" + serviceImplPath);
                } else {
                    LOGGER.info("创建ServiceImpl文件：【失败】" + serviceImplPath);
                }
            } else {
                LOGGER.info("生成ServiceImpl异常");
            }
        }
        return true;
    }

    /**
     * 生成BaseService文件
     *
     * @return
     * @throws Exception
     */
    public boolean generateBaseServiceFile() throws Exception {
        if (filePathMap.containsKey(BASE_SERVICE_FILE_PATH)) {
            if (table != null && table.getParamMaps() != null && table.getParamMaps().size() > 0) {
                String filePath = filePathMap.get(BASE_SERVICE_FILE_PATH);
                String poPath = filePathMap.get(PO_FILE_PATH);
                Map<String, Object> paramsMap = table.getParamMaps();
                paramsMap.put("packagePath", filePath.substring(filePath.indexOf(comDir) + 1, filePath.lastIndexOf(GenerateConstants.Separator.SLASH))
                        .replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                List<String> importList = new ArrayList<String>();
                importList.add(poPath.substring(poPath.indexOf(comDir) + 1, poPath.lastIndexOf(GenerateConstants.Separator.DOT)).replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                paramsMap.put("importList", importList);
                String template = GenerateFileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_BASE_SERVICE);
                String sqlMapContext = FreeMarkerUtils.getResult(template, paramsMap);

                boolean status = GenerateFileUtils.writeFile(filePath, sqlMapContext);
                if (status) {
                    LOGGER.info("创建BaseService文件：【成功】" + filePath);
                } else {
                    LOGGER.info("创建BaseService文件：【失败】" + filePath);
                }
            } else {
                LOGGER.info("生成BaseService异常");
            }
        }
        return true;
    }
    /**
     * 生成BaseServiceImpl文件
     *
     * @return
     * @throws Exception
     */
    public boolean generateBaseServiceImplFile() throws Exception {
        if (filePathMap.containsKey(BASE_SERVICE_IMPL_FILE_PATH)) {
            if (table != null && table.getParamMaps() != null && table.getParamMaps().size() > 0) {
                Map<String, Object> paramsMap = table.getParamMaps();
                String serviceImplPath = filePathMap.get(BASE_SERVICE_IMPL_FILE_PATH);
                String servicePath = filePathMap.get(BASE_SERVICE_FILE_PATH);
                String poPath = filePathMap.get(PO_FILE_PATH);
                List<String> importList = new ArrayList<String>();
                importList.add(poPath.substring(poPath.indexOf(comDir) + 1, poPath.lastIndexOf(GenerateConstants.Separator.DOT)).replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                importList.add(servicePath.substring(servicePath.indexOf(comDir) + 1, servicePath.lastIndexOf(GenerateConstants.Separator.DOT))
                        .replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));

                paramsMap.put("packagePath", serviceImplPath.substring(serviceImplPath.indexOf(comDir) + 1, serviceImplPath.lastIndexOf(GenerateConstants.Separator.SLASH))
                        .replaceAll(GenerateConstants.Separator.SLASH, GenerateConstants.Separator.DOT));
                paramsMap.put("importList", importList);
                String template = GenerateFileUtils.readerResourcesFile(TEMPLATE_ROOT + TEMPLATE_BASE_SERVICE_IMPL);
                String sqlMapContext = FreeMarkerUtils.getResult(template, paramsMap);
                //文件路径= 项目环境根目录 + 项目名称 + service目录 + 模块标识 + sevice
                boolean status = GenerateFileUtils.writeFile(serviceImplPath, sqlMapContext);
                if (status) {
                    LOGGER.info("创建BaseServiceImpl文件：【成功】" + serviceImplPath);
                } else {
                    LOGGER.info("创建BaseServiceImpl文件：【失败】" + serviceImplPath);
                }
            } else {
                LOGGER.info("生成BaseServiceImpl异常");
            }
        }
        return true;
    }


    public GenerateTable getTable() {
        return table;
    }

    public void setTable(GenerateTable table) {
        this.table = table;
    }
}
