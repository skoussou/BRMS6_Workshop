package com.redhat.consulting.brms.workshop.cep.command;

import java.io.Serializable;

public interface Command extends Serializable {
	
	public abstract String getId();
	
	public abstract Object execute();

}
