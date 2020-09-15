package com.study.utils;

import org.joda.time.DateTime;

/**
 * 工具类
 */
public class SysUtils {

	/**
	 *
	 * @return YYYY-MM-dd HH:mm:ss
	 */
	public static String getDate() {

		return new DateTime().toString("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 *
	 * @return YYYY-MM-dd-HH-mm-ss
	 */
	public static String getDateYmd() {
		return new DateTime().toString("yyyy-MM-dd-HH-mm-ss");
	}

	public static String getDateYmD() {
		return new DateTime().toString("yyyy-MM-dd");
	}

	public static void main(String[] args) {
		System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
	}
}
