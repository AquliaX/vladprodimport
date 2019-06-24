package org.vladimirskoe.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vladimirskoe.project.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
