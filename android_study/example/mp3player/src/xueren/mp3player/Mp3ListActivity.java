package xueren.mp3player;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import xueren.download.HttpDownloader;
import xueren.model.Mp3Info;
import xueren.mp3player.service.DownloadService;
import xueren.xml.Mp3ListContentHandler;
import android.app.Activity;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Mp3ListActivity extends ListActivity{
	List<Mp3Info> mp3Infos = null;   
	
	//创建一个广播接收器(匿名内部类写法)
	BroadcastReceiver b = new BroadcastReceiver() {
			
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String msg = intent.getStringExtra("msg");
			Toast.makeText(Mp3ListActivity.this, msg, Toast.LENGTH_SHORT).show();
		}
	};
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_mp3_list);
        updateListView();
        
        IntentFilter filter = new IntentFilter();
		//为IntentFilter添加一个Action
		filter.addAction("mp3Download");
		//将BroadcastReceiver对象注册到系统当中
		registerReceiver(b, filter);
    }
    
	@Override
	//为每个listItem注册点击事件
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Mp3Info mp3Info = mp3Infos.get(position);
		System.out.println("mp3info----------->" + mp3Info);
		Intent intent = new Intent();
		intent.putExtra("mp3Info", mp3Info); 
		intent.setClass(this,DownloadService.class);
		startService(intent);
	}

	private static final int UPDATE = 1;
	private static final int ABOUT = 2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
    	menu.add(0,UPDATE,1,R.string.mp3list_update);
    	menu.add(0,ABOUT,2,R.string.mp3list_about);
		return super.onCreateOptionsMenu(menu);
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    	if(item.getItemId() == UPDATE){
    		updateListView();
    	}
		return super.onOptionsItemSelected(item);
	}
    
    private SimpleAdapter buildSimpleAdapter(List<Mp3Info> mp3Infos){
    	//生成一个List对象，并按照SimpleAdapter的标准，将Mp3Infos当中的数据添加到List当中去
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		for(Iterator iterator = mp3Infos.iterator();iterator.hasNext();){
			Mp3Info mp3Info = (Mp3Info)iterator.next();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("mp3_name",mp3Info.getMp3Name());
			map.put("mp3_size",mp3Info.getMp3Size());
			list.add(map);
		}
		//创建一个SipleAdapter对象,把list数据以R.layout.mp3info_item布局样式封装起来
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.mp3info_item,new String[]{"mp3_name","mp3_size"},new int[]{R.id.mp3_name,R.id.mp3_size});
		return simpleAdapter;
    }
    private void updateListView(){
    	//下载MP3信息的XML文件
		String xml = downloadXML(AppConstant.URL.BASE_URL + "/resources.xml");
		//对XML进行解析，并将解析的结果放置到Mp3Info对象中，最后将这些Mp3Info放置到List中。
		mp3Infos = parse(xml);
		SimpleAdapter simpleAdapter = buildSimpleAdapter(mp3Infos);
		//将这个SimpleAdapter对象设置到ListActivity中
		setListAdapter(simpleAdapter);
    }
    
    
    //下载xml文件函数，返回值为xml文件字符串
    private String downloadXML(String urlStr){
    	HttpDownloader httpDownloader = new HttpDownloader();
    	String result = httpDownloader.download(urlStr);
    	return result;
    }
    
    private List<Mp3Info> parse(String xmlStr){
    	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    	List<Mp3Info> infos = new ArrayList<Mp3Info>();
    	
    	try {
    		//得到一个新的解析器
			XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
			//xml处理程序对象,处理结果会存到infos这个对象中。
			Mp3ListContentHandler mp3ListContentHandler = new Mp3ListContentHandler(infos);
			//为解析器设置处理程序，就是说通过此对象来处理xml内容
			xmlReader.setContentHandler(mp3ListContentHandler);
			//开始解析，并把要处理的xml字符串进去。
			xmlReader.parse(new InputSource(new StringReader(xmlStr)));
			for(Iterator iterator = infos.iterator();iterator.hasNext();){
				Mp3Info mp3Info = (Mp3Info) iterator.next();
				System.out.println(mp3Info);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return infos;
    	
    }
}