package com.kangda.controller;

import com.kangda.api.IShopService;
import com.kangda.api.IUserService;
import com.kangda.base.annotation.AspectIntercept;
import com.kangda.base.annotation.NoNeedLogin;
import com.kangda.base.redis.RedisConfig;
import com.kangda.base.socket.MessageAcceptor;
import com.kangda.entity.Page;
import com.kangda.entity.User;
import com.kangda.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by shouhan on 2017/8/22.
 * <p>
 * 用户
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IShopService shopService;
    @Autowired
    private RedisConfig redisConfig;
    @Autowired
    private IUserService userService;
    @Autowired
    private SendService sendService;

    /**
     * 登录(security)
     */
    @NoNeedLogin
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        //System.out.print("123456的加密密码："+passwordEncoder.encode("123456"));
        Object savedRequestObject = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequestObject != null) {
            request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
        }
        return new ModelAndView("login");
    }

    /**
     * 跳转主页(security)
     */
    @NoNeedLogin
    @RequestMapping("/home")
    public ModelAndView home(Model model) {
        User auth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //redis测试存入登录时的缓存
        redisConfig.setMap("userId", auth.getId());
        model.addAttribute("auth", auth);
        List<User> userList = userService.findUserList();
        model.addAttribute("userList", userList);
        return new ModelAndView("home");
    }

    /**
     * 退出页面(security)
     */
    @NoNeedLogin
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("login");
    }

    /**
     * WebSocket接口
     */
    //当浏览器向服务端发送请求时，通过@MessageMapping映射的地址，类似于@RequestMapping
    @MessageMapping(value = "/message/test")
    //当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
    @SendTo(value = "/topic/response")
    public String sendMessage(MessageAcceptor acceptor) {
        //将聊天信息打入mq中方便后面的操作
        return sendService.sendMessage(acceptor);
    }

    /**
     * 修改聊天字体颜色
     */
    @NoNeedLogin
    @RequestMapping("/update/color")
    @ResponseBody
    public String updateColor(String color) {
        userService.updateColor(color);
        return color;
    }

    /**
     * 测试dubbo的远程调用Shop模块的接口(aop切面测试接口)
     */
    @RequestMapping("test")
    @NoNeedLogin
    @AspectIntercept
    @ResponseBody
    public Map<String, Object> test() {
        return shopService.findByUserId();
    }

    /**
     * 分页查询user表
     */
    @RequestMapping("page")
    @NoNeedLogin
    @ResponseBody
    public Page<User> findPage(Page<User> page) {
        return userService.findPage(page);
    }
}
