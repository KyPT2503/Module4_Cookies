package cookie_session.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.servlet.http.HttpServletResponse;

@Aspect
public class CookieHandler {
    @Before("execution(public * cookie_session.controller.LoginController.showLoginPage(..)) && args(username,password)")
    public void cookieLog(String username, String password) {
        System.out.println("[Logger] username:" + username);
        System.out.println("[Logger] password:" + password);
    }
    @After("execution(public * cookie_session.controller.LoginController.clearCookie(..))")
    public void clearCookieLog(){
        System.out.println("[Logger] cookies clear all done.");
    }
    @Before("execution(public * cookie_session.controller.LoginController.getHitCounter(..)) && args(hitCounter,response)")
    public void countGetHitCounterMethodRun(String hitCounter, HttpServletResponse response){
        System.out.println("[Logger] gitCounter() method run.");
    }
}
