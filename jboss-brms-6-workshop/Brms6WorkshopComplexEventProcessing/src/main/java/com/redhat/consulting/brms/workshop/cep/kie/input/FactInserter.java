package com.redhat.consulting.brms.workshop.cep.kie.input;

import javax.enterprise.context.ApplicationScoped;

import org.kie.api.runtime.rule.FactHandle;

import com.redhat.consulting.brms.workshop.cep.model.Fact;

/**
 * Responsible for inserting {@link Fact Facts} into the JBoss BRMS CEP Session.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
@ApplicationScoped
public interface FactInserter {
	
	public static final String DEFAULT_STREAM = "RHSummitStream";
	
	public abstract FactHandle insert(Fact fact);
	
	public abstract FactHandle insert(String stream, Fact fact);
	

}
