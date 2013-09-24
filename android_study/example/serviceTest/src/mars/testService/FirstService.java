package mars.testService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;

public class FirstService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("onCreate start");
	}
	
	public int onStartCommand(Intent intent,int flags,int startId){
		System.out.println("flags----->" + flags);
		System.out.println("startId---->" + startId);
		System.out.println("onStartComment start");
		return 0;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("Service OnDestroy");
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}
	

}