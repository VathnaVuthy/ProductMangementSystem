package Java;

import java.util.Date;

/**
 * Created by User on 5/19/2017.
 */
public class Product {
    private int id;
    private String name;
    private double unitPrice;
    private int stockQuantity;
    private Date importedDate;

    public Product(int id, String name, double unitPrice, int stockQuantity, Date importedDate) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.importedDate = importedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Date getImportedDate() {
        return importedDate;
    }

    public void setImportedDate(Date importedDate) {
        this.importedDate = importedDate;
    }
}
