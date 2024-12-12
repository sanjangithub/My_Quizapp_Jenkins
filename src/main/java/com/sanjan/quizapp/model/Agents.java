package com.sanjan.quizapp.model;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "agents")
public class Agents {

	@Id
	@Column(name = "HID")
	private Integer hid;
	
	@Column(name = "Kills")
	private Integer kills;
	
	@Column(name = "Proficiency")
	private Integer proficiency;
	
	@Embedded
	private AgentContact agentContact;
	
	@Embedded
	private WeaponAssigned weaponAssigned;

	@Autowired
	public Agents(Integer hid, Integer kills, Integer proficiency, AgentContact agentContact,
			WeaponAssigned weaponAssigned) {
		super();
		this.hid = hid;
		this.kills = kills;
		this.proficiency = proficiency;
		this.agentContact = agentContact;
		this.weaponAssigned = weaponAssigned;
	}

	@Override
	public String toString() {
		return "Agents [hid=" + hid + ", kills=" + kills + ", proficiency=" + proficiency + ", agentContact="
				+ agentContact + ", weaponAssigned=" + weaponAssigned + "]";
	}
	
	public Agents() {}

	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	public Integer getKills() {
		return kills;
	}

	public void setKills(Integer kills) {
		this.kills = kills;
	}

	public Integer getProficiency() {
		return proficiency;
	}

	public void setProficiency(Integer proficiency) {
		this.proficiency = proficiency;
	}

	public AgentContact getAgentContact() {
		return agentContact;
	}

	public void setAgentContact(AgentContact agentContact) {
		this.agentContact = agentContact;
	}

	public WeaponAssigned getWeaponAssigned() {
		return weaponAssigned;
	}

	public void setWeaponAssigned(WeaponAssigned weaponAssigned) {
		this.weaponAssigned = weaponAssigned;
	}
}
