package financeapp;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class TransactionManager {

    private final MongoCollection<Document> collection;

    public TransactionManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("transactions");
    }

    // INSERT
    public void addTransaction(Transaction t) {
        collection.insertOne(t.toDocument());
    }

    // UPDATE
    public void updateTransaction(String id, Transaction t) {

        collection.updateOne(
                Filters.eq("_id", new ObjectId(id)),
                Updates.combine(
                        Updates.set("Vrsta", t.getType()),
                        Updates.set("Kategorija", t.getCategory()),
                        Updates.set("Iznos", t.getAmount()),
                        Updates.set("Opis", t.getDescription())
                )
        );
    }

    // DELETE
    public void deleteTransaction(String id) {

        collection.deleteOne(
                Filters.eq("_id", new ObjectId(id))
        );
    }

    // READ ALL
    public ArrayList<Transaction> getAllTransactions() {

        ArrayList<Transaction> list = new ArrayList<>();

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {

            Document d = cursor.next();

            String id = d.getObjectId("_id").toHexString();

            list.add(new Transaction(
                    id,
                    d.getString("Vrsta"),
                    d.getString("Kategorija"),
                    ((Number) d.get("Iznos")).doubleValue(),
                    d.getString("Opis")
            ));
        }

        return list;
    }

    public double getTotalIncome() {
        double total = 0;

        for (Transaction t : getAllTransactions()) {
            if ("Prihod".equals(t.getType())) {
                total += t.getAmount();
            }
        }

        return total;
    }

    public double getTotalExpense() {
        double total = 0;

        for (Transaction t : getAllTransactions()) {
            if ("Rashod".equals(t.getType())) {
                total += t.getAmount();
            }
        }

        return total;
    }
}