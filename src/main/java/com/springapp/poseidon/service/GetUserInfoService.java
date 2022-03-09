package com.springapp.poseidon.service;

import java.security.Principal;

public interface GetUserInfoService {

    String getUserInfo(Principal user);
}
