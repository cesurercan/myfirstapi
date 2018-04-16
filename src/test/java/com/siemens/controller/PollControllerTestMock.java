package com.siemens.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.http.HttpStatus;
import com.siemens.domain.Poll;
import com.siemens.repository.PollRepository;

public class PollControllerTestMock {
	

	//Used @Mock annotation to mock PollController's only dependency : Poll Repository
	@Mock
	private PollRepository pollRepository;
	
	//To properly initialize the annotated pollRepository property, I invoked the initMocks() method in the mockito annotations.
	@Before
	public void setUp() throws Exception{
			MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllPolls() {
		
		PollController pollController = new PollController();
		
		//injected the mock PollRepository using ReflectionTestUtils class.
		ReflectionTestUtils.setField(pollController, "pollRepository", pollRepository);
		
		//Used Mockito's when() and thenReturn() methods to set the PollRepository mock's behaviour.
		when(pollRepository.findAll()).thenReturn(new ArrayList<Poll>());
		
		//invoked the getAllPolls() method and verify findAll() method's invocation and assert controller's return value
		ResponseEntity<Iterable<Poll>> allPollsEntity = pollController.getAllpolls();
		
		verify(pollRepository, times(2)).findAll();
		assertEquals(HttpStatus.OK, allPollsEntity.getStatusCode());
		assertEquals(0, Lists.newArrayList(allPollsEntity.getBody()).size());
	
		//In this strategy, I treated the PollController as a POJO, and hence don’t test the controller’s request mappings, 
		//validations, data bindings, and any associated exception handlers.
		
		
	}

}
