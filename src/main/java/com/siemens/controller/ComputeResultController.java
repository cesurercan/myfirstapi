package com.siemens.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.siemens.domain.Vote;
import com.siemens.dto.VoteResult;
import com.siemens.repository.VoteRepository;

public class ComputeResultController {
	@Inject
	private VoteRepository voteRepository;

	@RequestMapping(value="/computeresult", method=RequestMethod.GET)
	public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
	VoteResult voteResult = new VoteResult();
	Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
	return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
	}

}
