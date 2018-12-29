package com.example.demo.generate;

import com.example.demo.generate.po.GenerateTable;
import com.example.demo.generate.utils.GenerateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateCodeMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateCodeMain.class);

    /**
     * 自动生成代码入扣 生成PO\VO\Mapper\service\serviceImpl
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            // 把你的表名放在这
            String[] tables = {"house_info"};

            for (String tableName : tables) {
                putDaoFile(tableName); //在dao项目中 生成PO/VO/mapper
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * PO/VO/Mapper 在finup-store-dao 项目中
     *
     * @param tableName
     */
    public static void putDaoFile(String tableName) {

        try {
            // 初始化table信息
            GenerateTable gt = new GenerateTable(tableName, "spiders");
            // 初始化自动生成代码
            GenerateCode code = new GenerateCode(gt);
            // 创建PO文件
            code.generatePoFile();
            //创建vo文件
//            code.generateVoFile();
            // 创建mapper文件
            code.generateMapperFile();
        } catch (Exception e) {
            LOGGER.error("generate po/vo/Mapper files error, errMsg: {}", e.getMessage(), e);
        }
    }
//
//    /**
//     * service 生成 在finup-store-service 项目中
//     *
//     * @param tableName
//     */
//    public static void putBaseSereviceFile(String tableName) {
//        try {
//            // 初始化table信息
//            GenerateTable gt = new GenerateTable(tableName, "finup-store-service");
//            // 初始化自动生成代码
//            GenerateCode code = new GenerateCode(gt);
//            // 创建service文件
//            code.generateBaseServiceFile();
//            //创建serviceImpl文件
//            code.generateBaseServiceImplFile();
//
//        } catch (Exception e) {
//            LOGGER.error("generate BaseService/BaseServiceImpl files error, errMsg: {}", e.getMessage(), e);
//        }
//    }
//    /**
//     * service 生成 在finup-store-web 项目中
//     *
//     * @param tableName
//     */
//    public static void putWebSereviceFile(String tableName) {
//        try {
//            // 初始化table信息
//            GenerateTable gt = new GenerateTable(tableName, "finup-store-web");
//            // 初始化自动生成代码
//            GenerateCode code = new GenerateCode(gt);
//            // 创建service文件
//            code.generateServiceFile();
//            //创建serviceImpl文件
//            code.generateServiceImplFile();
//
//        } catch (Exception e) {
//            LOGGER.error("generate service/serviceImpl files error, errMsg: {}", e.getMessage(), e);
//        }
//    }
//    /**
//     * service 生成 在finup-store-web 项目中
//     *
//     * @param tableName
//     */
//    public static void putAppServerSereviceFile(String tableName) {
//        try {
//            // 初始化table信息
//            GenerateTable gt = new GenerateTable(tableName, "finup-store-app-server");
//            // 初始化自动生成代码
//            GenerateCode code = new GenerateCode(gt);
//            // 创建service文件
//            code.generateServiceFile();
//            //创建serviceImpl文件
//            code.generateServiceImplFile();
//
//        } catch (Exception e) {
//            LOGGER.error("generate service/serviceImpl files error, errMsg: {}", e.getMessage(), e);
//        }
//    }
//    /**
//     * service 生成 在finup-store-schedule-server 项目中
//     *
//     * @param tableName
//     */
//    public static void putScheduleSereviceFile(String tableName) {
//        try {
//            // 初始化table信息
//            GenerateTable gt = new GenerateTable(tableName, "finup-store-schedule-server");
//            // 初始化自动生成代码
//            GenerateCode code = new GenerateCode(gt);
//            // 创建service文件
//            code.generateServiceFile();
//            //创建serviceImpl文件
//            code.generateServiceImplFile();
//
//        } catch (Exception e) {
//            LOGGER.error("generate BaseService/BaseServiceImpl files error, errMsg: {}", e.getMessage(), e);
//        }
//    }
}
