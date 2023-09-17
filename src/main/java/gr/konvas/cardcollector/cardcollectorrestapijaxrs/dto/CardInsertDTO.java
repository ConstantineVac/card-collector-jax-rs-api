package gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CardInsertDTO extends BaseDTO{

    @NotBlank(message = "Please fill in the card name, as seen on your card.")
    @Size(min = 3, max = 50)
    private String cardName;

    @NotBlank(message = "Please fill in the expansion set name, as seen on your booster pack.")
    @Size(min = 3, max = 50)
    private String setName;

    @NotBlank(message = "Please enter the card number, as seen at the bottom left of the card.")
    @Size(min = 3, max = 5)
    @Pattern(regexp = ("^(\\d{3}|[A-Z]{2}\\d{2,3})$"),
             message = "Please enter a valid set number (eg. 100 or TG02)")
    private String setNumber;

    @NotBlank(message = "Enter the number (int) of card copies you have. From 1 to 999 copies.")
    @Size(min = 1, max = 999)
    private Integer copies;

    @NotBlank(message = "Add a valid price.")
    private Double price;

    @NotBlank(message = "Add rarity, for example Rare.")
    private String rarity;

    public CardInsertDTO() {}

    public CardInsertDTO(String cardName, String setName, String setNumber, Integer copies, Double price, String rarity) {
        this.cardName = cardName;
        this.setName = setName;
        this.setNumber = setNumber;
        this.copies = copies;
        this.price = price;
        this.rarity = rarity;
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
