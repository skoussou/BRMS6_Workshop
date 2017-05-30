package com.redhat.consulting.brms.cep.workshop;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.impl.PseudoClockScheduler;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.consulting.brms.cep.workshop.model.BagScannedEvent;
import com.redhat.consulting.brms.cep.workshop.model.Event;
import com.redhat.consulting.brms.cep.workshop.model.Location;
import com.redhat.consulting.workshop.cep.commons.events.FactsLoader;

public class Main {
	
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	private static final String EVENTS_FILE_NAME = "events.csv";
	
	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.getKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.addEventListener(new LoggingRuleRuntimeEventListener());
		List<Event> events;
		try(InputStream eventFileInputStream = Main.class.getClassLoader().getResourceAsStream(EVENTS_FILE_NAME)) {
			events = FactsLoader.loadEvents(eventFileInputStream);
		} catch (IOException ioe) {
			throw new RuntimeException("I/O problem loading event file. Not much we can do in this lab.", ioe);
			
		}
		events.stream().forEach(event -> { insertAndFire(kieSession, event);});
		
		kieSession.dispose();
	}
	
	private static void insertAndFire(KieSession kieSession, Event event) {
		PseudoClockScheduler clock = kieSession.getSessionClock();
		kieSession.insert(event);
		
		long deltaTime = event.getTimestamp().getTime() - clock.getCurrentTime();
		if (deltaTime > 0) {
			clock.advanceTime(deltaTime, TimeUnit.MILLISECONDS);
		}
		kieSession.fireAllRules();
	}
	
}
