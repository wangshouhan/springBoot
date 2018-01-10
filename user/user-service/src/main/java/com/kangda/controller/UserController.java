package com.kangda.controller;

import com.kangda.api.IShopService;
import com.kangda.api.IUserService;
import com.kangda.base.annotation.NoNeedLogin;
import com.kangda.base.redis.RedisConfig;
import com.kangda.entity.Shop;
import com.kangda.entity.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by shouhan on 2017/8/22.
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

    /**
     * 测试dubbo的远程调用Shop模块的接口
     *
     * @return
     */
    @RequestMapping("test")
    public Shop test() {
        return shopService.findById(1);
    }

    /**
     * 登录(security)
     */
    @NoNeedLogin
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        //System.out.print("123456的加密密码："+passwordEncoder.encode("123456"));
        Object savedRequestObject = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        if (savedRequestObject != null) {
            request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
        }
        return "/login";
    }

    /**
     * 跳转主页(security)
     */
    @NoNeedLogin
    @RequestMapping("/home")
    public String home(Model model) {
        User auth = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //redis测试存入登录时的缓存
        if (redisConfig.getMap("user") != null) {
            redisConfig.remove("user");
        }
        redisConfig.setMap("user", auth);
        User user = (User) redisConfig.getMap("user");
        model.addAttribute("auth", user);
        List<User> userList = userService.findUserList();
        model.addAttribute("userList", userList);
        return "home";


    }

    /**
     * 退出页面(security)
     */
    @NoNeedLogin
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "login";
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
     * WebSocket接口
     */
    //当浏览器向服务端发送请求时，通过@MessageMapping映射的地址，类似于@RequestMapping
    @MessageMapping(value = "/message/test")
    //当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
    @SendTo(value = "/topic/response")
    public String say(MessageAcceptor acceptor) {
        return acceptor.getMsg();
    }
}
