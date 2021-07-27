package com.amber.mycommunity.controller;

import com.amber.mycommunity.dto.QuestionDTO;
import com.amber.mycommunity.mapper.QuestionMapper;
import com.amber.mycommunity.mapper.UserMapper;
import com.amber.mycommunity.model.Question;
import com.amber.mycommunity.model.User;
import com.amber.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author amber
 * @date 2021/7/22 21:14
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public  String index(HttpServletRequest request, HttpServletResponse response, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")){
                    String token =cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        List<QuestionDTO> questionList= questionService.list();
        model.addAttribute("questions",questionList);

        return "index";
    }
}
