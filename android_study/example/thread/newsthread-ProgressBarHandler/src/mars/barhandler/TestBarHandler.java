package mars.barhandler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class TestBarHandler extends Activity {
    /** Called when the activity is first created. */
	//声明控件变量
	ProgressBar bar = null;
	Button startButton = null;
	MyHandler myHandler = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //根据控件的ID得到代表控件的对象,并为按钮设置监听器
        bar = (ProgressBar)findViewById(R.id.bar);
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setOnClickListener(new ButtonListener());
        
        System.out.println("ACTIVITY-------->" + Thread.currentThread().getId());
    }
    //当点击startButton按钮时，就会执行ButtonListener的onClick方法
    class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			bar.setVisibility(View.VISIBLE);
			//生成一个HandlerThread对象，实现了使用Looper来处理消息队列的功能，这个类由Android应用程序框架提供
			HandlerThread handlerThread = new HandlerThread("handler_thread");
			//在使用HandlerThread的getLooper()方法之前，必须先调用该类的start();
			handlerThread.start();
			myHandler = new MyHandler(handlerThread.getLooper());
			
			myHandler.post(updateThread);
		}
    	
    }
    
    class MyHandler extends Handler{
		public MyHandler(){
			
		}
		public MyHandler(Looper looper){
			super(looper);
		}
		@Override
		public void handleMessage(Message msg) {
			System.out.println("Message-------->" + Thread.currentThread().getId());
			bar.setProgress(msg.arg1);
			if(msg.arg1 < 100){
				myHandler.post(updateThread);
			}
		}
	}
    
    //线程类，该类使用匿名内部类的方式进行声明
    Runnable updateThread = new Runnable(){
    	int i = 0 ;
		@Override
		public void run() {
			System.out.println("Begin Thread");
			System.out.println("Thread-------->" + Thread.currentThread().getId());
			i = i + 10 ;
			//得到一个消息对象，Message类是有Android操作系统提供
			Message msg = myHandler.obtainMessage();
			//将msg对象的arg1参数的值设置为i,用arg1和arg2这两个成员变量传递消息，优点是系统性能消耗较少
			msg.arg1 = i ;
			try {
				//设置当前显示睡眠1秒
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//将msg对象加入到消息队列当中
			myHandler.sendMessage(msg);
		}
    };
    
}