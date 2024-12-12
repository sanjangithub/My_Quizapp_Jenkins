package com.sanjan.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sanjan.quizapp.model.Agents;
import com.sanjan.quizapp.repository.AgentsDao;

@Service
public class AgentService {

	private AgentsDao agentsDao;

	@Autowired
	public AgentService(AgentsDao agentsDao) {
		super();
		this.agentsDao = agentsDao;
	}
	
	public AgentService() {}

	public ResponseEntity<List<Agents>> getAllAgents() {
		return new ResponseEntity<>(agentsDao.findAll(),HttpStatus.OK);
	}
	
	public ResponseEntity<Agents> getAgentInfoWithHID(int hid)
	{
		try
		{
			return new ResponseEntity<>(agentsDao.findById(hid).get(), HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			e.getLocalizedMessage();
		}
		return new ResponseEntity<Agents>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addNewAgent(Agents agents) {
		try
		{
			agentsDao.save(agents);
		}
		catch (Exception e) {
			e.getLocalizedMessage();
		}
		return new ResponseEntity<String>("Please check the configuration and the data",HttpStatus.EXPECTATION_FAILED);
	}
	
	 public ResponseEntity<String> updateAgent(int hid, Agents updatedAgent) {
		 try
		 {
	        Agents existingAgent = agentsDao.findById(hid).get();
	        
	        existingAgent.setHid(updatedAgent.getHid());
	        existingAgent.setKills(updatedAgent.getKills());
	        existingAgent.setProficiency(updatedAgent.getProficiency());
	        existingAgent.setAgentContact(updatedAgent.getAgentContact());
	        existingAgent.setWeaponAssigned(updatedAgent.getWeaponAssigned());
	        agentsDao.save(existingAgent);
	        return new ResponseEntity<String>("Agent details are updated", HttpStatus.OK);
		 }catch(Exception e)
		 {
			 e.getLocalizedMessage();
		 }
		 return new ResponseEntity<String>("Agent details are not updated",HttpStatus.NOT_ACCEPTABLE);
	    }
	 
	 public ResponseEntity<String> deleteTheAgentWithHID(int hid)
	 {
		 try {
			 agentsDao.deleteById(hid);
			 return new ResponseEntity<String>("Agent is removed from DB", HttpStatus.OK);
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		 return new ResponseEntity<String>("Agent is not removed from DB",HttpStatus.NOT_ACCEPTABLE);
	 }
	
}
