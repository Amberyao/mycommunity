package com.amber.mycommunity.controller;

import com.amber.mycommunity.dto.QuestionDTO;
import com.amber.mycommunity.exception.CustomizeErrorCode;
import com.amber.mycommunity.exception.CustomizeException;
import com.amber.mycommunity.mapper.QuestionMapper;
import com.amber.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author amber
 * @date 2021/7/28 14:33
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name ="id") String id, Model model){
        Long questionId = null;
        try {
            questionId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        QuestionDTO questionDTO=questionService.getById(questionId);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
