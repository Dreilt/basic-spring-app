package pl.dreilt.basicspringapp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class CustomErrorController implements ErrorController {

    private final MessageSource messageSource;

    public CustomErrorController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        String errorMessage = "";

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            errorMessage = messageSource.getMessage("error.unknownError.errorMessage", null, Locale.getDefault());

            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                errorMessage = messageSource.getMessage("error.500.errorMessage", null, Locale.getDefault());

                if (throwable.getCause().getMessage().equals("Invalid role: USER")) {
                    errorMessage = messageSource.getMessage("register.registerForm.roleNotFound.errorMessage", null, Locale.getDefault());
                }

                model.addAttribute("errorMessage", errorMessage);
                return "error/500";
            }
        }

        return "error/error";
    }
}
