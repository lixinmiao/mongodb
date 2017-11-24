package com.mongo.web;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Query;

import com.mongo.dao.impl.BaseDao;
import com.mongo.db.DBUtils;
import com.mongo.entity.Person;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.sun.org.apache.commons.collections.CursorableLinkedList.Cursor;

public class TestMongo extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response) {
		
		BaseDao baseDao = null;
		try {
			baseDao = new BaseDao(new MongoTemplate(new Mongo("localhost",
					27017), "mydb", "caonima"));
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (MongoException e1) {
			e1.printStackTrace();
		}
		List<Person> list = baseDao.findAll();
//		List<Person> list = new ArrayList<Person>();
//		DBUtils dbUtils = new DBUtils();
//		DBCursor cur = dbUtils.getDBCollection("caonima").find(null, null, 0, 20, 1);
//		DBCursor cur = dbUtils.getDBCollection("caonima").find().sort((DBObject) new BasicDBObject().put("id", new BasicDBObject("$in", "id")));
//		DBCursor cur = dbUtils.getDBCollection("caonima").find().skip(20).limit(20);
//		while (cur.hasNext()) {
//			Person o = (Person) cur.next();
//			list.add(o);
//
//		}
		request.getSession().setAttribute("person", list);
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
