package com.szincho.kimhyungjunproject.Order;

import com.szincho.kimhyungjunproject.Order.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("UPDATE Order " +
            "  SET isCanceled = true " +
            "WHERE id = :id")
    void cancelOrder(@Param("id") Long id);
}
