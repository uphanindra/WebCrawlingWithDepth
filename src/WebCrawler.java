/**
* The WebCrawler is an supporting program for WebCrawlAssignment application
* that implements Iterative Deepening Depth First Search/Traverse (IDDFS/T) algorithm.
* Also save the valid links or url into a mySql table using WebCrawlDBUtil.
*
* @author  Phanindra Uppalapati
* @version 1.0
* @since   2017-02-20 
*/
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
	int depth;
	String url;
	String domain;
	LinkedList<String> linksVisited;
	WebCrawlDBUtil WCDBObj; //Custom DB Utility Java Class to Store links and depth into a table
	
	public WebCrawler(String url,String domain,int depth){
		this.url = url;
		//domain keyword is used to filter out the links to other domains.
		this.domain=domain;
		this.depth = depth;
		this.linksVisited = new LinkedList<String>();
		this.WCDBObj = new WebCrawlDBUtil();
		//Clear the Table
		WCDBObj.deleteAll();
	}
	
	void crawl(){
		Document doc;
		
		if(depth<=0){
			System.out.println("Depth to crawl website should be greterthan 0");
			return;
		}
			
		try {
			doc = Jsoup.connect(this.url).get();
			Elements links = doc.select("a[href]");
			linksVisited.add(this.url);
			WCDBObj.insertRow(this.url, 1);
			System.out.println("link: "+this.url+ ", depth: 1");
			
			
			for(Element link: links){
				//System.out.println("link "+link.attr("href"));
				String linkProcessing = link.attr("href");
				if(linkProcessing.indexOf('/')==0)
					linkProcessing = this.url+linkProcessing;
				
				if(linkProcessing.contains(this.domain))
						crawlChild(linkProcessing,depth-1);
						//System.out.println(linkProcessing+".");
				/*else {
					System.out.println(linkProcessing+" is invalid.");
				}*/
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error occured while connecting to URL '" +this.url+ "'.");
			//e.printStackTrace();
		}
	}
	private void crawlChild(String linkToVisit, int depthIn) {
		// TODO Auto-generated method stub
		
		Document doc;
		
		if(depthIn==0)
			return;
		
		if(linksVisited.contains(linkToVisit))
			return;
		
		try {
			
			doc = Jsoup.connect(linkToVisit).get();
			Elements links = doc.select("a[href]");
			linksVisited.add(linkToVisit);
			WCDBObj.insertRow(linkToVisit, this.depth-depthIn+1);
			System.out.println("link: "+linkToVisit+ ", depth: "+(this.depth-depthIn+1));
			
			for(Element link: links){
				String linkProcessing = link.attr("href");
				if(linkProcessing.indexOf('/')==0)
					linkProcessing = this.url+linkProcessing;
				
				if(linkProcessing.contains(this.domain))
					crawlChild(linkProcessing,depthIn-1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//System.out.println("URL '" +this.url+ "' is not active/not found/not valid.");
			//e.printStackTrace();
		}
		
	}
	

		public void printLinks(){
			
			/*for (String string : linksVisited) {
				System.out.println(string);
			}*/
			//Read the links and depth from table
			
			System.out.println("Valid Links and the Depth");
			WCDBObj.read();
			return;
		}


}
