package in.cast.webspider;

import java.util.Set;

public class MyCrawler {
/**
 * ʹ�����ӳ�ʼ��url����
 * @return 
 * @param seeds
 * ����url
 */
	private void initCrawlerWithSeeds(String[] seeds){
		for (int i = 0; i < seeds.length; i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}
	/**
	 *ץȡ����
	 * @return 
	 * @param seeds
	 * ����url
	 */
	public void crawling(String[] seeds){//�������������ȡ��http:baidu.com��ͷ������
		LinkFilter filter=new LinkFilter() {
			public boolean accept(String url) {
				if (url.startsWith("http://www.baidu.com")) {//���Դ��ַ����Ƿ���ָ����ǰ׺��ʼ
					return true;
				} else {
					return false;
				}
				
			}
		};
		
		//��ʼ��url����
		initCrawlerWithSeeds(seeds);
		//ѭ����������ץȡ�����Ӳ�����ץȡ����ҳ������1000
		while (!LinkQueue.unVisitedUrlEmpty()
				&&LinkQueue.getVisitedUrlNum()<=1000) {
			//��ͷurl������
			String visitUrl=(String)LinkQueue.unVisitedUrlDeQueue();
			if (visitUrl!= null) {
				continue;
			}
			DomnLoadFile downLoader=new DomnLoadFile();
			//������ҳ
			downLoader.downloadFile(visitUrl);
			//��url���뵽�ѷ��ʵ�url��
			LinkQueue.addVisitedUrl(visitUrl);
			//��ȡ��������ҳ�е�url
			Set<String> links=HtmlParserTool.extracLinks(visitUrl, filter);
			//�µ�δ���ʵ�url���
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
