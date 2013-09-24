package mars.activity07;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioTest extends Activity {
    /** Called when the activity is first created. */
	//�Կؼ������������
	private RadioGroup genderGroup = null;
	private RadioButton femaleButton = null;
	private RadioButton maleButton = null;
	private CheckBox swimBox = null;
	private CheckBox runBox = null;
	private CheckBox readBox = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio);
        //ͨ���ؼ���ID���õ�����ؼ��Ķ���
        genderGroup = (RadioGroup)findViewById(R.id.genderGroup);
        femaleButton = (RadioButton)findViewById(R.id.femaleButton);
        maleButton = (RadioButton)findViewById(R.id.maleButton);
        swimBox = (CheckBox)findViewById(R.id.swim);
        runBox = (CheckBox)findViewById(R.id.run);
        readBox = (CheckBox)findViewById(R.id.read);
        //ΪRadioGroup���ü���������Ҫע����ǣ�����ļ�������Button�ؼ��ļ�����������ͬ
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(femaleButton.getId() == checkedId){
					System.out.println("famale");
					Toast.makeText(RadioTest.this, "famle", Toast.LENGTH_SHORT).show();
				}
				else if(maleButton.getId() == checkedId)
				{
					System.out.println("male");
					Toast.makeText(RadioTest.this, "male", Toast.LENGTH_SHORT).show();
				}
			}
		});
        
        //Ϊ��ѡ��ť��Ӽ�����
        swimBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					System.out.println("swim is checked");
					Toast.makeText(RadioTest.this, "swim is checked", Toast.LENGTH_SHORT).show();
				}
				else
				{
					System.out.println("swim is unchecked");
					Toast.makeText(RadioTest.this, "swim is unchecked", Toast.LENGTH_SHORT).show();
				}
			}
		});
        runBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					System.out.println("run is checked");
					Toast.makeText(RadioTest.this, "run is checked", Toast.LENGTH_SHORT).show();
				}
				else
				{
					System.out.println("run is unchecked");
					Toast.makeText(RadioTest.this, "run is unchecked", Toast.LENGTH_SHORT).show();
				}
			}
		});
        readBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					System.out.println("read is checked");
				}
				else
				{
					System.out.println("read is unchecked");
				}
			}
		});
    }
    
}