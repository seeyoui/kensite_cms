package test.seeyoui.kensite.junitTest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seeyoui.kensite.bussiness.demo.service.DemoService;

public class JunitDemo {

	@Autowired
	public static DemoService demoService;
	
	@BeforeClass
    public static void init() {
        ApplicationContext   context = new ClassPathXmlApplicationContext("classpath*:spring-*.xml");
        System.out.println(context);
        demoService = (DemoService)context.getBean("demoService");
        System.out.println(demoService);
    }
	
	@Test
	public void test() {
		System.out.println("=====");
		System.out.println(demoService.findTotal(null));
	}
}
