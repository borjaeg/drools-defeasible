package com.borjaeg.drools;
/**
 * Created by b3j90 on 26/9/16.
 */

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RuleEngine {

        public static void main(String[] args) {

            try {
                // load up the knowledge base
                KieServices ks = KieServices.Factory.get();
                KieContainer kContainer = ks.getKieClasspathContainer();
                KieSession kSession = kContainer.newKieSession("ksession-rules");

                Treatment treatment = new Treatment();
                treatment.setCrop("appletree");
                //treatment.setPlague("spider");
                treatment.setProduct("sulfur");
                kSession.insert(treatment);
                kSession.fireAllRules();

                System.out.println(treatment.getMessage());

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
}
