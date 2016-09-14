package in.cast.webspider;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
/**
 * 
 * �����ѷ��ʶ��У������ʶ��к���ȡ��URL�Ĺ�ϣ�����������У�����У��ж϶����Ƿ�յȲ���
 *
 */
public class LinkQueue {
	//�ѷ��ʵ�URl����
	private static Set visitedUrl =new HashSet();
	//�����ʵ�URL����
	private static Queue unVisitedUrl=new PriorityQueue();
	//��ȡUrl����
	public static Queue getUnVisitedUrl(){
		return unVisitedUrl;
	}
	//��ӵ����ʹ���URl������
	public static void addVisitedUrl(String url){
		
		visitedUrl.add(url);
	}
	//�Ƴ����ʹ���URL
	public static void removeVisitedUrl(String url){
		visitedUrl.remove(url);		
	}
	//δ���ʵ�url������
	public static Object unVisitedUrlDeQueue(){
		//poll()��ȡ���Ƴ��˶��е�ͷ������˶���Ϊ�գ��򷵻�null
		return unVisitedUrl.poll();	
	}
	//��֤ÿ��urlֻ������һ��
	public static void addUnvisitedUrl(String url){
		//trim()�����ַ����ĸ���������ǰ���հ׺�β���հ�,""��ʾ���ַ�
		if (url!=null&&!url.trim().equals("")&&!visitedUrl.contains(url)
				&&!unVisitedUrl.contains(url)) {
			unVisitedUrl.add(url);
			
		}
	}
	//����Ѿ����ʵ�URL��Ŀ
	public static int getVisitedUrlNum(){
		
		return visitedUrl.size();
	}
	//�жϵ�δ���ʵ�URL�����Ƿ�Ϊ��
	public static boolean unVisitedUrlEmpty(){
		return unVisitedUrl.isEmpty();
	}
}
