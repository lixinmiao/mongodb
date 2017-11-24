package com.mongo.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import com.mongo.db.DBUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;

public class UserDao extends DBUtils{
	
	// mongo db 删除数据库
	public void dropDatabase(String dbName) {
		try {
			m.dropDatabase(dbName);
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	// mongo db查询所有表名
	public void getAllCollections() {
		DB db = m.getDB("mydb");
		Set<String> colls = db.getCollectionNames();
		for (String s : colls) {
			System.out.println(s);
		}
	}

	// mongo db 查询索引
	public void getAllIndex() {
		List<DBObject> list = coll.getIndexInfo();
		for (DBObject o : list) {
			System.out.println(o);
		}
	}

	// mongo db 插入 insert
	public void insert(int i) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", "同盟" + i);
		doc.put("age", 20 + i);
		doc.put("sex", "男");
		doc.put("time", new Date());
		coll.insert(doc);
	}

	public void batchInsert() {
		List datas = new ArrayList();
		for (int i = 0; i < 100; i++) {
			BasicDBObject bo = new BasicDBObject();
			bo.put("name", "liu");
			bo.append("age", i);
			datas.add(bo);
		}
		coll.insert(datas);
	}

	// 查询单个
	public void findOne() {
		BasicDBObject obj = (BasicDBObject) coll.findOne();
		System.out.println(obj);
	}

	// mongo db 修改
	public void update() {
		BasicDBObject query = new BasicDBObject();
		query.put("name", "liu");
		DBObject stuFound = coll.findOne(query);
		stuFound.put("name", stuFound.get("name") + "update_1");
		coll.update(query, stuFound);
	}

	// 查询所有
	public void queryAll() {
		BasicDBObject obj = new BasicDBObject();
		obj.put("sex", "boy");
		DBCursor cursor = coll.find(obj);
		while (cursor.hasNext()) {
			String name = (String) cursor.next().get("name");
			System.out.println(name);
		}
		cursor.close();
	}

	// 条件删除
	public void delete(String name) {
		BasicDBObject query = new BasicDBObject();
		query.put("name", name);
		// 找到并且删除，并返回删除的对象
		DBObject removeObj = coll.findAndRemove(query);
		System.out.println(removeObj);
	}

	// 条件查询
	public void findByName(String name) {
		BasicDBObject obj = new BasicDBObject();
		obj.put("name", name);
		DBCursor cursor = coll.find(obj);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	// 条件查询2
	public void argsFind() {
		BasicDBObject condition = new BasicDBObject();
		// condition.put("age", new BasicDBObject("$gt", 20).append("$lte",
		// 30));
		condition.put("age", new BasicDBObject("$gt", 50));
		coll.find(condition);
		// 比较符
		// "$gt"： 大于
		// "$gte"：大于等于
		// "$lt"： 小于
		// "$lte"：小于等于
		// "$in"： 包含
		// 以下条件查询20<age<=30
	}

	// 分页查询
	public void pageQuery() {
		DBCursor cursor = coll.find().skip(0).limit(10);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
	
	public static void main(String[] args) {
		UserDao db = new UserDao();
//		db.insert(3);
		db.findByName("同盟3");
//		db.getAllCollections();
		
	}
}
