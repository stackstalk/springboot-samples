package com.stackstalk.democache;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserDetails, Integer> {

}
