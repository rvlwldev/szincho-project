package com.szincho.kimhyungjunproject.Order.DTO.Exception;

public class OrderNotFoundException extends IllegalArgumentException {

    public OrderNotFoundException() {
        super("주문 정보를 찾을 수 없습니다.");
    }

}
