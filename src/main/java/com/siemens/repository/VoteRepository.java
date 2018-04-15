package com.siemens.repository;

import org.springframework.data.repository.CrudRepository;

import com.siemens.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
