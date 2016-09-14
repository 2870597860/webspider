package in.cast.webspider;

import java.util.Set;

public class MyCrawler {
/**
 * 使用种子初始化url队列
 * @return 
 * @param seeds
 * 种子url
 */
	private void initCrawlerWithSeeds(String[] seeds){
		for (int i = 0; i < seeds.length; i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}
	/**
	 *抓取过程
	 * @return 
	 * @param seeds
	 * 种子url
	 */
	public void crawling(String[] seeds){//定义过滤器，提取以http:baidu.com开头的链接
		LinkFilter filter=new LinkFilter() {
			public boolean accept(String url) {
				if (url.startsWith("http://www.baidu.com")) {//测试此字符串是否以指定的前缀开始
					return true;
				} else {
					return false;
				}
				
			}
		};
		
		//初始化url队列
		initCrawlerWithSeeds(seeds);
		//循环条件：待抓取的链接不空且抓取的网页不超过1000
		while (!LinkQueue.unVisitedUrlEmpty()
				&&LinkQueue.getVisitedUrlNum()<=1000) {
			//对头url出队列
			String visitUrl=(String)LinkQueue.unVisitedUrlDeQueue();
			if (visitUrl!= null) {
				continue;
			}
			DomnLoadFile downLoader=new DomnLoadFile();
			//下载网页
			downLoader.downloadFile(visitUrl);
			//改url放入到已访问的url中
			LinkQueue.addVisitedUrl(visitUrl);
			//提取出下载网页中的url
			Set<String> links=HtmlParserTool.extracLinks(visitUrl, filter);
			//新的未访问的url入队
			for (String link : links) {
				LinkQueue.addUnvisitedUrl(link);
				
			}
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyCrawler crawler=new MyCrawler();
		crawler.crawling(new String[]{"http://www.baidu.com"});
	}

}
