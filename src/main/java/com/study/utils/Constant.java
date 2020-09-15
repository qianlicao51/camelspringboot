package com.study.utils;

public enum Constant {

	SUCCESS("0000", "成功"), FAIL("9000", "失败");

	private String code;

	private String name;

	private Constant(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
