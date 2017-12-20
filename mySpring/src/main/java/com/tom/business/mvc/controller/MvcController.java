package com.tom.business.mvc.controller;

import com.alibaba.fastjson.JSON;
import com.tom.business.mvc.model.User;
import com.tom.business.mvc.service.MvcService;
import com.tom.common.util.json.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lxl
 */
@Controller
@RequestMapping(value = "mvc")
public class MvcController {

    @Autowired
    private MvcService mvcService;

    @GetMapping(value = "get/{id}")
    public void getUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        User user = mvcService.getUser(id);
        System.out.println(user);
        try {
            response.getWriter().write(GsonUtil.bean2json(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "update")
    public void updateUser(HttpServletRequest request, HttpServletResponse response, User user) {
        Boolean b = mvcService.updateUser(user);
        try {
            response.getWriter().write(GsonUtil.bean2json(b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
