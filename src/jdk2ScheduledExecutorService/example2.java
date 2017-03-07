/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn12JdkTimerExecutor
 * 描述信息: 
 * 创建日期：2015年12月16日 下午4:38:46 
 * @author malitao
 * @version 
 */
package jdk2ScheduledExecutorService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 也是example3中的内容
 * http://blog.csdn.net/tsyj810883979/article/details/8481621
 *  接口scheduleAtFixedRate原型定义及参数说明
 *  public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
				long initialDelay,
				long period,
				TimeUnit unit);
	command：执行线程
	initialDelay：初始化延时
	period：两次开始执行最小间隔时间
	unit：计时单位

	接口scheduleWithFixedDelay原型定义及参数说明
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
				long initialDelay,
				long delay,
				TimeUnit unit);
	command：执行线程
	initialDelay：初始化延时
	period：前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
	unit：计时单位
 * 
 * 
 * http://blog.sina.com.cn/s/blog_8e48f19a01018y4h.html
 * 
 * ScheduledExecutorService接口
在ExecutorService的基础上，ScheduledExecutorService提供了按时间安排执行任务的功能，它提供的方法主要有：
schedule(task,initDelay):安排所提交的Callable或Runnable任务在initDelay指定的时间后执行。
scheduleAtFixedRate()：安排所提交的Runnable任务按指定的间隔重复执行
scheduleWithFixedDelay()：安排所提交的Runnable任务在每次执行完后，等待delay所指定的时间后重复执行。

这个例子有两个任务，第一个任务每隔一秒打印一句“Taskrepeating”,第二个任务在5秒钟后取消第一个任务。
*1:初始化一个ScheduledExecutorService对象，这个对象的线程池大小为2。
*2:用内函数的方式定义了一个Runnable任务。
*3:调用所定义的ScheduledExecutorService对象来执行任务，任务每秒执行一次。能重复执行的任务一定是 Runnable类型。注意我们可以用TimeUnit来制定时间单位，这也是Java5.0里新的特征，5.0以前的记时单位是微秒，现在可精确到奈秒。
*4:调用ScheduledExecutorService对象来执行第二个任务，第二个任务所作的就是在5秒钟后取消第一个任务。
*5:关闭服务。
*
 * 创建日期：2015年12月16日 下午4:38:46
 * 
 * @author malitao
 */

public class example2 {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// *1
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		// *2
		Runnable task1 = new Runnable() {
			public void run() {
				System.out.println("Taskrepeating.");
			}
		};
		// *3
		final ScheduledFuture future1 = service.scheduleAtFixedRate(task1, 0, 1, TimeUnit.SECONDS);
		// *4
		ScheduledFuture future2 = service.schedule(new Callable() {
			public String call() {
				future1.cancel(true);
				return "taskcancelled!";
			}
		}, 10, TimeUnit.SECONDS);
		System.out.println(future2.get());
		// *5
		service.shutdown();
	}
}