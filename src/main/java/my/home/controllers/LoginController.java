package my.home.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLoginPage(ModelMap model, HttpServletRequest request, Authentication authentication) {
        //проверить, если пользователь уже вошел в систему, то перенапраить на страниу Welcome
        if (authentication != null) {
            System.out.println("пользователь подключен");
            return "redirect:/welcome";
        } else {
// иначе
//если будет ошибка ввода, то в строке поиска в браузере появится параметр "?error", который мы чекаем и передаем
            if (request.getParameterMap().containsKey("error")) {
                model.addAttribute("error", true);
                System.out.println("Error req...");
            }
            return "login";
        }

    }


}
