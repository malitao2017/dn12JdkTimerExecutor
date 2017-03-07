/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn12Timer
 * 描述信息: 
 * 创建日期：2015年12月16日 下午3:57:15 
 * @author malitao
 * @version 
 */
package jdk2ScheduledExecutorService;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** 
 *  常用方式，第一个时间是程序运行起后延时多长时间启动，第二个是运行频率
 *  
 * 创建日期：2015年12月16日 下午3:57:15 
 * @author malitao
 */
public class example1 {
	
	static long time = 0l;
	
	public static void main(String[] args) {
		System.out.println(new Date());
		ScheduledExecutorService es=Executors.newSingleThreadScheduledExecutor();
		
		es.scheduleAtFixedRate(new Runnable(){
			public void run() {
				System.out.println("定时：~~~~~~"+" date: "+new Date()+" 距离刚才："+(System.currentTimeMillis()-time));
				time = System.currentTimeMillis();
			}
		}, 1000, 6000,TimeUnit.MILLISECONDS);
	}
	
}
