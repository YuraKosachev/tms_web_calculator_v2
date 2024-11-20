package com.app.web_calculator_v2.filters;

import com.app.web_calculator_v2.models.ValidatorResult;
import com.app.web_calculator_v2.services.ValidationService;
import com.app.web_calculator_v2.validators.EmptyStringValidator;
import com.app.web_calculator_v2.validators.ExpressionValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(servletNames = "CalculationServlet")
public class CalculationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if(!req.getMethod().equalsIgnoreCase("POST")){
            chain.doFilter(req, res);
            return;
        }
        String expression = req.getParameter("expression");

        ValidationService validationService = ValidationService.getValidationService();

        ValidatorResult validationResult = validationService.validate(expression,
                EmptyStringValidator.getInstance(),
                ExpressionValidator.getInstance("([^0-9+-/*().\\s]+)", "the expression must not contain letters and comma characters"));

        if(!validationResult.isSuccess())
        {
            req.setAttribute("validation_errors", validationResult.getErrors());
            getServletContext().getRequestDispatcher("/calculation.jsp").forward(req, res);
        }
        chain.doFilter(req, res);
    }
}
