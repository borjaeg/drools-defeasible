package com.borjaeg.drools;
/**
 * Created by b3j90 on 26/9/16.
 */

import com.borjaeg.ontology.Treatment;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

public class RuleEngine {

	public static void insert(Treatment[] facts) {

		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");
			// StatefulKnowledgeSession kSession = getSession(
			// "rules/TreatmentKB.drl" );
			for (Treatment fact: facts)
				kSession.insert(fact);
			
			kSession.fireAllRules();
			query(kSession);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void query(KieSession kSession) {
		QueryResults results = kSession.getQueryResults("getUsersForCard", new Object[] { "string" });
		for (QueryResultsRow row : results) {
			System.out.println(((Treatment) row.get("$listOfUserCards")).getProduct());
		}
	}

	private static void printObjects(KieSession kSession) {
		for (Object o : kSession.getObjects()) {
			Treatment t = (Treatment) o; 
			System.out.println(t.getMessage() + ": "+ t.getProduct());
		}

	}
}
