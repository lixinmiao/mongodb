package com.mongo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mongo.dao.MongoDao;
import com.mongo.dao.impl.BaseDao;
import com.mongo.entity.Page;
import com.mongo.entity.Person;
import com.mongodb.Mongo;

@Controller
@SuppressWarnings("unchecked")
public class TestController {

	@Autowired
	MongoDao mongodao;

	
	@ResponseBody
	@RequestMapping(value = "/getUsersForSpring1.json", method = RequestMethod.GET)
	public Object getUsers2() {
		Map map = new HashMap();
		List<Person> list = mongodao.findByRegex("99");
		map.put("users", list);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/getUsersForSpring2.json", method = RequestMethod.GET)
	public Object getUsers3(@ModelAttribute Page page) {
		Map map = new HashMap();
		List<Person> list = mongodao.findAll(page);
		map.put("users", list);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUsersForJDBC.json", method = RequestMethod.GET)
	public Object getUsers1() {
		Map map = new HashMap();
		BaseDao baseDao = null;
		try {
			baseDao = new BaseDao(new MongoTemplate(new Mongo("localhost",
					27017), "mydb", "caonima"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Person> list = baseDao.findAll();
		map.put("users", list);

		return map;
	}

}
