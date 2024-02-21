package com.green.FetchStudent.controller;

import com.green.FetchStudent.service.StuServiceImpl;
import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.DetailVO;
import com.green.FetchStudent.vo.StuScoreVO;
import com.green.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/stu")
public class StuController {
    @Resource(name = "stuService")
    private StuServiceImpl stuService;
    // 테이블 두개 이상에서 동시에 데이터를 조회 -> join

    @GetMapping("/manage")
    //학급 목록 데이터 조회
    public String classCode(Model model, StuVO stuVO){
        model.addAttribute("classList", stuService.selectClass());
        model.addAttribute("stuList",stuService.selectStudent(stuVO));

        return "stu_manage";
    }
    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVO> fetchSelect(StuVO stuVO){
        List<StuVO> stuList =stuService.selectStudent(stuVO);
        return stuList;
    }
    @ResponseBody
    @PostMapping("/stuScore")
    public DetailVO studentScore(StuVO stuVO) {
        //클릭한 학생의 상세 정보 조회
        StuVO stuInfo = stuService.selectOneStu(stuVO);

        // 클릭한 학생의 점수 정보 조회
        StuScoreVO scoreInfo = stuService.selectScore(stuVO);
        //조회한 데이터를 가지고 자바스크립트로 이동
            DetailVO result = new DetailVO();
            result.setStuVO(stuInfo);
            result.setStuScoreVO(scoreInfo);

            return result;
    }
    @ResponseBody
    @PostMapping("/inScore")
    public void insertScore(StuScoreVO stuScoreVO){
        stuService.insertScore(stuScoreVO);
    }
}
