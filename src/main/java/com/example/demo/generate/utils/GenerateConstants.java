package com.example.demo.generate.utils;

/**
 * Created by apple on 2018/8/20.
 */
public class GenerateConstants {

    /**
     * 分隔符
     */
    public interface Separator{
        /**表名后缀*/
        public static String TABLE_SUFFIX = "_T";
        /**分隔符'_'*/
        public static String UPDERLINE = "_";
        /**逗号*/
        public static String COMMA = ",";
        /**反斜杠*/
        public static String BACKSLASH = "\\";
        /**斜杠*/
        public static String SLASH = "/";
        /**点*/
        public static String DOT = ".";
        /**回车换行*/
        public static String CRLF = "\r\n";
        /**单引号*/
        public static String SINGLE_QUOTES = "'";
        /**双引号*/
        public static String SINGLE_DOUBLEQUOTES = "\"";
        /**叹号*/
        public static String EXCLAMATION_MARK = "!";
        /**空格*/
        public static String BLANK = " ";
        /**百分号*/
        public static String PERCENT = "%";
        /**两位小数*/
        public static String TWO_DIGITS_DECIMALS = "#.00";
        /**连接符*/
        public static String CONNECTOR = "-";
        /**顿号*/
        public static String PAUSE = "、";
        /**左括号*/
        public static String LBRACKET = "(";
        /**右括号*/
        public static String RBRACKET = ")";
        /**冒号*/
        public static String COLON = ":";
    }

    /**
     * 文件后缀类型
     */
    public interface FileType{
        /**xml后缀*/
        public static String XML_SUFFIX = ".xml";
        /**java后缀*/
        public static String JAVA_SUFFIX = ".java";
        /**jsp后缀*/
        public static String JSP_SUFFIX = ".jsp";
        /**图片后缀*/
        public static String IMGER_JPG_SUFFIX = "jpg";
        /**pdf后缀 */
        public static String PDF_SUFFIX	=".pdf";
    }

    public interface PageParams{
        /**页面列表数据的累计总和*/
        public static String START = "start";
        /**页面请求页数 */
        public static String DRAW = "draw";
        /**请求的页数*/
        public static String PAGE_NO = "pageNo";
        /**每页显示的变量*/
        public static String LENGTH = "length";
        /**页面起始页*/
        public 	static final int PAGE_START = 1;
        /**模糊检索参数后缀*/
        public static String FUZZY_SEARCH_PARAM_SUFFIX = "Like";
        /**或查询前缀*/
        String OR = "OR";
    }


    /**
     * 是否删除
     * */
    public interface DeleteStatus{
        //所有禁用为1
        public static Integer DISABLED = 1;
        //所有启用为0
        public static Integer ENABLED = 0;
    }
}
