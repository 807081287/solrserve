package org.jon.lv.solrserve;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
	 * 类描述：junit测试基础类，引入Spring配置文件，以便注入。
	 * @author: zouwei
	 * @version: 2014年5月6日 下午5:30:00
 */ //,"classpath:config/application-quartz.xml"
@ContextConfiguration(
        locations = {"classpath:config/application-common.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore
public class JunitBaseTest {

	@Test
	public void say(){
		System.out.println("hello world");
	}
}
