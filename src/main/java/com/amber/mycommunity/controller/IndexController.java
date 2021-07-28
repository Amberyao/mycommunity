package com.amber.mycommunity.controller;

import com.amber.mycommunity.dto.PaginationDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    public  String index(HttpServletRequest request, HttpServletResponse response, Model model,
                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                         @RequestParam(name = "size", defaultValue = "5") Integer size,
                         @RequestParam(name = "search", required = false) String search,
                         @RequestParam(name = "tag", required = false) String tag,
                         @RequestParam(name = "sort", required = false) String sort){
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
        PaginationDTO paginationDTO= questionService.list(page,size);
        model.addAttribute("pagination",paginationDTO);

        return "index";
    }
}
