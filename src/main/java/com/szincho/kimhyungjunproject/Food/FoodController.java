package com.szincho.kimhyungjunproject.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FoodController {

    @Autowired
    FoodService service;

}
