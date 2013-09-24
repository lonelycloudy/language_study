package mars.activity02;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * ���Activity��Ӧ�ó�������ֻ�����еĵ�һ��Activity�������Activity������һ����ť
 * @author mars_chenchuan
 *
 */
public class Activity02 extends Activity {
    /** Called when the activity is first created. */
	//����ť���������
	private Button myButton = null;
	//��д���൱�е�onCreate������Activity��һ������ʱ������������
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ΪActivity���ò��ֹ����ļ� 
        setContentView(R.layout.main);
        //�������д����Ǹ��ݿؼ���ID���õ��ؼ�����
        myButton = (Button)findViewById(R.id.myButton);
        //Ϊ��ť�������ü���������
        myButton.setOnClickListener(new MyButtonListener());
    }
    //������һ���ڲ��࣬����ڲ���Ķ�����һ���������������ҶԼ��������Ǻ���Ϥ�����Բο����ģʽ���еĹ۲���ģʽ��
    class MyButtonListener implements OnClickListener{
    	//���ɸ���Ķ��󣬲�����ע�ᵽ�ؼ��ϡ�����ÿؼ����û����£��ͻ�ִ��onClick���� 
		@Override
		public void onClick(View v) {
			//����һ��Intent����
			Intent intent = new Intent();
			//��Intent���������һ����ֵ��
			intent.putExtra("testIntent", "�����Ե�һ��activity");
			//����Intent����Ҫ������Activity
			intent.setClass(Activity02.this, OtherActivity.class);
			//ͨ��Intent������������һ��Activity
			Activity02.this.startActivity(intent);
			/**���µ�4�д��뽫�������Ͷ��ŵ�Activity����ϸ����������Ժ�Ŀγ̵��н��н���
				Uri uri = Uri.parse("smsto://13718140886");    
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);    
				intent.putExtra("sms_body", "�����������?");    
				startActivity(intent);
			 */
		}
    	
    }
}