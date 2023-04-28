package com.szincho.kimhyungjunproject.Order;

import com.szincho.kimhyungjunproject.Order.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
