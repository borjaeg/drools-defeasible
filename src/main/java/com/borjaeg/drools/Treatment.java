package com.borjaeg.drools;

/**
 * Created by b3j90 on 26/9/16.
 */
public class Treatment {

    private String crop;
    private String plague;
    private String message = "";
    private String product = "";

    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public String getCrop() {
        return crop;
    }
    public void setCrop(String crop) {
        this.crop = crop;
    }
    public String getPlague() {
        return plague;
    }
    public void setPlague(String plague) {
        this.plague = plague;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}