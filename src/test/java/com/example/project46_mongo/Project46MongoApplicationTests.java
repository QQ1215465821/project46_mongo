package com.example.project46_mongo;

import com.example.project46_mongo.mongo.Book;
import com.example.project46_mongo.mongo.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class Project46MongoApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
//        Book book = new Book();
//        book.setId(11);
//        book.setName("testMongoDB");
//        book.setType("testMongoDB");
//        book.setDescription("testMongoDB");
//        mongoTemplate.save(book);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("testMongoDB");
            user.setType("testMongoDB");
            user.setDescription("testMongoDB");
            user.setAge(10*i);
            User save = mongoTemplate.save(user);
            System.out.println("save = " + save);
        }

    }
    @Rollback(value = false)
    @Transactional(rollbackFor = Exception.class)
    @Test
    void contextLoads2() {
        User user = new User();
        user.setId(1);
        user.setName("testMongoDB22");
        user.setType("testMongoDB");
        user.setDescription("testMongoDB");
        User save = mongoTemplate.save(user);
        mongoTemplate.save(user, "user2");
//        int a = 1/0;
    }
    @Test
    void contextLoads3() {
        List<User> userList = mongoTemplate.findAll(User.class);
        System.out.println("userList = " + userList);

        System.out.println("user = " + mongoTemplate.findById(1, User.class));
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gte(20).lte(30));
        query.addCriteria(Criteria.where("age").gte(20).lte(30));
        System.out.println("userList = " + mongoTemplate.find(query, User.class));

    }

    void testGit(){
        /**
         * 777内容
         * 888内容
         */
    }

}
