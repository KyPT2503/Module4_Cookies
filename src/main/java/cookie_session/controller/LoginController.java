package cookie_session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
    @ModelAttribute("hitCounter")
    public String getHitCounter(@CookieValue(value = "hitCounter", defaultValue = "-1") String hitCounter, HttpServletResponse response, HttpServletRequest request) {
        if (hitCounter.equals("-1")) {
            System.out.println("...");
            Cookie cookie = new Cookie("hitCounter", "1");
            cookie.setMaxAge(1000000);
            response.addCookie(cookie);
            return "1";
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("hitCounter")) {
                Cookie cookie1 = new Cookie("hitCounter", String.valueOf(Integer.parseInt(cookie.getValue()) + 1));
                response.addCookie(cookie1);
                return cookie1.getValue();
            }
        }
        return "1";
    }

    @GetMapping("")
    public ModelAndView showLoginPage(@CookieValue(value = "username", defaultValue = "username") String username, @CookieValue(value = "password", defaultValue = "password") String password) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("username", username);
        modelAndView.addObject("password", password);
        return modelAndView;
    }

    @GetMapping("/create-cookie")
    public ModelAndView createCookie(HttpServletResponse response) {
        response.addCookie(new Cookie("username", "thekypccc@gmail.com"));
        response.addCookie(new Cookie("password", "anh_thu_an_cut"));
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/clear-cookie")
    public ModelAndView clearCookie(HttpServletRequest request, HttpServletResponse response) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("username") || cookie.getName().equals("password")) {
                Cookie un = new Cookie("username", "");
                un.setMaxAge(0);
                Cookie pw = new Cookie("password", "");
                pw.setMaxAge(0);
                response.addCookie(un);
                response.addCookie(pw);
            }
        }
        return new ModelAndView("redirect:/login");
    }
}
