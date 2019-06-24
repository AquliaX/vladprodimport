package org.vladimirskoe.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vladimirskoe.project.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
