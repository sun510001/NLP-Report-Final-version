package NLP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class SearchClass {
	
	public int searchclass(Twitter twitter,String keyword,int stopflag2) throws TwitterException, InterruptedException, IOException{
		//Start keyword search	

		//name the searching file
		String filename = null;
		Date stime = new Date();
		SimpleDateFormat date = new SimpleDateFormat("'D'yyyyMMdd'T'HHmmss"); 
		filename = date.format(stime)+"="+keyword+".txt";
		

		File file = new File(filename);//new file to save tweets.
		Query query = new Query();
		query.setQuery(keyword);
		query.setCount(100);
		//int flag = 1;
		
		
		while(true) {
			PrintWriter con = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
			QueryResult result = twitter.search(query);

			System.out.println("Result:"+result.getTweets().size());
			//System.out.println("Page:"+new Integer(i).toString());
			
			for(Status tweet:result.getTweets()) {
				//System.out.println(result.getTweets());
				if((!tweet.isRetweet())||(tweet.getUser().getName()!=keyword)) {//filter retweet and keyword's tweet
					String gtext = tweet.getText();
					StringTokenizer gt = new StringTokenizer(gtext,"\\s*|\t|\r|\n");
					//con.print(flag+".|>");//count tweets
					//flag++;
					while(gt.hasMoreTokens()) {
						String word = gt.nextToken();
						if(word.indexOf("RT") == -1/*word.indexOf("#") == -1 && word.indexOf("閿涳拷") == -1 && word.indexOf("http") == -1 && word.indexOf("@") == -1*/)
						{
							//word.replaceAll("\\s*|\t|\r|\n", "");
							con.print(word);
							//System.out.print(word);
						}
					}
					//String u = tweet.getUser().getName()+"@"+tweet.getUser().getScreenName();//add user's name and screenname 
					//con.println("<|User:"+u);
					con.println("");
					//System.out.println(u);
				}
			}
			//System.out.println(result.hasNext());
			con.close();
			Thread.sleep(2000);//wait for twitter api time limit after searched 100 tweets
			
			stopflag2++;//180 limit in 15 min (stopflag)
			if(stopflag2 > 150) {
				for(int st=16;st>=1;st--) {//wait 16min
					System.out.println("It will be continue after "+st+" min");
					Thread.sleep(60*1000);	
				}
				stopflag2 = 0;
			}
			System.out.println("SP:"+stopflag2);
			if(result.hasNext()){
				query = result.nextQuery();
			}else{
				System.out.println("Break!");
				break;
			}
		}	
		return stopflag2;	
	}
}
