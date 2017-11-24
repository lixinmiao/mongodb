package com.mongo.test;

import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class CreateDBTable {
	public static void main(String[] args) {
		try {
			// connect to mongoDB
			Mongo mongo = new Mongo("localhost", 27017);

			// 类似数据库名
			DB db = mongo.getDB("mydb");

			// 类似数据表名
			DBCollection collection = db.getCollection("caonima");

			for(int i=0;i<1000;i++){
				// create a document to store attributes
				BasicDBObject document = new BasicDBObject();
				document.put("id", i);
				document.put("name", "lmx"+i);
				document.put("message", "hello "+i);
				document.put("score", i);
				// save it into collection named "myCollection"
				collection.insert(document);
			}
			

			// search query
			BasicDBObject searchQuery = new BasicDBObject();
			//searchQuery.put("id", 100);

			DBCursor cursor = collection.find(searchQuery);

			// loop over the cursor and display the result
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

}
