/**
* The WebCrawlAssignment program implements an application that
* crawl website/url into given depth using Iterative Deepening 
* Depth First Search/Traverse (IDDFS) algorithm.
* 
*
* @author  Phanindra Uppalapati
* @version 1.0
* @since   2017-02-20 
*/
public class WebCrawlAssignment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//NOTE: More depth of crawling increase program execution time based on number of unvisited URL's in a page.
		
		WebCrawler crawlerObj = new WebCrawler("https://www.flipkart.com/","flipkart.com",2);
		//WebCrawler crawlerObj = new WebCrawler("http://localhost:80/webCrawl/Web//","localhost:80/webCrawl/Web",3);
		
		System.out.println("Crawling Started..");
		crawlerObj.crawl();
		System.out.println("Reading from Database..");
		crawlerObj.printLinks();
		
	}

}
