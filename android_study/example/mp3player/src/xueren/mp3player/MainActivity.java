package xueren.mp3player;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

//tabActivity�̳���Activity�����ڲ��������TabHost������ͨ��getTabHost()��ȡ��
public class MainActivity extends TabActivity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //�õ�TabHost���󣬶�TabActivity�Ĳ�����������������
        TabHost tabHost = getTabHost();
        //����һ��Intent���󣬸ö���ָ��һ��Activity
        Intent remoteIntent = new Intent();
        remoteIntent.setClass(this, Mp3ListActivity.class);
      //����һ��TabSpec����������������һ��ҳ
        TabHost.TabSpec remoteSpec = tabHost.newTabSpec("Remote");
      //��android��image��drawable��Դ��һ�����ͣ�����Ҫ�� getResources().getDrawable()����������ͼƬ��Դ
        Resources res = getResources();
      //���ø�ҳ��indicator
        remoteSpec.setIndicator("�������б�", res.getDrawable(android.R.drawable.stat_sys_download));
      //���ø�ҳ������
        remoteSpec.setContent(remoteIntent);
      //�����úõ�TabSpec������ӵ�TabHost����
        tabHost.addTab(remoteSpec);
        
        Intent localIntent = new Intent();
		localIntent.setClass(this, LocalMp3ListActivity.class);
		TabHost.TabSpec localSpec = tabHost.newTabSpec("Local");
		localSpec.setIndicator("�����б�", res.getDrawable(android.R.drawable.stat_sys_upload));
		localSpec.setContent(localIntent);
		tabHost.addTab(localSpec);
        
	}

}
