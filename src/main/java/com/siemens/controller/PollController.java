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
	
	/**
	 * A GET request on the /polls endpoint provides a collection of all of the polls available 
	 * in the QuickPolls application.The @RequestMapping annotation declares the URI and the allowed HTTP method. 
	 * The getAllPolls() method used ResponseEntity as its return type, indicating that the return value 
	 * is the complete HTTP response. ResponseEntity gives you full control over the HTTP response, 
	 * including the response body and response headers. The method implementation begins with reading 
	 * all of the polls using the PollRepository. We then create an instance of ResponseEntity and pass 
	 * in Poll data and the HttpStatus.OK status value. The Poll data becomes part of the response body 
	 * and OK (code 200) becomes the response status code.
	 */
	
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
	
	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
	Poll p = pollRepository.save(poll);
	return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
	pollRepository.deleteById(pollId);
	return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
