package cn.org.generator;
import cn.org.rapid_framework.generator.GeneratorFacade;

public class GenerationTest {

	public static void main(String[]args)throws Exception{
		GeneratorFacade g = new GeneratorFacade();
//		g.generateByTable("CMS_COMMENT","template_kensite");
//		g.generateByTable("CMS_GUESTBOOK","template_kensite");

//		g.deleteByTable("T_TEST_W","template_kensite_V2");
		g.generateByTable("KS_DATA_SOURCE","template_kensite_V2");
	}
}
