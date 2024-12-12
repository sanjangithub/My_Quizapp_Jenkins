package com.sanjan.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjan.quizapp.model.Agents;
import com.sanjan.quizapp.service.AgentService;

@RestController
@RequestMapping("/v1/sj/hitman")
public class AgentController {
	
	AgentService agentService;

	@Autowired
	public AgentController(AgentService agentService) {
		super();
		this.agentService = agentService;
	}
	
	public AgentController() {}

	@GetMapping("/agents")
	ResponseEntity<List<Agents>> getAllAgents(){
		return agentService.getAllAgents();
	}
	
	@GetMapping("/agents/details/{hid}")
	public ResponseEntity<Agents> getAgentWithHID(@PathVariable("hid") int hid)
	{
		return agentService.getAgentInfoWithHID(hid);
	}
	
	@PostMapping("/addAgent")
	public ResponseEntity<String> addNewAgent(@RequestBody Agents agents)
	{
		agentService.addNewAgent(agents);
		return new ResponseEntity<String>("Agent is now in DB",HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{hid}")
	public ResponseEntity<String> updateExistingAgentDetails(@PathVariable("hid") int hid, @RequestBody Agents agents)
	{
		return agentService.updateAgent(hid, agents);
	}
	
	@DeleteMapping("/delete/{hid}")
	public ResponseEntity<String> deleteAgentWithHID(@PathVariable("hid") int hid)
	{
		return agentService.deleteTheAgentWithHID(hid);
	}
}
