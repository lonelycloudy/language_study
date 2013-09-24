package mars.activity03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//1.��Activity03���У�Ҫ�����ĸ��ؼ�
//2.ҪΪ���е������ؼ�������ʾ��ֵ
//3.����һ���������࣬������ť���µĶ���
//4.����������Ķ��󣬰��ڰ�ť������
public class Activity03 extends Activity {
    /** Called when the activity is first created. */
	private EditText factorOne ;
	private EditText factorTwo;
	private TextView symbol;
	private Button calculate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //���ݿؼ���ID��ȡ�ô���ؼ��Ķ���
        factorOne = (EditText)findViewById(R.id.factorOne);
        factorTwo = (EditText)findViewById(R.id.factorTwo);
        symbol = (TextView)findViewById(R.id.symbol);
        calculate = (Button)findViewById(R.id.calculate);
        //Ϊsymbol��calculate������ʾ��ֵ
//        symbol.setText("����");
//        calculate.setText("����");
        symbol.setText(R.string.symbol);
        calculate.setText(R.string.calculate);
        //���������Ķ���󶨵���ť��������
        calculate.setOnClickListener(new CalculateListener());
    }
    //���ͻ����MENU��ť��ʱ�򣬵��ø÷���
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, 1, 1, R.string.exit);
    	menu.add(0,2,2,R.string.about);
		return super.onCreateOptionsMenu(menu);
	}
    //���ͻ�����˵����е�ĳһ��ѡ��ʱ������ø÷���
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == 1){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
	class CalculateListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			//ȡ������EditText�ؼ���ֵ
			String factorOneStr = factorOne.getText().toString();
			String factorTwoStr = factorTwo.getText().toString();
			//��������ֵ��ŵ�Intent������
			Intent intent = new Intent();
			intent.putExtra("one",factorOneStr);
			intent.putExtra("two",factorTwoStr);
			intent.setClass(Activity03.this, ResultActivity.class);
			//ʹ�����Intent����������ResultActivity
			Activity03.this.startActivity(intent);
		}
    }
}