package hu.bme.aut.retelab2.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ad {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private int price;

    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    private String secretKey;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) { this.title = title; }

    public String getTitle() { return this.title; }

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return this.description; }

    public void setPrice(int price) { this.price = price; }

    public int getPrice() { return this.price; }

    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public LocalDateTime getCreatedDate() { return this.createdDate; }

    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    public String getSecretKey() { return this.secretKey; }

    public List<String> getTags() { return this.tags; }

    public void setTags(List<String> tags) { this.tags = tags; }
}
