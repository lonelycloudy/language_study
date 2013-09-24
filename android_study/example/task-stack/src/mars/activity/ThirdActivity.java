package mars.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThirdActivity extends Activity{

	private Button button = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        button = (Button)findViewById(R.id.thirdButton);
        button.setOnClickListener(new ButtonListener());
    }
    
    class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			 Uri uri = Uri.parse("smsto://0800000123");    
			 Intent it = new Intent(Intent.ACTION_SENDTO, uri);    
			 it.putExtra("sms_body", "The SMS text");    
			 startActivity(it);   
		}
    	
    }
    
}
