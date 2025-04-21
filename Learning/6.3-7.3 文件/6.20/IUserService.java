package com.cqupt.service;

import com.cqupt.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface IUserService {


	UserEntity getUserByAccountName(String accountName);


	UserEntity getUserByLoginInfo(Map<String, Object> map);


}
