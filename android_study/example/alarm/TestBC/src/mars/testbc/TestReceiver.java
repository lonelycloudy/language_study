package mars.testbc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TestReceiver extends BroadcastReceiver{

	public TestReceiver(){
		System.out.println("TestReceiver");
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "onReceive", Toast.LENGTH_SHORT).show();
	}
}
