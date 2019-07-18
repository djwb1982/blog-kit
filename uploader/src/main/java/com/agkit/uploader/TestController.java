package com.agkit.uploader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @program: com.agkit.uploader.TestController
 * @description:
 * @author: king djwb1982@163.com
 * @create: 2019-05-03 21:41
 **/
@RestController
public class TestController {
    @RequestMapping("/aa")
    @ResponseBody
    public Map<String,String> tdd(Model model){
        HashMap<String,String> map=new HashMap<>();
        map.put("aaa","aaa");
        return map;
    }
}
