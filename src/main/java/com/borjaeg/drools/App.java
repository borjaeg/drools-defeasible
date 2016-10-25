package com.borjaeg.drools;

import com.borjaeg.ontology.Treatment;

public class App {
	
	public static void main(String[] args){
	
		Treatment treatment = new Treatment();
		treatment.setCrop("appletree");
		treatment.setPlague("spider");
		treatment.setProduct("sulfur");
		Treatment treatment_2 = new Treatment();
		treatment_2.setCrop("appletree");
		treatment_2.setPlague("spider");
		treatment_2.setProduct("decen");
	
		RuleEngine.insert(new Treatment[] { treatment, treatment_2});
	
	}

}
