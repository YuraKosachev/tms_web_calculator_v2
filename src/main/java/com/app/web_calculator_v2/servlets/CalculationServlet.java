package com.app.web_calculator_v2.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.License;

import java.io.IOException;

@WebServlet(name = "CalculationServlet", value = "/")
public class CalculationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/calculation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String queryExpression = req.getParameter("expression");

        License.iConfirmNonCommercialUse("yuko_by");
        Expression expression = new Expression(queryExpression);
        req.setAttribute("result", "%s = %.2f".formatted(expression.getExpressionString(), expression.calculate()));
        getServletContext().getRequestDispatcher("/calculation.jsp").forward(req, resp);
    }
}
