package com.borjaeg.utils;

import org.drools.core.BeliefSystemType;
import org.drools.core.SessionConfiguration;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

/**
 * Created by b3j90 on 25/10/16.
 */
public class Knowledge {

        public static StatefulKnowledgeSession getSession(String ruleFile ) {
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
            kBase.addKnowledgePackages(kBuilder.getKnowledgePackages());


            KieSessionConfiguration ksConf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
            ((SessionConfiguration) ksConf).setBeliefSystemType(BeliefSystemType.DEFEASIBLE);

            StatefulKnowledgeSession kSession = kBase.newStatefulKnowledgeSession(ksConf, null);
            return kSession;
        }

}
