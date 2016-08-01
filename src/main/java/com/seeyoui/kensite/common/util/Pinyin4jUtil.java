package com.seeyoui.kensite.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin4jUtil {

	/**
	 * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（长沙市长:cssc,zssz,zssc,cssz）
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					// 取得当前汉字的所有全拼
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat);
					if (strs != null) {
						for (int j = 0; j < strs.length; j++) {
							// 取首字母
							pinyinName.append(strs[j].charAt(0));
							if (j != strs.length - 1) {
								pinyinName.append(",");
							}
						}
					}
					// else {
					// pinyinName.append(nameChar[i]);
					// }
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName.append(nameChar[i]);
			}
			pinyinName.append(" ");
		}
		// return pinyinName.toString();
		return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
	}

	/**
	 * 汉字转换位汉语全拼，英文字符不变，特殊字符丢失
	 * 支持多音字，生成方式如（重当参:zhongdangcen,zhongdangcan,chongdangcen
	 * ,chongdangshen,zhongdangshen,chongdangcan）
	 * 
	 * @param chines
	 *            汉字
	 * @return 拼音
	 */
	public static String converterToSpell(String chines) {
		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					// 取得当前汉字的所有全拼
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat);
					if (strs != null) {
						for (int j = 0; j < strs.length; j++) {
							pinyinName.append(strs[j]);
							if (j != strs.length - 1) {
								pinyinName.append(",");
							}
						}
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName.append(nameChar[i]);
			}
			pinyinName.append(" ");
		}
		// return pinyinName.toString();
		return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
	}

	/**
	 * 去除多音字重复数据
	 * 
	 * @param theStr
	 * @return
	 */
	private static List<Map<String, Integer>> discountTheChinese(String theStr) {
		// 去除重复拼音后的拼音列表
		List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();
		// 用于处理每个字的多音字，去掉重复
		Map<String, Integer> onlyOne = null;
		String[] firsts = theStr.split(" ");
		// 读出每个汉字的拼音
		for (String str : firsts) {
			onlyOne = new Hashtable<String, Integer>();
			String[] china = str.split(",");
			// 多音字处理
			for (String s : china) {
				Integer count = onlyOne.get(s);
				if (count == null) {
					onlyOne.put(s, new Integer(1));
				} else {
					onlyOne.remove(s);
					count++;
					onlyOne.put(s, count);
				}
			}
			mapList.add(onlyOne);
		}
		return mapList;
	}

	/**
	 * 解析并组合拼音，对象合并方案(推荐使用)
	 * 
	 * @return
	 */
	private static String parseTheChineseByObject(
			List<Map<String, Integer>> list) {
		Map<String, Integer> first = null; // 用于统计每一次,集合组合数据
		// 遍历每一组集合
		for (int i = 0; i < list.size(); i++) {
			// 每一组集合与上一次组合的Map
			Map<String, Integer> temp = new Hashtable<String, Integer>();
			// 第一次循环，first为空
			if (first != null) {
				// 取出上次组合与此次集合的字符，并保存
				for (String s : first.keySet()) {
					for (String s1 : list.get(i).keySet()) {
						String str = s + s1;
						temp.put(str, 1);
					}
				}
				// 清理上一次组合数据
				if (temp != null && temp.size() > 0) {
					first.clear();
				}
			} else {
				for (String s : list.get(i).keySet()) {
					String str = s;
					temp.put(str, 1);
				}
			}
			// 保存组合数据以便下次循环使用
			if (temp != null && temp.size() > 0) {
				first = temp;
			}
		}
		String returnStr = "";
		if (first != null) {
			// 遍历取出组合字符串
			for (String str : first.keySet()) {
				returnStr += (str + ",");
			}
		}
		if (returnStr.length() > 0) {
			returnStr = returnStr.substring(0, returnStr.length() - 1);
		}
		return returnStr;
	}

	public static void main(String[] args) {
		File inputfile = new File("D:/前海人员名单.txt");
		String[] cn = {"张韶辉","陈卫","熊靖波","郭起宏","刘宁武","谢涛","李亚非","傅聪","张蕾","林淯杰","张学和","张国松","韦山","段偲","贺洁","陈文颖","李琳琳","许瓅晔","何婷婷","赵笑梅","邓军","刘首辰","李惠","奚越","张清平","燕琰","江凡","刘洋","张建斌","黄璐娜","陈爱玲","刘良清","何洋","邵明治","杨励耘","陈思宇","杨斯伟","陈昊禹","马雨龙","张军","彭警","刘祖尊","张澜","李艳华","聂勇辉","陈康","于大龙","陈辉棉","武友桐","张昆山","张帅康","冯省快","吴洋","陈阳斌","庄晓丽","耿军","朱洲","陈文涛","钟宜先","刘军林","刘志刚","田宏银","林艳","梁志文","杜寒飞","曾南华","李敏","李鹏辉","唐嘉妮","齐思卉","杨青","何林苡","陈楚茵","张梦楠","王超群","林晓文","彭浩川","蔡寿杰","姚辰亮","蓝婧","吴宝妹","刘姗灵","赖赏萁","李培斌","邹勇强","章颖","聂海峰","肖宇","李秀虹","黄可思","陈佳琦","曾译萱","喻仁杰","丛擎","晏澜","邹琼","盛丽丽","赵新俊","万迪智","王泽铭","徐维娜","颜婧","郑承毅","梁文瑞","冯仕明","林荡","范凯宾","张迅栋","贾强","薛伟琼","崔一楠","唐梦欣","李洲羽","褚龙","黄顺欣","谷奇峰","骆睿","何洋","朱福寿","杨旭明","钟国兵","叶威","张巍","张福财","邓继开","龚朝凯","邓治国","李翔","陈航","梁雅琪","梁家明","游锐","苏毓腾","邱宝茹","龙文武","冯天舜","陈艳","秦亮","陈进得","许家驹","沈小婵","胡海波","郑李梨","史润琼","姜凤英","李伟梁","陈中华","余嘉凤","李然","张植翔","廖静静","张晓珊","黄晓鹏","余思豪","朱志文","黄贵成","周广","黄晓东","陈丽文","郭梅","吴昕雨","刘秋萍","殷晶芳","黄赛庭","胡智颖","肖凤琴","陈佩佩","庄晓丽","向慧明","汤泓","韩雪苹","王咱如","李家慧","廖艳","赵敏","周群","朱素萍","张馨媛","于梅","金晶","陈芳菲","朱楚媚","易剑雄","赖嘉庆","张硕","谢芳伟","龙远坤","侍仲楠","李勇","谭志朋","饶颖钦","李小刚","付晓甫","王晓锋","王振华","梁任新","燕俊","王少新","江燕","陈航滔","张佳霖","曾小蓉","谭媛","田家萁","陈明珠","张安洪","毛雪晴","李俊勤","欧阳辉勇","周振宇","邓建源","陈映","韦志宇","邹灵花","马会婷","樊宝霞","阳佩佩","吴桂芳","赵靓","罗治理","谭华静","刘翊","李伟怡","赵芳","丁朝平","刘泽全","陈恩琪","赵丽丽","佘小伟","谢晓波","赖运兴","兰泽玉","王孔陈","朱智彬","李思远","王侹","李松林","徐锐","周俊","孙飞","李天威","余静燕","莫芳雅","陈浩","林小乔","张荣兰","谢曦","杨斯路","赵洋","何海华","文梓锋","刘宇璇","韩帝","林欢亮","刘海","许玚","于天翊","范诗琴","骆睿","吕朝晖","李翔","左琳","吴颖","陈科","解飞","杨立","彭茜倩","甄永峰","余若婷","李望福","宋爽","袁为民","沈扬","周武新","徐翔","徐霜泷","王广生","朱牡丹","刘晓芬","吴立胜","朱瑶","刘明明","林轩因","李楠","蒋文文","刘琨","邓健源","雷明亮","汪小梅","蔡晓慧","许瑒","李旺福"};
		List<String> cnList = Arrays.asList(cn);
		String[] en = {"zhangshaohui","chenwei","xiongjingbo","guoqihong","liuningwu","xietao","liyafei","fucong","zhanglei","linyujie","zhangxuehe","zhangguosong","weishan","duancai","hejie","chenwenying","lilinlin","xuliye","hetingting","zhaoxiaomei","dengjun","liushouchen","lihui","xiyue","zhangqingping","yanyan","jiangfan","liuyang","zhangjianbin","huangluna","chenailing","liuliangqing","heyang","shaomingzhi","yangliyun","chensiyu","yangsiwei","chenhaoyu","mayulong","zhangjun","pengjing","liuzuzun","zhanglan","liyanhua","nieyonghui","chenkang","yudalong","chenhuimian","wuyoutong","zhangkunshan","zhanghsuaikang","fengshengkuai","wuyang","chenyangbin","zhuanxiaoli","gengjun","zhuzhou","chenwentao","zhongyixian","liujunlin","liuzhigang","tianhongyin","linyan","liangzhiwen","duhanfei","zengnanhua","limin","lipenghui","tangjiani","qisihui","yangqing","helinyi","chenchuyin","zhangmengnan","wangchaoqun","linxiaowen","penghaochuan","caishoujie","yaochenliang","lanjing","wubaomei","liushanling","laishangqi","lipeibin","zouyongqiang","zhangying","niehaifeng","xiaoyu","lixiuhong","huangkesi","chenjiaqi","zengyixuan","yurenjie","congqing","yanlan","zouqiong","shenglili","zhaoxinjun","wandizhi","wangzeming","xuweina","yanjing","zhengchengyi","liangwenrui","fengshiming","lindang","fankaibin","zhangxundong","jiaqiang","xueweiqiong","cuiyinan","tangmengxin","lizhouyu","chulong","huangshunxin","guqifeng","luorui","heyang","zhufushou","yangxuming","zhongguobing","yewei","zhangwei","zhangfucai","dengjikai","gongzhaokai","dengzhiguo","lixiang","chenhang","liangyaqi","liangjiaming","yourui","suyuteng","qiubaoru","longwenwu","fengtianshun","chenyan","qinliang","chenjinde","xujiaju","shenxiaochan","huhaibo","zhenglili","shirunqiong","jiangfengying","liweiliang","chenzhonghua","yujiafeng","liran","zhangzhixiang","liaojingjing","zhangxiaoshan","huangxiaopeng","yusihao","zhuzhiwen","huangguicheng","zhouguang","huangxiaodong","chenliwen","guomei","wuxinyu","liuqiuping","yinjingfang","huangsaiting","huzhiying","xiaofengqin","chenpeipei","zhuangxiaoli","xianghuiming","tanghong","hanxueping","wangzanru","lijiahui","liaoyan","zhaomin","zhouqun","zhusuping","zhangxinyuan","yumei","jinjing","chenfangfei","zhuchumei","yijianxiong","laijiaqing","zhangshuo","xiefangwei","longyuankun","shizhongnan","liyong","tanzhipeng","raoyingqin","lixiaogang","fuxiaofu","wangxiaofeng","wangzhenhua","liangrenxin","yanjun","wangshaoxin","jiangyan","chenhangtao","zhangjialin","zengxiaorong","tanyuan","tianjiaqi","chenmingzhu","zhanganhong","maoxueqing","lijunqin","ouyanghuiyong","zhouzhenyu","dengjianyuan","chenying","weizhiyu","zoulinghua","mahuiting","fanbaoxia","yangpeipei","wuguifang","zhaoliang","luozhili","tanhuajing","liuyi","liweiyi","zhaofang","dingzhaoping","liuzequan","chenenqi","zhaolili","shexiaowei","xiexiaobo","laiyunxing","lanzeyu","wangkongchen","zhuzhibin","lisiyuan","wangting","lisonglin","xurui","zhoujun","sunfei","litianwei","yujingyan","mofangya","chenhao","linxiaoqiao","zhangronglan","xiexi","yangsilu","zhaoyang","hehaihua","wenzifeng","liuyuxuan","handi","linhuanliang","liuhai","xuyang","yutianyi","fanshiqin","luorui","lvzhaohui","lixiang","zuolin","wuying","chenke","xiefei","yangliyun","pengxiqian","zhenyongfeng","xuruoting","liwangfu","songshuang","yuanweimin","shenyang","zhouwuxin","xuxiang","xushuanglong","wangguangsheng","zhumudan","liuxiaofen","wulisheng","zhuyao","liumingming","linxuanyin","linan","jiangwenwen","liukun","dengjianyuan","leimingliang","wangxiaomei","caixiaohui","xuyang","liwangfu"};
		List<String> enList = Arrays.asList(en);
		try {
			FileReader fr = new FileReader(inputfile);
			BufferedReader br = new BufferedReader(fr);
			String s;
			while ((s = br.readLine()) != null) {
				// System.out.println(s.replaceAll(" ", ""));
				if(cnList.indexOf(s) != -1) {
					System.out.println(enList.get(cnList.indexOf(s)));
				} else {
					System.out.println(Pinyin4jUtil.converterToSpell(s.replaceAll(" ", "")));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
