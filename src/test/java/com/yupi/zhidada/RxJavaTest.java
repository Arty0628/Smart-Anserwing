package com.yupi.zhidada;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RxJavaTest {
    @Test
    public void test() throws InterruptedException {
        //创建数据流
        Flowable<Long> flowable = Flowable.interval(1, TimeUnit.SECONDS) // 每隔一秒产生一个数据
                .map(i -> i + 1)
                .subscribeOn(Schedulers.io());//指定使用的线程池
        // 订阅Flowable 打印接受到的数字
        flowable
                .observeOn(Schedulers.io())// 给下面的操作指定线程池
                .doOnNext(item -> System.out.println(item.toString()))
                .subscribe();
        //主线程睡眠，以便观察到结果
        Thread.sleep(10000L);
    }

    //以下代码，JVM设置为 -Xms64m -Xmx64m 后会报OOM
    //RxJava自定义的线程池使用的是无界队列，会一直堆积

//    @Test
//    public void test1() {
//        Scheduler io = Schedulers.io();
//        while(true){
//            io.scheduleDirect(() -> {
//                System.out.println(Thread.currentThread().getName() + "hello");
//                try{
//                    Thread.sleep(50000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//    }

}
