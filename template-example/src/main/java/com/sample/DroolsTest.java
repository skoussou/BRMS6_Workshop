package com.sample;

import org.kie.api.KieServices;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import model.Person;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("TemplatesKS");

          for (KiePackage pkg: kSession.getKieBase().getKiePackages()) {
              
              for (Rule rule: pkg.getRules()) {
                  
                  System.out.println("Found rule:"+rule.getName());
              }
          }
          
          System.out.println("Executing rules generated from a template");
          
          Person person = new Person();
          person.setAge(42);
          person.setType("eidam");
          
          kSession.insert(person);
          kSession.fireAllRules();
          
          
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

}
