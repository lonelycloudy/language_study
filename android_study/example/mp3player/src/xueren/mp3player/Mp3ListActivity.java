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
	
	//����һ���㲥������(�����ڲ���д��)
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
		//ΪIntentFilter���һ��Action
		filter.addAction("mp3Download");
		//��BroadcastReceiver����ע�ᵽϵͳ����
		registerReceiver(b, filter);
    }
    
	@Override
	//Ϊÿ��listItemע�����¼�
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
    	//����һ��List���󣬲�����SimpleAdapter�ı�׼����Mp3Infos���е�������ӵ�List����ȥ
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		for(Iterator iterator = mp3Infos.iterator();iterator.hasNext();){
			Mp3Info mp3Info = (Mp3Info)iterator.next();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("mp3_name",mp3Info.getMp3Name());
			map.put("mp3_size",mp3Info.getMp3Size());
			list.add(map);
		}
		//����һ��SipleAdapter����,��list������R.layout.mp3info_item������ʽ��װ����
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.mp3info_item,new String[]{"mp3_name","mp3_size"},new int[]{R.id.mp3_name,R.id.mp3_size});
		return simpleAdapter;
    }
    private void updateListView(){
    	//����MP3��Ϣ��XML�ļ�
		String xml = downloadXML(AppConstant.URL.BASE_URL + "/resources.xml");
		//��XML���н��������������Ľ�����õ�Mp3Info�����У������ЩMp3Info���õ�List�С�
		mp3Infos = parse(xml);
		SimpleAdapter simpleAdapter = buildSimpleAdapter(mp3Infos);
		//�����SimpleAdapter�������õ�ListActivity��
		setListAdapter(simpleAdapter);
    }
    
    
    //����xml�ļ�����������ֵΪxml�ļ��ַ���
    private String downloadXML(String urlStr){
    	HttpDownloader httpDownloader = new HttpDownloader();
    	String result = httpDownloader.download(urlStr);
    	return result;
    }
    
    private List<Mp3Info> parse(String xmlStr){
    	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    	List<Mp3Info> infos = new ArrayList<Mp3Info>();
    	
    	try {
    		//�õ�һ���µĽ�����
			XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
			//xml����������,��������浽infos��������С�
			Mp3ListContentHandler mp3ListContentHandler = new Mp3ListContentHandler(infos);
			//Ϊ���������ô�����򣬾���˵ͨ���˶���������xml����
			xmlReader.setContentHandler(mp3ListContentHandler);
			//��ʼ����������Ҫ�����xml�ַ�����ȥ��
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