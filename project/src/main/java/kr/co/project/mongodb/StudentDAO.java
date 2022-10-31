package kr.co.project.mongodb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	public Map<String, Object> insert(Map<String, Object> map) {
		return mongoTemplate.insert(map, "student");
	}
	
	public List<Map> list(Map map) {
		if (!"".equals(map.get("sword"))) { // 검색어가 있을 때
			Criteria cri = new Criteria();
			Query q = new Query(cri.orOperator(
					Criteria.where("id").is(map.get("sword")),
					Criteria.where("name").is(map.get("sword")),
					Criteria.where("id").regex(map.get("sword").toString()), // LIKE 검색
					Criteria.where("name").regex(map.get("sword").toString()) // LIKE 검색
					));
			return mongoTemplate.find(q, Map.class, "student");
		} else { // 검색어가 없을 때
			return mongoTemplate.findAll(Map.class, "student");
		}
	}
}

