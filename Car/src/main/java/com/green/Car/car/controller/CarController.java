package com.green.Car.car.controller;

import com.green.Car.car.service.CarService;
import com.green.Car.car.vo.CarInfoVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CarController {
    @Resource(name="carService")
    private CarService carService;

    // 홈화면
    @GetMapping("/list")
    public String carList(){
        return "content/car/car_list";
    }

    // 차량관리
    @GetMapping("/carManagementForm")
    public String carManagementForm(Model model){
        model.addAttribute("carList", carService.selectCarInfo());

        return "content/car/car_management";
    }
    @PostMapping("/carManagement")
    public String carManagement(CarInfoVO carInfoVO){
        carService.insertCarInfo(carInfoVO);

        return "redirect:/carManagementForm";
    }


    //판매정보 관리
    @GetMapping("/saleInfo")
    public String saleInfo(){
        return "content/car/car_sale_info";
    }
    //판매목록 페이지
    @GetMapping("/carSaleList")
    public  String saleTable(){
        return "content/car/car_sale_list";
    }
}



