package com.simple.etlforlog.interfaces.test;

import org.junit.Test;

import com.simple.etlforlog.driver.AbstrcactEtlDiver;
import com.simple.etlforlog.driver.EtlDriver;

public class interfaceTest {

	/**
	 * 驱动接口测试
	 */
	@Test
	public void test1() {
		AbstrcactEtlDiver etl = new EtlDriver();
		etl.start();
	}
}
