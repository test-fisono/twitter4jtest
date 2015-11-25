import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.util.StringTokenizer;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
//import twitter4j.MediaEntity;


public class TestTweet {
	public static void main(String[] args) throws TwitterException {

		// 初期化
		Twitter twitter = new TwitterFactory().getInstance();
		Query query = new Query();


		try {
			File file = new File("./tweetsGeo.txt");
				PrintWriter pw;
				//追記で書き込む
				pw = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
		
				///*
				// 検索ワードをセット（試しにバルスを検索）
				query.setQuery("雨");
				// 1度のリクエストで取得するTweetの数（100が最大）
				query.setCount(100);
				query.setSince("2014-11-11");
				query.setUntil("2015-11-11");
				//*/
				
				/*
				// ツイート位置情報をもとに検索
				// ロケーションを設定
				GeoLocation geo = new GeoLocation(35.69384, 139.703549);
				// 検索範囲を指定
				query.setGeoCode(geo, 8.0, Query.KILOMETERS);
				*/
				
				// 1500件（15ページ）最大数
				for (int i = 1; i <= 15; i++) {
					// 検索実行
					QueryResult result = twitter.search(query);
					System.out.println("ヒット数 : " + result.getTweets().size());
					System.out.println("ページ数 : " + new Integer(i).toString());
	          
					// 検索結果を見てみる
					for (Status tweet : result.getTweets()) {
						// 本文
						//String str = tweet.getText();
						GeoLocation g = tweet.getGeoLocation();
						//MediaEntity[] mejdia = tweet.getMediaEntities();
						// ハッシュタグとURLの削除	
						//StringTokenizer sta = new StringTokenizer(str, " ");
						
						/*
						//トークンの出力
						while(sta.hasMoreTokens()) {
							String wk = sta.nextToken();
							if(wk.indexOf("#") == -1 && wk.indexOf("http") == -1
									&& wk.indexOf("RT") == -1 && wk.indexOf("@") == -1){
								pw.println(wk);
							}
						}
						
						String u = tweet.getUser().getName();
						pw.println(u);
						*/
						
						pw.println(g);
						
						
					}

					if(result.hasNext()){
						query = result.nextQuery();
					}else{
						break;
					}
				}
	          pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}