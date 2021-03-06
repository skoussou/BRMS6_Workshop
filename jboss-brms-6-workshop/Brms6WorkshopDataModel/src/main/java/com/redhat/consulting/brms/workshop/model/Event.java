package com.redhat.consulting.brms.workshop.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents an Event, i.e. a concert, etc.
 *  
 * @author ddoyle
 */
public class Event {
	
	private final String name;
	
	public static enum EVENT_RATING {
		A, B, C, D
	}
	
	private final EVENT_RATING rating;
	
	
	public Event(final String name, final EVENT_RATING rating) {
		this.name = name;
		this.rating = rating;
	}


	public String getName() {
		return name;
	}


	public EVENT_RATING getRating() {
		return rating;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", getName()).append("rating", getRating()).build();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Event event = (Event) obj;
		//@formatter:off
		return new EqualsBuilder().appendSuper(super.equals(event))
				.append(getName(), event.getName())
				.append(getRating(), event.getRating()).isEquals();
				
		//@formatter:on
	}
	
	@Override
	public int hashCode() {
		//@formatter:off
		return new HashCodeBuilder(17, 67)
				.append(getName())
				.append(getRating()).toHashCode();
		//@formatter:on
	}
	
	

}
