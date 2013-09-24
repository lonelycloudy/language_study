package xueren.model;

import java.io.Serializable;

//һ���������л��Ľӿڣ�һ����ֻ��ʵ����Serializable�ӿڣ����Ķ�����ǿ����л��ġ����л��󷽱�洢�ʹ���
public class Mp3Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String mp3Name;
	private String mp3Size;
	private String lrcName;
	private String lrcSize;
	@Override
	public String toString() {
		return "Mp3Info [id=" + id + ", mp3Name=" + mp3Name + ", mp3Size="
				+ mp3Size + ", ircName=" + lrcName + ", lrcSize=" + lrcSize
				+ "]";
	}
	public Mp3Info() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMp3Name() {
		return mp3Name;
	}
	public Mp3Info(String id, String mp3Name, String mp3Size, String ircName,
			String lrcSize) {
		super();
		this.id = id;
		this.mp3Name = mp3Name;
		this.mp3Size = mp3Size;
		this.lrcName = lrcName;
		this.lrcSize = lrcSize;
	}
	public void setMp3Name(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	public String getMp3Size() {
		return mp3Size;
	}
	public void setMp3Size(String mp3Size) {
		Float b=new Float(mp3Size);
		Float c =(b/(1024*1024));
		String d = String.format("%.2f", c)+"M";
		this.mp3Size = d;
	}
	public String getLrcName() {
		return lrcName;
	}
	public void setLrcName(String lrcName) {
		this.lrcName = lrcName;
	}
	public String getLrcSize() {
		return lrcSize;
	}
	public void setLrcSize(String lrcSize) {
		this.lrcSize = lrcSize;
	}
	
	
}
