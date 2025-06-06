package smartproject.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Grocery {
    @Id
    int id;
    String name;
    Double price;
    public Grocery()
    {

    }
public Grocery(int id, String name, Double price) {
    this.id = id;
    this.name = name;
    this.price = price;
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
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
