package com.amber.mycommunity.controller;

import com.amber.mycommunity.dto.AccessTokenDTO;

import com.amber.mycommunity.mapper.UserMapper;
import com.amber.mycommunity.model.User;
import com.amber.mycommunity.provider.GiteeProvider;
import com.amber.mycommunity.provider.Githubprovider;
import com.amber.mycommunity.provider.dto.GiteeUser;
import com.amber.mycommunity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by codedrinker on 2019/4/24.
 */

@Controller
@Slf4j
public class AuthorizeController {

    @Autowired
    private Githubprovider githubProvider;

    @Autowired
    private GiteeProvider giteeProvider;

    @Value("${gitee.client.id}")
    private String clientId;

    @Value("${gitee.client.secret}")
    private String clientSecret;

    @Value("${gitee.redirect.uri}")
    private String redirectUri;


    @Autowired
    private UserService userService;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = giteeProvider.getAccessToken(accessTokenDTO);
        GiteeUser giteeUser = giteeProvider.getUser(accessToken);
        if (giteeUser != null && giteeUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(giteeUser.getName());
            user.setAccountId(String.valueOf(giteeUser.getId()));
            user.setAvatarUrl(giteeUser.getAvatarUrl());
            userService.createOrUpdate(user);
            //??????????????????cookie ???session
            //???????????????????????????
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
            //request.getSession().setAttribute("user",giteeUser);
            return "redirect:/";
        }else {
            //???????????????????????????
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().invalidate();
        //request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }
}
