package financeapp;

import org.bson.Document;

public class Transaction {

    private String id;
    private String type;
    private String category;
    private double amount;
    private String description;

    public Transaction(String id, String type, String category,
                       double amount, String description) {

        this.id = id;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public Document toDocument() {

        return new Document("Vrsta", type)
                .append("Kategorija", category)
                .append("Iznos", amount)
                .append("Opis", description);
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}