package com.test;

/**
 * @author zhangzb
 * @since 2018/6/20 17:05
 */
public class MyVolatile implements Runnable {

    private boolean isRunning = true;

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {

        while (this.isRunning) {
            //System.out.println("没有被停止。。。。。。。。");
        }

        System.out.println("停止。。。。。" + isRunning);
    }

    public static void main(String[] args) throws InterruptedException {
        MyVolatile myVolatile = new MyVolatile();
        Thread t = new Thread(myVolatile);
        t.start();

        Thread.sleep(1000);

        //System.out.println("要设置了" + myVolatile.isRunning);
        myVolatile.setRunning(false);
        //System.out.println("myVolitile中值是====>" + myVolatile.isRunning);

    }
}
