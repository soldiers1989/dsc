package com.yixin.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 跳转
 * Package : com.yixin.controller
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月21日 下午5:02:42
 *
 */
@Controller
@RequestMapping("/")
public class RedirectController {
	
    /**
     * 通过后台服务拼接URL
     * @param url
     * @param model
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年8月21日 下午5:02:36
     */
    @RequestMapping(value = "/static")
	public String redirect(@RequestParam("url") String url,ModelMap model) {
		return "redirect:/vue/"+url;
	}
}
