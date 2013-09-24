package xueren.mp3player;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Queue;

import xueren.lrc.LrcProcessor;
import xueren.model.Mp3Info;
import xueren.mp3player.service.DownloadService;
import xueren.mp3player.service.PlayService;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerActivity extends Activity{
	ImageButton beginButton = null;
	ImageButton pauseButton = null;
	ImageButton stopButton = null;
	TextView lrcTextView = null;
	TextView mp3NameView = null;
	private Mp3Info mp3Info = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		Intent intent = getIntent();
		mp3Info = (Mp3Info)intent.getSerializableExtra("mp3Info");
		
		//得到三个按钮并注册事件
		mp3NameView = (TextView)findViewById(R.id.mp3Name);
		mp3NameView.setText(mp3Info.getMp3Name() + "");
		
		beginButton = (ImageButton)findViewById(R.id.begin);
		pauseButton = (ImageButton)findViewById(R.id.pause);
		stopButton = (ImageButton)findViewById(R.id.stop);
		beginButton.setOnClickListener(new BeginButtonListener());
		pauseButton.setOnClickListener(new PauseButtonListener());
		stopButton.setOnClickListener(new StopButtonListener());
		lrcTextView = (TextView)findViewById(R.id.lrcText);
		
		IntentFilter filter = new IntentFilter();
		//为IntentFilter添加一个Action
		filter.addAction("updateLrc");
		//将BroadcastReceiver对象注册到系统当中
		registerReceiver(b, filter);
	}
	
	//创建一个歌词信息接收器(匿名内部类写法)
	BroadcastReceiver b = new BroadcastReceiver() {
			
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String lrcMessage = intent.getStringExtra("lrcMessage");
			String mp3Name = intent.getStringExtra("mp3Name");
			if(mp3Info.getMp3Name().equals(mp3Name)){
				lrcTextView.setText(lrcMessage);
			}
		}
	};
	class BeginButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("mp3Info", mp3Info); 
				intent.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
				intent.setClass(PlayerActivity.this,PlayService.class);
				System.out.println("PlayService Is Will Start!");
				startService(intent);
				if(lrcTextView.getText().equals("")){
					lrcTextView.setText("正在加载歌词...");
				}
		}

		
	}
	
	class PauseButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("MSG", AppConstant.PlayerMsg.PAUSE_MSG);
			intent.setClass(PlayerActivity.this,PlayService.class);
			startService(intent);
		}
	}
	
	class StopButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("MSG", AppConstant.PlayerMsg.STOP_MSG);
			intent.setClass(PlayerActivity.this,PlayService.class);
			startService(intent);
			lrcTextView.setText("已停止");
		}
		
	}
	
	
}
