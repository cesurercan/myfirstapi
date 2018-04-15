package com.siemens.controller;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.siemens.domain.Poll;
import com.siemens.repository.PollRepository;

@RestController
public class PollController {
	
	@Inject
	private PollRepository pollRepository;
	
	@RequestMapping(value="/polls", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Poll>> getAllpolls(){
		Iterable<Poll> allPolls = pollRepository.findAll();
		return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
	public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
		Optional<Poll> p = pollRepository.findById(pollId);
		return new ResponseEntity<> (p, HttpStatus.OK);}
	
	
	@RequestMapping(value="/polls", method=RequestMethod.POST)
	public ResponseEntity<?> createPoll(@RequestBody Poll poll){
		poll  = pollRepository.save(poll);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(poll.getId())
				.toUri();

		responseHeaders.setLocation(newPollUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
}
