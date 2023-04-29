package com.szincho.kimhyungjunproject.Order.DTO.Exception.Enum;

public enum OrderStatus {
    ARRIVED("이미 처리된 주문입니다."),
    DEPARTED("이미 출발한 주문입니다."),
    CANCELED("이미 취소된 주문입니다.");

    private final String message;

    OrderStatus(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
