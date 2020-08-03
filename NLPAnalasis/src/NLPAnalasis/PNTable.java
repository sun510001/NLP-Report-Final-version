package NLPAnalasis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PNTable {
	public double PNTable(String SimpleWord,String WordType) throws IOException {//Analysis the word is postitive or negiative
		//String SimpleWord = "ののしる";
		//System.out.println(WordType);
		DiffWord difftype = new DiffWord();
		String type = null;
		if(difftype.DiffWord(WordType, "名詞")) {
			type = "名詞";
		}else if(difftype.DiffWord(WordType, "動詞")) {
			type = "動詞";
		}else if(difftype.DiffWord(WordType, "形容詞")) {
			type = "形容詞";
		}else if(difftype.DiffWord(WordType, "副詞")) {
			type = "副詞";
		}else {
			return 0;
		}
		//System.out.println(type);
		System.out.print(SimpleWord+" ");//debug mode 2-------------------------------------------<
		File file = new File("pn_ja0.9+"+type+".dic");
		FileInputStream a = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(a,"utf-8"));
		String sim1 = null;
		double sum = 0;
		double temp = 0;
		boolean first = false;//jump the First line
		boolean stop = false;
		while((sim1 = reader.readLine())!=null){
			if(first==false) {
				first = true;
				continue;
			}

			//System.out.println(sim1);
			StringTokenizer sim2 = new StringTokenizer(sim1,":");
			for(int i=0;i<2;i++) {
				String gt = sim2.nextToken();
				//System.out.println(gt);
				
				DiffWord diff = new DiffWord();
				if(diff.DiffWord(SimpleWord, gt)) {
					//System.out.println("!!!!!!!!!!!!!");
					if(i==0) {//kannji
						//System.out.println(sim2.nextToken());
						sim2.nextToken();
						//System.out.println(sim2.nextToken());
						sim2.nextToken();
						temp = Double.valueOf(sim2.nextToken().toString());
					}else {//hiragana
						sim2.nextToken();
						//System.out.println(sim2.nextToken());
						temp = Double.valueOf(sim2.nextToken().toString());
					}
					stop = true;
					break;
				}
			}
			if(stop==true) {
				break;
			}
			
		}
		//test2018.9.19
		if(temp<0) {
			temp = 50 * temp + 45;
		}else if(temp>0){
			temp = 50 * temp - 45;
		}else {
			temp = 0;
		}
		System.out.println(temp);//debug mode 3--------------------------------------<
		return temp;
	}
}
