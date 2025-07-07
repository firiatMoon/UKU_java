package uku.java.StreamAPI;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public BigDecimal getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return String.format("Product [id = %s, name = %s, category = %s, price = %s]", id, name, category, price);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(category, product.category) &&
                price.compareTo(product.price) == 0;
    }

    @Override
    public int hashCode(){
        return 31 * Objects.hash(id, name, category, price);
    }

    public static class Builder {
        private final Product product;

        public Builder() {
            product = new Product();
        }

        public Builder id(Long id) {
            product.id = id;
            return this;
        }

        public Builder name(String name) {
            product.name = name;
            return this;
        }

        public Builder category(String category) {
            product.category = category;
            return this;
        }

        public Builder price(BigDecimal price) {
            product.price = price;
            return this;
        }

        public Product build() {
            return product;
        }
    }
}
