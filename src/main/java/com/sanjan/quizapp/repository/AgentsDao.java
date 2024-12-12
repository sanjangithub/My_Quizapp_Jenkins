package com.sanjan.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanjan.quizapp.model.Agents;

@Repository
public interface AgentsDao extends JpaRepository<Agents, Integer> {

}
