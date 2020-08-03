package NLPAnalasis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class AnaApp {
	public static void main(String[] args) throws IOException {
		System.out.println("NLPAnalasis start...");
		
		String str = null;
		
		File file = new File("D20180912T022211=三千院.txt");
		FileInputStream a = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(a,"utf-8"));
		double All = 0;
		double Tempstr = 0;
		int Allflag = 0;
		int Count = 0;
		//while((str = reader.readLine())!=null){
		for(int i=1;i<=1000;i++) {
			if((str=reader.readLine())!=null){
				System.out.println("T-->"+str);
				
				AnaToken ana = new AnaToken();
				Tempstr = ana.AnaToken(str);
				Allflag++;
				if(Tempstr != 0){
					Count++;
				}
				All = All + Tempstr;
				System.out.println("AnaApp.Tempstr = "+Tempstr);//return from AnaToken is String type  //debug mode1-----------------------------<
			}
		}//for3000
		DecimalFormat df = new DecimalFormat("0.00");
        All = Double.valueOf(df.format(All/Count).toString());
        System.out.println("**************************Result******************************");
        System.out.println("Sum of tweets: "+Allflag);
        System.out.println("Sum of the tweets includes feelings: "+Count);
        System.out.println("The Grade of all tweets(0-5): "+All);
	}

}
