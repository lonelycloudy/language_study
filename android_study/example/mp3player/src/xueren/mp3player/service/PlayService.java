package xueren.mp3player.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Queue;

import xueren.lrc.LrcProcessor;
import xueren.model.Mp3Info;
import xueren.mp3player.AppConstant;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;

public class PlayService extends Service{
	private MediaPlayer mediaPlayer = null;
	Mp3Info mp3Info = null;
	private boolean isPause = false;
	private boolean isPlaying = false;
	private boolean isReleased = false;
	private UpdateTimeCallback updateTimeCallback = null;
	ArrayList<Queue> queues = null;
	private Handler handler = new Handler();
	
	private long begin = 0;
	private long nextTimeMill = 0;
	private long currentTimeMill = 0;
	private long pauseTimeMill = 0;
	long offset = 0;
	private String message = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("PlayService Is Starting!");
		mp3Info = (Mp3Info) intent.getSerializableExtra("mp3Info");
		int MSG = intent.getIntExtra("MSG", 0);
		if(mp3Info != null){
			if(MSG == AppConstant.PlayerMsg.PLAY_MSG){
				if(!isPlaying){
					play(mp3Info);
				}
			}
		}else{
			if(MSG == AppConstant.PlayerMsg.PAUSE_MSG){
				pause();
			}else if(MSG == AppConstant.PlayerMsg.STOP_MSG){
					stop();
			}
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void play(Mp3Info mp3Info){
		String path = getMp3Path(mp3Info);
		mediaPlayer = MediaPlayer.create(PlayService.this, Uri.parse("file://" + path));
		mediaPlayer.setLooping(false);
		mediaPlayer.start();
		isPlaying = true;
		isReleased = false;
		begin = System.currentTimeMillis();
		nextTimeMill = 0;
		currentTimeMill = 0;
		prepareLrc(mp3Info.getLrcName());
		//执行updateTimeCallback
		handler.post(updateTimeCallback);
	}
	
	private void pause(){
		if(mediaPlayer != null){
			if(!isReleased){
				if(!isPause){
					mediaPlayer.pause();
					isPause = true;
					isPlaying = true;
					//从handler当中移除updateTimeCallback;
					handler.removeCallbacks(updateTimeCallback);
					pauseTimeMill = System.currentTimeMillis();
				}
				else{
					mediaPlayer.start();
					isPause = false;
					handler.post(updateTimeCallback);
				}
			}
		}
	}
	
	private void stop(){
		if(mediaPlayer != null){
			if(isPlaying){
				if(!isReleased){
					mediaPlayer.stop();
					mediaPlayer.release();
					isReleased = true;
				}
				isPlaying = false;
				//从handler当中移除updateTimeCallback;
				handler.removeCallbacks(updateTimeCallback);
			}
		}
	}
	private String getMp3Path(Mp3Info mp3Info){
		String SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
		String path = SDCardRoot + File.separator + "mp3" + File.separator + mp3Info.getMp3Name();
		return path;
	}
	
	//更新歌词
	class UpdateTimeCallback implements Runnable{
		Queue<Long> times = null;
		Queue<String> messages = null;
		String mp3Name = null;
		public UpdateTimeCallback(ArrayList<Queue> queues){
			times = queues.get(0);
			messages = queues.get(1);
			mp3Name = mp3Info.getMp3Name();
		}
		public void run(){
			
			//计算偏移量，也就是说从开始播放MP3到现在为止，共消耗了多少时间，以毫秒为单位
			if(pauseTimeMill != 0){
				System.out.println(pauseTimeMill);
				begin = begin + (System.currentTimeMillis() - pauseTimeMill);
				pauseTimeMill = 0;
			}
			offset = System.currentTimeMillis() - begin;
			System.out.println(offset);
			if(currentTimeMill == 0){
				nextTimeMill = (Long)times.poll();
				message = (String)messages.poll();
			}
			if(offset >= nextTimeMill){
				//广播歌词
				Intent i = new Intent();
				i.putExtra("lrcMessage", message);
				i.putExtra("mp3Name", mp3Name);
				i.setAction("updateLrc");
				sendBroadcast(i);
				//lrcTextView.setText(message);
				nextTimeMill = (Long)times.poll();
				message = (String)messages.poll();
			}
			currentTimeMill = currentTimeMill + 1000;
			handler.postDelayed(updateTimeCallback,1000);
		}
	}
	
	/*根据歌词文件的名字，来读取歌词文件当中的信息*/
	private void prepareLrc(String lrcName){
		try{
			InputStream inputStream = new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+ "/mp3/" + lrcName);
			LrcProcessor lrcProcessor = new LrcProcessor();
			ArrayList<Queue> queues = lrcProcessor.process(inputStream);
			updateTimeCallback = new UpdateTimeCallback(queues);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
