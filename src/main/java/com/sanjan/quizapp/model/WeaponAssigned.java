package com.sanjan.quizapp.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Embeddable;

@Embeddable
@Component
public class WeaponAssigned {
	
	public WeaponAssigned() {}

	private String weapon;
	private String knife;
	
	public WeaponAssigned(String weapon, String knife) {
		super();
		this.weapon = weapon;
		this.knife = knife;
	}

	@Override
	public String toString() {
		return "WeaponAssigned [weapon=" + weapon + ", knife=" + knife + "]";
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getKnife() {
		return knife;
	}

	public void setKnife(String knife) {
		this.knife = knife;
	}
}
