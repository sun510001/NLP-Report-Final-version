package NLP;


import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetAccessClass {
	public Twitter GetAccess(){ 
		
		ConfigurationBuilder cb = new ConfigurationBuilder();//get access
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("x")
			.setOAuthConsumerSecret("x")
			.setOAuthAccessToken("x")
			.setOAuthAccessTokenSecret("x");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		return twitter;
	
	}
}
