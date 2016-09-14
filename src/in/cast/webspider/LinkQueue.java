package in.cast.webspider;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
/**
 * 
 * 定义已访问队列，待访问队列和爬取得URL的哈希表，包括出队列，入队列，判断队列是否空等操作
 *
 */
public class LinkQueue {
	//已访问的URl集合
	private static Set visitedUrl =new HashSet();
	//待访问的URL集合
	private static Queue unVisitedUrl=new PriorityQueue();
	//获取Url队列
	public static Queue getUnVisitedUrl(){
		return unVisitedUrl;
	}
	//添加到访问过的URl队列中
	public static void addVisitedUrl(String url){
		
		visitedUrl.add(url);
	}
	//移除访问过的URL
	public static void removeVisitedUrl(String url){
		visitedUrl.remove(url);		
	}
	//未访问的url出队列
	public static Object unVisitedUrlDeQueue(){
		//poll()获取并移除此队列的头，如果此队列为空，则返回null
		return unVisitedUrl.poll();	
	}
	//保证每个url只被访问一次
	public static void addUnvisitedUrl(String url){
		//trim()返回字符串的副本，忽略前导空白和尾部空白,""表示空字符
		if (url!=null&&!url.trim().equals("")&&!visitedUrl.contains(url)
				&&!unVisitedUrl.contains(url)) {
			unVisitedUrl.add(url);
			
		}
	}
	//获得已经访问的URL数目
	public static int getVisitedUrlNum(){
		
		return visitedUrl.size();
	}
	//判断的未访问的URL队列是否为空
	public static boolean unVisitedUrlEmpty(){
		return unVisitedUrl.isEmpty();
	}
}
