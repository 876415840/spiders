package com.example.demo.generate.utils;


import java.io.*;

public class GenerateFileUtils {
	/**
	 * 读取项目中Resources文件夹下面的文件
	 *
	 * @param filePath
	 * @return
	 */
	public static String readerResourcesFile(String filePath) {
		BufferedReader buffer = null;
		StringBuffer sb = new StringBuffer();
		try {
			String fileName = getClassResources() + "/" + filePath;
			buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
			String line = null;
            while ((line = buffer.readLine()) != null) {
            	sb.append(line);
            	sb.append("\n");
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 写入文件
	 * @param filePath
	 * @param content
	 * @return
	 */
	public static boolean writeFile(String filePath , String content) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				String array[] = filePath.split(GenerateConstants.Separator.SLASH);
				StringBuffer buff = new StringBuffer();
				for(int i=0;i<array.length;i++){
					buff.append(array[i]).append(GenerateConstants.Separator.SLASH);
					if(i>0 && array[i].indexOf(GenerateConstants.Separator.DOT) == -1){
						String path = buff.toString().substring(0, buff.toString().length());
						File mkdirFile = new File(path);
						if(!mkdirFile.exists()  && !mkdirFile.isDirectory()) {
							mkdirFile.mkdir();
						}
					}
				}
				file.createNewFile();
			}else{
				System.out.println("当前文件已经存在 文件路径："+filePath);
				return false;
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}




	/**
	 * @param folderPath 文件路径 (只删除此路径的最末路径下所有文件和文件夹)
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); 	// 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); 		// 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除指定文件夹下所有文件
	 * @param path 文件夹完整绝对路径
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);	// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);	// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	public static String getClassResources(){
		String path =  (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}
}
