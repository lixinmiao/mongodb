package com.mongo.dao.impl;

import java.util.List;  
import java.util.regex.Pattern;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;  
import org.springframework.data.document.mongodb.query.Criteria;  
import org.springframework.data.document.mongodb.query.Query;  
import org.springframework.data.document.mongodb.query.Update;  
import org.springframework.stereotype.Repository;

import com.mongo.entity.Person;

@Repository
public class BaseDao {  
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public BaseDao(){}
	
	public BaseDao(MongoTemplate mongoTemplate){
		this.mongoTemplate = mongoTemplate;
	}
	
    public List<Person> findAll() {  
//    	Criteria criteria = new Criteria("name").regex("db.caonima.find().skip(0).limit(20)");
        return mongoTemplate.find(new Query(), Person.class);  
    }  
  
    public void findAndModify(String id) {   
        mongoTemplate.updateFirst(new Query(Criteria.where("id").is(id)), new Update().inc("age", 3));  
    }  
  
    public List<Person> findByRegex(String regex) {  
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);  
        Criteria criteria = new Criteria("name").regex(pattern.toString());  
        return mongoTemplate.find(new Query(criteria), Person.class);  
    }  
  
    public Person findOne(String id) {  
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Person.class);  
    }  
  
      
    public void insert(Person person) {  
        mongoTemplate.insert(person);  
    }  
  
      
    public void removeAll() {  
        List<Person> list = this.findAll();  
        if(list != null){  
            for(Person person : list){  
                mongoTemplate.remove(person);  
            }  
        }  
    }  
  
      
    public void removeOne(String id){  
        Criteria criteria = Criteria.where("id").in(id);  
        if(criteria == null){  
             Query query = new Query(criteria);  
             if(query != null && mongoTemplate.findOne(query, Person.class) != null)  
                 mongoTemplate.remove(mongoTemplate.findOne(query, Person.class));  
        }  
    }  
}
