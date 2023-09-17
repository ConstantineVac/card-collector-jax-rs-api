package gr.konvas.cardcollector.cardcollectorrestapijaxrs.dto;

public class CardReadOnlyDTO extends BaseDTO{
    private String cardName;
    private String setName;
    private String setNumber;
    private Integer copies;
    private Double price;
    private String rarity;

    public CardReadOnlyDTO() {
    }

    public CardReadOnlyDTO(Long id, String cardName, String setName, String setNumber, Integer copies, Double price, String rarity) {
        this.setId(id);
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
