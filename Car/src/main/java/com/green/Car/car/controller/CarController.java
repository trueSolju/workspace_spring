package com.green.Car.car.controller;

import com.green.Car.car.service.CarService;
import com.green.Car.car.vo.CarInfoVO;
import com.green.Car.car.vo.SalesInfoVO;
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
    @GetMapping("/saleInfoForm")
    public String saleInfoForm(Model model){
        model.addAttribute("carList", carService.selectCarInfo());
        return "content/car/car_sale_info";
    }
    @PostMapping("/saleInfo")
    public String saleInfo(SalesInfoVO salesInfoVO){
        carService.insetMember(salesInfoVO);
        return "redirect:/carSaleList";
    }
    //판매목록 페이지
    @GetMapping("/carSaleList")
    public  String saleTable(Model model){
        model.addAttribute("memberList",carService.selectAllMember());
        return "content/car/car_sale_list";
    }
}



