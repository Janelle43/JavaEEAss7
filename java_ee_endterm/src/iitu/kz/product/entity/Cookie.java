package iitu.kz.product.entity;

import java.util.Date;

public class Cookie {
    private Long id;
    private String name;
    private User author;
    private String recipe;
    private int rating;
    private int price;
    private Date createdAt;

    public Cookie(Long id, String name, User author, String recipe, int rating, int price, Date createdAt) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.recipe = recipe;
        this.rating = rating;
        this.price = price;
        this.createdAt = createdAt;
    }

    public Cookie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
