package com.digitalojt.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalojt.api.entity.AdminInfo;
import com.digitalojt.api.service.AdminInfoService;

/**
 * 管理者認証APIコントローラー
 * 
 * @author your name
 *
 */

@RestController
@RequestMapping("/api/admin/auth")
public class AdminAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminInfoService adminInfoService;

    @PostMapping("/login")
    public String login(@RequestBody AdminInfo adminInfo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(adminInfo.getAdminId(), adminInfo.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Login successful";
    }

    @PostMapping("/register")
    public String register(@RequestBody AdminInfo adminInfo) {
        adminInfoService.save(adminInfo);
        return "Admin registered successfully";
    }
}