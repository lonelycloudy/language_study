package mars.activity02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends Activity{
	private TextView myTextView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
		//ȡ�ô���һ��Activity���д��ݹ�����Intent����
		Intent intent = getIntent();
		//��Intent���и���keyȡ��value
		String value = intent.getStringExtra("testIntent");
		//���ݿؼ���ID�õ���Ӧ�Ŀؼ�����
		myTextView = (TextView)findViewById(R.id.myTextView);
		//Ϊ�ؼ�����Textֵ
		myTextView.setText(value);
		
	}

}
