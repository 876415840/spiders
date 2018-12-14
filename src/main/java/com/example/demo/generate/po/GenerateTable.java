package com.example.demo.generate.po;



import com.example.demo.generate.utils.GenerateConstants;
import com.example.demo.generate.utils.GenerateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateTable {

	public GenerateTable() {

	}

	public GenerateTable(String tableName, String childSystemName) throws Exception {

		this.tableName = tableName;
		this.childSystemName = childSystemName;
		init();
	}

	/** 接口项目名称 */
	private String childSystemName;
	/** 表前缀 */
	private String tablePrefix;
	/** 去掉模块后的第一个单词 */
	private String tableMiddle;
	/** 表名 */
	private String tableName;
	/** po名称 */
	private String poName;
	/** poName 首字母小写 */
	private String firstLowerPoName;

	private Map<String, Object> paramMaps = new HashMap<String, Object>();

	/** 项目的跟路径 */
	private String workspacesPath;
	/** 是否存在启用字段 */
	private String existDisabled = GenerateConstants.DeleteStatus.DISABLED.toString();
	/** 是否存在UUID字段 */
	private String existUuid = GenerateConstants.DeleteStatus.DISABLED.toString();

	private Map<String, String> excludeColumnMap = new HashMap<String, String>();
	/** 包路径 */
	private List<String> importList = new ArrayList<String>();
	/** 类型Map */
	private Map<String, String> typeMap = new HashMap<String, String>();

	/** 字段列 */
	private List<GenerateColumn> columnList = new ArrayList<GenerateColumn>();
	/** po定义的变量 */
	private List<GenerateColumn> poColumnList = new ArrayList<GenerateColumn>();

	private void init() throws Exception {

		// 提取表的前缀

		//this.tablePrefix = this.tableName.substring(0, this.tableName.indexOf(GenerateConstants.Separator.UPDERLINE));
		this.poName = GenerateUtils.tableToPoName(tableName);
		//this.tableMiddle = this.poName.toLowerCase().substring(tablePrefix.length());
		this.tableMiddle = this.poName.toLowerCase();

		// 首字母小写
		this.firstLowerPoName = this.poName.substring(0, 1).toLowerCase() + this.poName.substring(1);
		// 初始化项目跟路径
		String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")))
				.replaceAll("file:", "").replaceAll("%20", " ").trim();
		path = path.substring(0, path.indexOf("target") - 1);
		path = path.substring(0, path.lastIndexOf("/") + 1);
		this.workspacesPath = path;
		initColumn();
		initMap();

		initExcludeColumn();
	}

	/**
	 * 初始化字段列
	 * 
	 * @throws Exception
	 */
	private void initColumn() throws Exception {

		if (columnList == null || columnList.size() == 0) {
			// 初始化表中所有字段信息
			this.columnList = GenerateUtils.columnToList(tableName);

			for (GenerateColumn column : this.columnList) {
				typeMap.put(column.getType(), column.getType());

			}
		}
	}

	/**
	 * 初始化 map参数，主要数据与模板合成
	 */
	private void initMap() {

		paramMaps.put("poName", this.poName);
		paramMaps.put("firstLowerPoName", this.firstLowerPoName);
		paramMaps.put("childSystemName", this.childSystemName);
//		paramMaps.put("existDisabled", this.existDisabled);
		paramMaps.put("tableName", this.tableName);
//		paramMaps.put("tablePrefix", this.tablePrefix.toLowerCase());
		paramMaps.put("tableMiddle", this.tableMiddle.toLowerCase());

//		paramMaps.put("existUuid", this.existUuid);
		paramMaps.put("tableMiddle", this.tableMiddle);
		paramMaps.put("columnList", this.columnList);
		paramMaps.put("poColumnList", this.poColumnList);
		paramMaps.put("importList", this.importList);
	}

	/***
	 * 初始化排除的列
	 */
	private void initExcludeColumn() {


		Map<String, String> importMap = new HashMap<String, String>();
		for (GenerateColumn column : this.columnList) {
//			if (!excludeColumnMap.containsKey(column.getName())) {
				poColumnList.add(column);
				String importStr = GenerateUtils.dbTypeToImportPath(column.getType());
				if (!importMap.containsKey(importStr)) {
					importMap.put(importStr, null);
					importList.add(importStr);
				}
			}
//		}
	}

	public String getTableName() {

		return tableName;
	}
	
	public void setTableName(String tableName) {

		this.tableName = tableName;
	}

	public List<GenerateColumn> getColumnList() {

		return columnList;
	}

	public void setColumnList(List<GenerateColumn> columnList) {

		this.columnList = columnList;
	}

	public String getPoName() {

		return poName;
	}

	public void setPoName(String poName) {

		this.poName = poName;
	}

	public String getTablePrefix() {

		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {

		this.tablePrefix = tablePrefix;
	}

	public String getTableMiddle() {

		return tableMiddle;
	}

	public void setTableMiddle(String tableMiddle) {

		this.tableMiddle = tableMiddle;
	}

	public String getFirstLowerPoName() {

		return firstLowerPoName;
	}

	public void setFirstLowerPoName(String firstLowerPoName) {

		this.firstLowerPoName = firstLowerPoName;
	}

	public String getExistDisabled() {

		return existDisabled;
	}

	public void setExistDisabled(String existDisabled) {

		this.existDisabled = existDisabled;
	}

	public Map<String, Object> getParamMaps() {

		return paramMaps;
	}

	public void setParamMaps(Map<String, Object> paramMaps) {

		this.paramMaps = paramMaps;
	}

	public String getExistUuid() {

		return existUuid;
	}

	public void setExistUuid(String existUuid) {

		this.existUuid = existUuid;
	}

	public String getWorkspacesPath() {

		return workspacesPath;
	}

	public void setWorkspacesPath(String workspacesPath) {

		this.workspacesPath = workspacesPath;
	}

	public Map<String, String> getExcludeColumnMap() {

		return excludeColumnMap;
	}

	public void setExcludeColumnMap(Map<String, String> excludeColumnMap) {

		this.excludeColumnMap = excludeColumnMap;
	}

	public List<GenerateColumn> getPoColumnList() {

		return poColumnList;
	}

	public void setPoColumnList(List<GenerateColumn> poColumnList) {

		this.poColumnList = poColumnList;
	}

	public List<String> getImportList() {

		return importList;
	}

	public void setImportList(List<String> importList) {

		this.importList = importList;
	}

	public Map<String, String> getTypeMap() {

		return typeMap;
	}

	public void setTypeMap(Map<String, String> typeMap) {

		this.typeMap = typeMap;
	}

	public String getChildSystemName() {

		return childSystemName;
	}

	public void setChildSystemName(String childSystemName) {

		this.childSystemName = childSystemName;
	}

}
