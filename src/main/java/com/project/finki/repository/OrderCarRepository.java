package com.project.finki.repository;

import com.project.finki.model.OrderCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCarRepository extends JpaRepository<OrderCar, Long> {

    void deleteByShoppingCartId(Long shoppingCartId);
}
