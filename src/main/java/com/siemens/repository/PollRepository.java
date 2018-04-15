package com.siemens.repository;

import org.springframework.data.repository.CrudRepository;

import com.siemens.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long>{

}
