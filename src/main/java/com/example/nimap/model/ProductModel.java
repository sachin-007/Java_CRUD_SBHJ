package com.example.nimap.model;
import jakarta.persistence.*;


@Entity
@Table(name ="Product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String Description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryid" , referencedColumnName = "categoryid")
    private CategoryModel category;
    private double ourPrice;
    private double starRating;

    private int stock;





    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ProductModel() {
        super();
    }

    public ProductModel(int id, String title, String description, CategoryModel category,double ourPrice, double starRating,int stock) {
        super();
        this.id = id;
        this.title = title;
        Description = description;
        this.category = category;
        this.ourPrice = ourPrice;
        this.starRating = starRating;
        this.stock = stock;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(Double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(Double starRating) {
        this.starRating = starRating;
    }


}
