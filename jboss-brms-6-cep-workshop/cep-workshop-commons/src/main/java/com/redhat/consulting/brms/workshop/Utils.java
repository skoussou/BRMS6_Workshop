package com.redhat.consulting.brms.workshop;

import org.kie.api.runtime.KieSession;

public class Utils {

	
	public static KieSession decorateSession(final KieSession kieSession) {
		LoggingAgendaEventListener loggingListener = new LoggingAgendaEventListener();
		kieSession.addEventListener(loggingListener);
		return kieSession;
	}
	
}
