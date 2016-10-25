package com.borjaeg.ontology;

/**
 * Created by b3j90 on 26/9/16.
 */
public class Treatment {

    private String crop;
    public String plague;
    private String message = "";
    private String product = "";
    private boolean isForbidden = false;
    

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
	public boolean isForbidden() {
		return isForbidden;
	}
	public void setForbidden(boolean isForbidden) {
		this.isForbidden = isForbidden;
	}
}