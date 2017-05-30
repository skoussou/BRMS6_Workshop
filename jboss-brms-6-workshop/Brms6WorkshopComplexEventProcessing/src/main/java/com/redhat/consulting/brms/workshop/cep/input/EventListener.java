package com.redhat.consulting.brms.workshop.cep.input;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.consulting.brms.workshop.cep.eventproducer.FactsLoader;
import com.redhat.consulting.brms.workshop.cep.kie.input.FactInserter;
import com.redhat.consulting.brms.workshop.cep.kie.input.PseudoClock;
import com.redhat.consulting.brms.workshop.cep.model.Event;

/**
 * Demo listener implementation which generates the events itself.
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
@ApplicationScoped
public class EventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

	@Inject @PseudoClock
	private FactInserter factInserter;

	public EventListener() {
	}
	
	@PostConstruct
	public void initialize() {
	}

	public void start() {
		
		String eventsFileUrl = this.getClass().getClassLoader().getResource("events.csv").getFile();
		File eventsFile = new File(eventsFileUrl);
		List<Event> events = FactsLoader.loadEvents(eventsFile);
		
		for (Event nextEvent: events) {
			factInserter.insert(nextEvent);
		}
	}
	
	public void stop() {
		//Stopping.
	}
}
