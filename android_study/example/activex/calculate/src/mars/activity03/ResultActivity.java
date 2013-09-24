package mars.activity03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
//1.���ܴ�Activity03���������ݵ�ֵ
//2.��������ֵ�Ļ�
//3.������Ľ����ʾ��Activity��
public class ResultActivity extends Activity{
	private TextView resultView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		resultView = (TextView)findViewById(R.id.result);
		//�õ�Intent�����е�ֵ 
		Intent intent = getIntent();
		String factorOneStr = intent.getStringExtra("one");
		String factorTwoStr = intent.getStringExtra("two");
		int factorOneInt = Integer.parseInt(factorOneStr);
		int factorTwoInt = Integer.parseInt(factorTwoStr);
		//��������ֵ�Ļ�
		int result = factorOneInt * factorTwoInt;
		resultView.setText(result + "");
	}

}
