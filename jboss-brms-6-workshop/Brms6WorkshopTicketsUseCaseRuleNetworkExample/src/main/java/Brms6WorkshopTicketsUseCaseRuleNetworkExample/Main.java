package Brms6WorkshopTicketsUseCaseRuleNetworkExample;


import java.util.HashMap;
import java.util.Map;

import com.redhat.consulting.brms.workshop.RulesEngine;
import com.redhat.consulting.brms.workshop.model.Person;
import org.joda.time.LocalDate;



public class Main extends RulesEngine{

	public static void main(String[] args) {
		Main main = new Main();
		main.run(new Person("Jason", "Doyle", new LocalDate(2011, 3, 11)), true);
	}

	@Override
	protected Map<String, Object> getGlobals() {
		Map<String, Object> globals = new HashMap<>();
		globals.put("discountService", new DiscountService());
		return globals;
	}
	
}