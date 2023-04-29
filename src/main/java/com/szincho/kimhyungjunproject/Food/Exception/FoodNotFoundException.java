package com.szincho.kimhyungjunproject.Food.Exception;

public class FoodNotFoundException extends Exception {

    public FoodNotFoundException() {
        super("음식 정보를 찾을 수 없습니다.");
    }


    public FoodNotFoundException(int id) {
        super(String.format("음식등록번호 %d번의 정보를 찾을 수 없습니다.", id));
    }
}
