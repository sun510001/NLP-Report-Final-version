package NLPAnalasis;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import com.atilika.kuromoji.ipadic.Token;
import com.atilika.kuromoji.ipadic.Tokenizer;

public class AnaToken {
	public Double AnaToken(String word) throws IOException{
		
		//String Out = "";//mix the words in tokens
		//String Simple = null;//One word
		//String type = null;
		double temp = 0;
		double sum = 0;
		int flag = 0;
		
        Tokenizer tokenizer = new Tokenizer() ;
        List<Token> tokens = tokenizer.tokenize(word);
        for (Token token : tokens) {
        	//System.out.println(token.getSurface() + "\t" + token.getBaseForm()+"\t"+token.getPartOfSpeechLevel1());
        	//Simple = token.getBaseForm();
        	//type = token.getPartOfSpeechLevel1();
        	
        	PNTable pt = new PNTable();
        	temp = pt.PNTable(token.getBaseForm(),token.getPartOfSpeechLevel1());
        	if(temp!=0) {
        		flag++;
        	}
        	sum = sum + temp;
        	//System.out.println(temp);
        	//Out = Out+Simple;
        }
        if(sum==0) {
        	return 0.000000;
        }
        //sum = (2.5*sum/flag)+2.5;
        //test2018.9.19
        sum = sum / flag;    //now avg sum [-5,+5] 
        sum = 0.5 * sum + 2.5;//sum [0,5]
        //System.out.println("AnaToken.sum = "+sum);
        DecimalFormat df = new DecimalFormat("0.000000");
        return Double.valueOf(df.format(sum).toString());
	}
}
