/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn12JdkTimerExecutor
 * 描述信息: 
 * 创建日期：2015年12月16日 下午4:23:28 
 * @author malitao
 * @version 
 */
package jdk3compare;

/** 
 *  
 *  不同于timer 当一个任务挂掉后，其他的任务不会出问题
 *  
 * 创建日期：2015年12月16日 下午4:23:28 
 * @author malitao
 */

import java.util.concurrent.Executors;  
import java.util.concurrent.ScheduledExecutorService;  
import java.util.concurrent.TimeUnit;  
  
public class ScheduledExecutorTest {  
    //线程池能按时间计划来执行任务，允许用户设定计划执行任务的时间，int类型的参数是设定  
    //线程池中线程的最小数目。当任务较多时，线程池可能会自动创建更多的工作线程来执行任务  
    //此处用Executors.newSingleThreadScheduledExecutor()更佳。
    public ScheduledExecutorService scheduExec = Executors.newScheduledThreadPool(1);  
    //启动计时器  
    public void lanuchTimer(){  
        Runnable task = new Runnable() {  
            public void run() {  
                throw new RuntimeException(); 
            }  
        };  
        scheduExec.scheduleWithFixedDelay(task, 1000*5, 1000*10, TimeUnit.MILLISECONDS);  
    }  
    //添加新任务  
    public void addOneTask(){  
        Runnable task = new Runnable() {  
            public void run() {  
                System.out.println("welcome to china");  
            }  
        };  
        scheduExec.scheduleWithFixedDelay(task, 1000*1, 1000, TimeUnit.MILLISECONDS);  
    }  
      
    public static void main(String[] args) throws Exception {  
        ScheduledExecutorTest test = new ScheduledExecutorTest();  
        test.lanuchTimer();  
        Thread.sleep(1000*5);//5秒钟之后添加新任务  
        test.addOneTask();  
    }  
}