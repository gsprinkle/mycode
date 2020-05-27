package com.gspit.mycode.create;
/** 

* @author 作者 郭小雨

* @version 创建时间：2020年5月26日 下午4:56:17 

* 类说明 创建日期文件夹
* Calendar  
* day of week 需要-1，而month 需要+1
	getActualMaximum（field）  获取实际的最大值
*/

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.junit.Test;

public class CreateFolderWithBatch {
	// 周数字数组
	public static final String[] WEEK_STR = {"","一","二","三","四","五","六","日"}; 
	// 第一步是确定文件路径
	String baseDir = "C:\\Users\\Administrator\\Desktop\\花云里幼儿园\\";

	@Test
	public void createFolderForMonth() {
		Calendar instance = Calendar.getInstance();
		// 当月最大天数
		int leastMaximum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 当天是周几
		int week = instance.get(Calendar.DAY_OF_WEEK) - 1;

		System.out.println(week);
	}

	// 花云里公众号每日任务
	// 创建某月的工作日文件夹
	public void createFolder(int year, int month) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);
		// month 是从-开始，所以设置的时候要 -1
		cal.set(Calendar.MONTH, month - 1);
		
		// 创建月份文件夹，名称为年月
		String monthDir = baseDir +  year + "年" + month + "月份" + "\\";
		File fm = new File(monthDir);
		fm.mkdir();
		baseDir = monthDir;
		for (int date = 1; date <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); date++) {
			cal.set(Calendar.DATE, date);
			// 月份 month 是从-开始，所以获取的时候要 +1
			int m = cal.get(Calendar.MONTH) + 1;
			// 当天是周几 从周日开始，所以要 -1
			int week = (cal.get(Calendar.DAY_OF_WEEK) - 1) > 0 ? (cal.get(Calendar.DAY_OF_WEEK) - 1) : 7;
			int day = cal.get(Calendar.DATE);
			// 首先判断是否是周末，周末略过，不是周末则创建
			if (week < 6) {
				// 创建日期文件夹
				String name = year + "年" + m + "月" + day + "日-周" + WEEK_STR[week];
				String dirName = baseDir + name;
				File dir = new File(dirName);
				dir.mkdir();
				// 在文件夹里面创建文件:年月日 + 任务.doc
				File file = new File(dirName + "\\" + name + "学习任务.docx");
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("创建 \"" + dir.getAbsolutePath() + "\"文件夹成功！");
			}
			// System.out.println("今天是" + year + "年" + m +"月，" + date + "号，周" +
			// week);
		}
	}

	public static void main(String[] args) {
		CreateFolderWithBatch cf = new CreateFolderWithBatch();
		// 根据年、月两个参数 创建花云里幼儿园公众号每日任务文件夹和基础文件
		// cf.createFolder(2020, 5);

	}

}
