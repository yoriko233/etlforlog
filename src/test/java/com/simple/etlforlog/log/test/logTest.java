package com.simple.etlforlog.log.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Test;

public class logTest {
	/**
	 * log4J配置测试
	 */
	@Test
	public void test1() {
		Properties logPorperites = new Properties();
		try {
			logPorperites.load(new FileInputStream("src/source/log4j.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger log = Logger.getLogger(logTest.class);
		log.debug("debug");
		log.info("info");
		log.error("error");
	}
}
