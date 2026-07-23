package financeapp;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static final String URI =
            "mongodb+srv://hanicelmir_db_user:w5CaZFZZcggyq8BW@cluster0.wo9cp2k.mongodb.net/?appName=Cluster0";

    private static final String DB_NAME = "financeTrackerDB";

    private static MongoClient client;

    public static MongoDatabase getDatabase() {

        if (client == null) {
            client = MongoClients.create(URI);
        }

        return client.getDatabase(DB_NAME);
    }
}