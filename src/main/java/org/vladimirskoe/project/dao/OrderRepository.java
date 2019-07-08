package org.vladimirskoe.project.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vladimirskoe.project.entity.Order;
import org.vladimirskoe.project.entity.User;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByUser(User user);

}
