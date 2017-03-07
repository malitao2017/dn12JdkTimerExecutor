/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn12Timer
 * 描述信息: 
 * 创建日期：2015年12月14日 下午5:22:25 
 * @author malitao
 * @version 
 */
package jdk1timer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/** 
 * 定时器 ：Timer
 * 创建日期：2015年12月14日 下午5:22:25 
 * @author malitao
 */
public class TimerDemo {

	public static void main(String[] args) {
//		demo1();
//		demo2();
//		demo3();
		demo4();
	}
	/**
	 * 定时器演示：周末去爬山
	 *
	 * 创建日期：2015年12月14日 下午5:27:31 
	 * @author malitao
	 */
	public static void demo1(){
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		Date date = cal.getTime();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("去爬香山");
			}
		}, date);
	}
	
	/**
	 * 三秒后爬山	
	 * 创建日期：2015年12月14日 下午5:28:00 
	 * @author malitao
	 */
	public static void demo2(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("去爬香山");
			}
		}, 3000);
	}
	
	/**
	 * 3秒时输出“爆炸”，5秒时取消
	 *
	 * 创建日期：2015年12月14日 下午5:30:28 
	 * @author malitao
	 */
	public static void demo3(){
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("爆炸");
			}
		}, 3000);
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("取消定时器Timer");
				timer.cancel();
			}
		}, 5000);
	}
	
	/**
	 * 倒计时
	 */
	public static void demo4(){
		int min = 1;
		long start = System.currentTimeMillis();
		final long end = start + min*60*1000;
		
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				long show = end-System.currentTimeMillis();
				long hour = show/1000/60/60;
				long m = show/1000/60;
				long second = show/1000%60;
				System.out.println(hour+":"+m+":"+second);
			}
		}, 0,1000);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("结束定时器Timer");
				timer.cancel();
			}
		}, new Date(end));
	}
}
