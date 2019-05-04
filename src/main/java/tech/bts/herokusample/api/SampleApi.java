package tech.bts.herokusample.api;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class SampleApi {

    private MongoCollection<Document> words;

    @Autowired
    public SampleApi(@Value("${mongoUri}") String mongoUri) {

        final MongoClient mongoClient = MongoClients.create(mongoUri);
        final MongoDatabase database = mongoClient.getDatabase("bts");
        this.words = database.getCollection("words");
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from sample app";
    }

    @GetMapping("/insert/{word}")
    public String insertWord(@PathVariable String word) {
        Document document = new Document();
        document.append("word", word);
        document.append("date", new Date());
        words.insertOne(document);

        return "Word successfully inserted!";
    }

}