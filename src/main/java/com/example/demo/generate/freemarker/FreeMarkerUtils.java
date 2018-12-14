package com.example.demo.generate.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

/**
 * freemarker模板、内容合成工具类
 * FreeMarkerUtils
 * @version 1.0.0
 */
@SuppressWarnings("deprecation")
public class FreeMarkerUtils {
	private Configuration cfg = new Configuration();
	private Template template = null;
	private static final String UTF8 = "UTF-8";

	/**
	 * 以模板内容为参数构造模板工具<br>
	 * 创建一个新的实例 FreeMarkerUtils.
	 * 
	 * @param templateContent
	 */
	public FreeMarkerUtils(String templateContent) {
		cfg.setTemplateLoader(new StringTemplateLoader(templateContent));
		cfg.setDefaultEncoding(UTF8);
		cfg.setOutputEncoding(UTF8);
		try {
			template = cfg.getTemplate("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 指定内容和数据进行合并
	 * @param template
	 * @param obj
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	public static String getResult(String template, Object obj) throws Exception{
		
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(new StringTemplateLoader(template));
		cfg.setDefaultEncoding(UTF8);
		cfg.setOutputEncoding(UTF8);
		try {
			Template templateobj = cfg.getTemplate("");
			templateobj.setEncoding(UTF8);
			StringWriter writer = new StringWriter();
			templateobj.process(obj, writer);;
			return writer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
