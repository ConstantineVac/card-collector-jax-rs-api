package gr.konvas.cardcollector.cardcollectorrestapijaxrs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "CARDS", schema = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CARD_NAME", length = 50)
    private String cardName;
    @Column(name = "SET_NAME", length = 50)
    private String setName;
    @Column(name = "SET_NUMBER")
    private String setNumber;
    @Column(name = "COPIES")
    private Integer copies;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "RARITY")
    private String rarity;

    public Card () {}

    public Card(String cardName, String setName, String setNumber, Integer copies, Double price, String rarity) {
        this.cardName = cardName;
        this.setName = setName;
        this.setNumber = setNumber;
        this.copies = copies;
        this.price = price;
        this.rarity = rarity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
