package com.borjaeg.drools;
/**
 * Created by b3j90 on 26/9/16.
 */

import org.drools.core.BeliefSystemType;
import org.drools.core.SessionConfiguration;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class RuleEngine {

        public static void main(String[] args) {

            try {
                // load up the knowledge base
                KieServices ks = KieServices.Factory.get();
                KieContainer kContainer = ks.getKieClasspathContainer();
                //KieSession kSession = kContainer.newKieSession("ksession-rules");
                
                StatefulKnowledgeSession kSession = getSession( "rules/TreatmentKB.drl" );
               

                Treatment treatment = new Treatment();
                treatment.setCrop("appletree");
                treatment.setPlague("spider");
                treatment.setProduct("sulfur");
                kSession.insert(treatment);
                kSession.fireAllRules();

                for (Object o : kSession.getObjects()){
                     System.out.println(((Treatment) o).getMessage()); 	
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        private static StatefulKnowledgeSession getSession( String ruleFile ) {
            KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

            try {
                System.setProperty("drools.negatable", "on");
                kBuilder.add(ResourceFactory.newClassPathResource(ruleFile),
                             ResourceType.DRL);
                if (kBuilder.hasErrors()) {
                    System.err.println(kBuilder.getErrors());
                }
            } finally {
                System.setProperty("drools.negatable", "off");
            }

            KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
            kBase.addKnowledgePackages( kBuilder.getKnowledgePackages() );


            KieSessionConfiguration ksConf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
            ((SessionConfiguration) ksConf).setBeliefSystemType( BeliefSystemType.DEFEASIBLE );

            StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession( ksConf, null );
            return kSession;
        }
}
