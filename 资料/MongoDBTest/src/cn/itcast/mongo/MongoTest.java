package cn.itcast.mongo;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;

/**
 * ʹ��java����Mongodb
 * 
 * @author zhaoqx
 * 
 */
public class MongoTest {

	// ��ѯ�����е������ĵ�
	@Test
	public void test1() throws Exception {
		Mongo mongo = new Mongo("localhost", 27017);

		DB db = mongo.getDB("test");

		DBCollection collection = db.getCollection("person");

		DBCursor find = collection.find();
		while (find.hasNext()) {
			System.out.println(find.next());
		}

		mongo.close();
	}

	// �������ɾ�����519f36b8a5827a330bb59d2d,user99990
	@Test
	public void test2() throws UnknownHostException {
		Mongo mongo = new Mongo("localhost", 27017);

		DB db = mongo.getDB("test");

		DBCollection collection = db.getCollection("person");
		
		//BasicDBObject o = new BasicDBObject("_id",new ObjectId("519f36b8a5827a330bb59d2d"));
		BasicDBObject o = new BasicDBObject("name","user99990");
		WriteResult remove = collection.remove(o);
		System.out.println(remove.getN());
		mongo.close();
	}
	
	//�򼯺��в����ĵ�
	@Test
	public void test3() throws Exception{

		Mongo mongo = new Mongo("localhost", 27017);

		DB db = mongo.getDB("test");

		DBCollection collection = db.getCollection("person");
		
		BasicDBObject o = new BasicDBObject();
		o.put("name", "zhangsan");
		o.put("age", 20);
		
		collection.insert(o);
		mongo.close();
	}
	
	//���¼����е��ĵ� 519f3d9d47845d93bb56a0e6
	@Test
	public void test4() throws Exception{

		Mongo mongo = new Mongo("localhost", 27017);

		DB db = mongo.getDB("test");

		DBCollection collection = db.getCollection("person");
		
		BasicDBObject query = new BasicDBObject("_id",new ObjectId("519f3d9d47845d93bb56a0e6"));
		
		BasicDBObject obj = (BasicDBObject) collection.findOne(query);
		
		obj.put("age",30);
		obj.put("address", "bj");
		
		collection.update(query, obj);
		
		mongo.close();
	}
}
