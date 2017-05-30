package com.redhat.consulting.brms.workshop;


import org.joda.time.LocalDate;

import com.redhat.consulting.brms.workshop.RulesEngine;
import com.redhat.consulting.brms.workshop.model.Person;



public class Main extends RulesEngine{

	public static void main(String[] args) {
		Main main = new Main();
		main.run(new Person("Jason", "Doyle", new LocalDate(2011, 3, 11)), true);
	}
	
}
