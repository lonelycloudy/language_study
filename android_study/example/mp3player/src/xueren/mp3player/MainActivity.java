package xueren.mp3player;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

//tabActivity继承自Activity，其内部定义好了TabHost，可以通过getTabHost()获取。
public class MainActivity extends TabActivity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //得到TabHost对象，对TabActivity的操作都用这个对象完成
        TabHost tabHost = getTabHost();
        //生成一个Intent对象，该对象指向一个Activity
        Intent remoteIntent = new Intent();
        remoteIntent.setClass(this, Mp3ListActivity.class);
      //生成一个TabSpec对象，这个对象代表了一个页
        TabHost.TabSpec remoteSpec = tabHost.newTabSpec("Remote");
      //在android中image是drawable资源的一种类型，所以要用 getResources().getDrawable()方法来调用图片资源
        Resources res = getResources();
      //设置该页的indicator
        remoteSpec.setIndicator("服务器列表", res.getDrawable(android.R.drawable.stat_sys_download));
      //设置该页的内容
        remoteSpec.setContent(remoteIntent);
      //将设置好的TabSpec对象添加到TabHost当中
        tabHost.addTab(remoteSpec);
        
        Intent localIntent = new Intent();
		localIntent.setClass(this, LocalMp3ListActivity.class);
		TabHost.TabSpec localSpec = tabHost.newTabSpec("Local");
		localSpec.setIndicator("本地列表", res.getDrawable(android.R.drawable.stat_sys_upload));
		localSpec.setContent(localIntent);
		tabHost.addTab(localSpec);
        
	}

}
