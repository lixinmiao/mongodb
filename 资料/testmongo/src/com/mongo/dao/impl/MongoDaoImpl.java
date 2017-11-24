package com.mongo.dao.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Order;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mongo.dao.MongoDao;
import com.mongo.entity.Page;
import com.mongo.entity.Person;

public class MongoDaoImpl implements MongoDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Person> findAll(Page page) {
		Query query = new Query();
		//query.sort().on("score", Order.DESCENDING);
		query.skip(page.getPage()).limit(page.getPageSize());
		return mongoTemplate.find(query, Person.class);
	}

	public List<Person> findByRegex(String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Criteria criteria = new Criteria("name").regex(pattern.toString());
		return mongoTemplate.find(new Query(criteria), Person.class);
	}

}
