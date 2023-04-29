package com.szincho.kimhyungjunproject.Order.DTO.Exception.Enum;

public enum OrderStatus {
    ARRIVED("이미 처리된 주문입니다. 주문번호를 다시 확인해주세요."),
    DEPARTED("이미 출발한 주문입니다. 주문번호를 다시 확인해주세요."),
    CANCELED("이미 취소된 주문입니다. 주문번호를 다시 확인해주세요."),
    NOT_ALLOWED_TO_BE_ARRIVED("아직 출발하지 않은 주문입니다. 주문번호를 다시 확인해주세요.");

    private final String message;

    OrderStatus(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
