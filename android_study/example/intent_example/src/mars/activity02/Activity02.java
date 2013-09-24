package mars.activity02;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 这个Activity是应用程序启动只有运行的第一个Activity，在这个Activity当中有一个按钮
 * @author mars_chenchuan
 *
 */
public class Activity02 extends Activity {
    /** Called when the activity is first created. */
	//代表按钮对象的引用
	private Button myButton = null;
	//复写父类当中的onCreate方法，Activity第一次运行时会调用这个方法
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //为Activity设置布局管理文件 
        setContentView(R.layout.main);
        //以下两行代码是根据控件的ID来得到控件对象
        myButton = (Button)findViewById(R.id.myButton);
        //为按钮对象设置监听器对象
        myButton.setOnClickListener(new MyButtonListener());
    }
    //以下是一个内部类，这个内部类的对象是一个监听器（如果大家对监听器不是很熟悉，可以参考设计模式当中的观察者模式）
    class MyButtonListener implements OnClickListener{
    	//生成该类的对象，并将其注册到控件上。如果该控件被用户按下，就会执行onClick方法 
		@Override
		public void onClick(View v) {
			//生成一个Intent对象
			Intent intent = new Intent();
			//在Intent对象当中添加一个键值对
			intent.putExtra("testIntent", "我来自第一个activity");
			//设置Intent对象要启动的Activity
			intent.setClass(Activity02.this, OtherActivity.class);
			//通过Intent对象启动另外一个Activity
			Activity02.this.startActivity(intent);
			/**以下的4行代码将启动发送短信的Activity，详细的情况会在以后的课程当中进行介绍
				Uri uri = Uri.parse("smsto://13718140886");    
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);    
				intent.putExtra("sms_body", "今天你喝了吗?");    
				startActivity(intent);
			 */
		}
    	
    }
}