package com.springapp.poseidon.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
public class GetUserInfoServiceImpl implements GetUserInfoService{

    @Override
    public String getUserInfo(Principal user) {
        StringBuffer userInfo = new StringBuffer();

        if (user instanceof UsernamePasswordAuthenticationToken) {
            userInfo.append(getUsernamePasswordLoginInfo(user));
        }else if (user instanceof OAuth2AuthenticationToken) {
            userInfo.append(getOAuthLoginInfo(user));
        }
        return userInfo.toString();
    }


    private StringBuffer getOAuthLoginInfo(Principal user) {
        StringBuffer protectedInfo = new StringBuffer();
        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);

        if (authToken.isAuthenticated()) {
            Map<String, Object> userDetails = ((DefaultOAuth2User) authToken.getPrincipal()).getAttributes();
            protectedInfo.append(userDetails.get("login"));
        }else {
            protectedInfo.append("NA");
        }
        return protectedInfo;
    }


    private StringBuffer getUsernamePasswordLoginInfo(Principal user) {
        StringBuffer usernameInfo = new StringBuffer();
        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);

        if (token.isAuthenticated()) {
            User u = (User) token.getPrincipal();
            usernameInfo.append(u.getUsername());
        }else {
            usernameInfo.append("NA");
        }
        return usernameInfo;
    }
}
