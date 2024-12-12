package com.sanjan.quizapp.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Embeddable;

@Embeddable
@Component
public class AgentContact {

	public AgentContact() {}
	
	private String Hitmail;

	public AgentContact(String hitmail) {
		super();
		Hitmail = hitmail;
	}

	@Override
	public String toString() {
		return "AgentContact [Hitmail=" + Hitmail + "]";
	}
//
	public String getHitmail() {
		return Hitmail;
	}

	public void setHitmail(String hitmail) {
		Hitmail = hitmail;
	}
}
