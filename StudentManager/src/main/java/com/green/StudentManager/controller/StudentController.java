package com.green.StudentManager.controller;

import com.green.StudentManager.service.StuService;
import com.green.StudentManager.service.StuServiceImpl;
import com.green.StudentManager.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Resource(name = "stuService")
    private StuServiceImpl stuService;

    //학생 목록 페이지로 이동
    @GetMapping("/")
    public String stuList(Model model){
        //학생 목록 조회
        List<StuVO> list =stuService.selectStuList();
        //조회한 목록을 html로 전달
        model.addAttribute("stuList",list);
        return "stu_list";
    }
    @GetMapping("/regStu")
    public String regStu(){
        return "reg_student";
    }
//    post방식 get방식이 다르면 이름이 같아도 등록 가능
    @PostMapping("/regStu")
    public String reg(StuVO stuVO) {
        //학생등록
        stuService.insertStu(stuVO);
        return "redirect:/";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(name = "stuNo")int stuNo,Model model){
        //학생의 상세정보 조회
        StuVO stu = stuService.selectStu(stuNo);
        //조회한 데이터를 html로 전달
        model.addAttribute("stuInfo",stu);


        return "student_detail";
    }
    @GetMapping("/deta")
    public String delete(StuVO stuVO){
        stuService.deleteStu(stuVO.getStuNo());
        return "redirect:/";
    }
}
