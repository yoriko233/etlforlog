package com.simple.etlforlog.driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import com.simple.utils.DateParserUtil;

/**
 * @description EtlJob执行类
 * @author renweidong
 *
 */
public class EtlDriver extends Configured implements Tool, AbstrcactEtlDiver {
	public static Logger log = Logger.getLogger(EtlDriver.class);
	public static Map<String, String> propertiesMap = null;

	public EtlDriver() {
		Properties logProperites = new Properties();
		Properties hadoopProperites = new Properties();
		try {
			logProperites.load(new FileInputStream("src/source/log4j.properties"));
			hadoopProperites.load(new FileInputStream("src/source/hadoop.properties"));
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}
		Enumeration<String> enum1 = (Enumeration<String>) hadoopProperites.propertyNames();
		propertiesMap = new HashMap<String, String>();
		while (enum1.hasMoreElements()) {
			String tmpKey = enum1.nextElement();
			String tmpValue = hadoopProperites.getProperty(tmpKey);
			propertiesMap.put(tmpKey, tmpValue);
		}

	}

	public void start() {
		System.setProperty("HADOOP_USER_NAME", propertiesMap.get("HADOOP_USER_NAME"));
		int status = 0;
		try {
			status = ToolRunner.run(new EtlDriver(), null);
		} catch (Exception e) {
			log.error(e);
		} finally {
			System.exit(status);
		}
	}

	public int run(String[] arg0) throws Exception {
		
		String inputPath = propertiesMap.get("HADOOP_INPUT_PATH");
		String outputPath = propertiesMap.get("HADDOP_OUTPUT_PATH");
		String tmpPath = propertiesMap.get("HADOOP_TMP_PATH");
		
		String yesterday = DateParserUtil.getYestoday();
		
		int MRstauts = 0;//MR运行状态
		Configuration conf = getConf();
		
		Job job = Job.getInstance(conf);
		job.setJobName(propertiesMap.get("HADOOP_JOB1_NAME"));
		job.setJarByClass(EtlDriver.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.setMapperClass(Mapper.class);
		job.setReducerClass(Reducer.class);
		
		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(tmpPath));
		
		MRstauts = job.waitForCompletion(true)?0:1;
		
		log.info("Job Finish");
		
		
		return 0;
	}

}
