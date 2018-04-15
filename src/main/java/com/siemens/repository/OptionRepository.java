package com.siemens.repository;

import org.springframework.data.repository.CrudRepository;

import com.siemens.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {

}
