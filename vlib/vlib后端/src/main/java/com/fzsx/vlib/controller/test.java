package com.fzsx.vlib.controller;

import com.alibaba.fastjson.JSONObject;
import com.fzsx.vlib.common.Constant;
import com.fzsx.vlib.common.MD5Utils;
import com.fzsx.vlib.common.TokenUtil;
import com.fzsx.vlib.entity.Menu;
import com.fzsx.vlib.entity.Supplier;
import com.fzsx.vlib.entity.User;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * vlib后端 - test
 * TODO
 *
 * @author HeiYunxi 黑昀熙
 * @version 1.0
 */
@Controller
@RequestMapping(value = "api")
public class test extends HttpServlet {

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object login(@RequestBody Map<String,Object> map) {
        String question = map.get("question").toString() == null ? "" : map.get("question").toString();
        Object file = map.get("file").toString() == null ? "" : map.get("file").toString();
        System.out.println(question);
        System.out.println(file);
        return map;
    }
}

