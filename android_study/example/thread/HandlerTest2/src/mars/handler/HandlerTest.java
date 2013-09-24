package mars.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

public class HandlerTest extends Activity {
	private Handler handler = new Handler();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// sendMessage();
		//handler.post(r);
		setContentView(R.layout.main);
		Thread t = new Thread(r);
		t.start();
		System.out.println("activity--->" + Thread.currentThread().getId());
		System.out.println("activityname--->" + Thread.currentThread().getName());
	}
	
	Runnable r = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("handler--->" + Thread.currentThread().getId());
			System.out.println("handlername--->" + Thread.currentThread().getName());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};

}