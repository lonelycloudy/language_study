package xueren.mp3player.service;

import xueren.download.HttpDownloader;
import xueren.model.Mp3Info;
import xueren.mp3player.AppConstant;
import xueren.mp3player.Mp3ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service{

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Mp3Info mp3Info = (Mp3Info)intent.getSerializableExtra("mp3Info");
		//System.out.println("service-----------" + mp3Info);
		//创建一个新的线程对象
		DownloadThread downloadThread = new DownloadThread(mp3Info);
		//开启一个新的线程,避免影响当前activity操作
		Thread thread = new Thread(downloadThread);
		thread.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	class DownloadThread implements Runnable{
		private Mp3Info mp3Info = null;
		public DownloadThread(Mp3Info mp3Info) {
			super();
			this.mp3Info = mp3Info;
		}
		@Override
		public void run() {
			// 下载地址 http://219.239.72.244/mp3
			String mp3Url = AppConstant.URL.BASE_URL + "/" + mp3Info.getMp3Name();
			String lrcUrl = AppConstant.URL.BASE_URL + "/" + mp3Info.getLrcName();
			HttpDownloader httpDownloader = new HttpDownloader();
			int result1 = httpDownloader.downFile(mp3Url,"mp3/",mp3Info.getMp3Name());
			int result = httpDownloader.downFile(lrcUrl,"mp3/",mp3Info.getLrcName());
			String resultString = null;
			if(result == -1){
				resultString = "下载失败";
			}else if(result == 0){
				resultString = "文件下载成功";
			}else if(result == 1){
				resultString = "文件已存在，不需要重复下载";
			}
			Intent intent = new Intent();
			intent.putExtra("msg", resultString);
			intent.setAction("mp3Download");
			sendBroadcast(intent);
			System.out.println(resultString);
		}

		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
