/**   
 * Copyright © 2015 北京恒泰实达科技发展有限公司. All rights reserved.
 * 项目名称：dn12JdkTimerExecutor
 * 描述信息: 
 * 创建日期：2015年12月16日 下午4:22:34 
 * @author malitao
 * @version 
 */
package jdk3compare;

/** 
 * 
 * 个人总结：timer的bug：60秒执行一次的话，如果用户修改了时间的话 那么时针都会归0，本来是临近10秒执行的时候timer又会重新计时一次 再等60才执行。
 * 运行该程序，Timer会抛出一个RumtimeException和java.lang.IllegalStateException:Timer already cancelled.
常言道，真是祸不单行，Timer还将它的问题传染给下一个倒霉的调用者，这个调用者原本试图提交一个TimerTask的，你可能希望程序会一直运行下去，然而实际情况如程序所示5秒钟后就中止了，还伴随着一个异常，异常的消息是"Timer already cancelled"。ScheduledThreadPoolExector妥善地处理了这个异常的任务，所以说在java5.0或更高的JDK中，几乎没有理由再使用Timer了。

 * 创建日期：2015年12月16日 下午4:22:34 
 * @author malitao
 */

import java.util.Timer;  
import java.util.TimerTask;  
  
public class TimerTest {  
    private Timer timer = new Timer();  
    //启动计时器  
    public void lanuchTimer(){  
        timer.schedule(new TimerTask(){  
            public void run() {  
                throw new RuntimeException();  
            }  
        }, 1000*3, 500);  
    }  
    //向计时器添加一个任务  
    public void addOneTask(){  
        timer.schedule(new TimerTask(){  
            public void run(){  
                System.out.println("hello world");  
            }  
        }, 1000*1,1000*5);  
    }  
      
    public static void main(String[] args) throws Exception {  
        TimerTest test = new TimerTest();  
        test.lanuchTimer();  
        Thread.sleep(1000*5);//5秒钟之后添加一个新任务  
        test.addOneTask();  
    }  
}