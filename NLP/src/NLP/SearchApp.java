package NLP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class SearchApp {

	public static void main(String args[]) throws TwitterException, InterruptedException {
		
		Twitter twitter = null;
		GetAccessClass ac = new GetAccessClass();
		twitter = ac.GetAccess();
		

		//System.out.println(twitter.getRateLimitStatus());

		
		String keyword = null;
		String filename = null;
		int scantype = 0; 
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter searchtype(0 - One Word Mode; 1 - List Mode): ");
		while(sc.hasNextInt()) {
			scantype = sc.nextInt();
			if(scantype == 0||scantype==1) {
				if(scantype == 0) {
					System.out.println("Please enter the word you want to search: ");
						keyword = sc.next();
						//System.out.println("word: "+keyword);
				}else{
					System.out.println("Please enter the name of the list file(with '.txt'): ");
					 filename = sc.next();
				}
				break;
			}else {
				System.out.println("Error! Wrong munber! Please enter searchtype(0 - One Word Mode; 1 - List Mode): ");
			}
		}sc.close();
		
		try {
			int stopflag = 0;
			int flag = 0;
			//System.out.println("reading...");
			//System.out.println(str);
			if(scantype==0) {
				//keyword = "";
				System.out.println("Start One Word Mode ...");
				SearchClass searchstart = new SearchClass();
				searchstart.searchclass(twitter, keyword, stopflag);
			}else {
				System.out.println("Start List Mode ...");
				String str = null;
				File file2 = new File(filename);//scan the file to get travel point list to be the keyword
				FileInputStream a = new FileInputStream(file2);
				BufferedReader reader = new BufferedReader(new InputStreamReader(a, "UTF-8"));

				

				while((str=reader.readLine()) != null) {
					
					keyword = str;//key word list
					if(flag!=0)//ignore first line of scanning file
					{
						if("".equals(str.trim())) {//ignore blank line
							continue;
						}
						System.out.println("Searching: "+str);
						SearchClass searchstart = new SearchClass();
						stopflag = searchstart.searchclass(twitter, keyword, stopflag);
						flag++;
					}else {
						flag++;
					}
				}reader.close();
			}
			System.out.print("Searching end.");
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}


