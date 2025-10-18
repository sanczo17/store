package pl.wsei.store.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.wsei.store.service.BasketService;

import java.io.IOException;

@WebServlet(name = "SellFromBasketServlet", value = "/sell-from-basket")
public class SellFromBasketServlet extends HttpServlet {

    private final BasketService basketService = new BasketService();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String item = request.getParameter("item");
        String quantityParam = request.getParameter("quantity");

        int quantity = 1;
        if (quantityParam != null && !quantityParam.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityParam);
            } catch (NumberFormatException e) {
                quantity = 1;
            }
        }

        try {
            basketService.sellItem(item, quantity);
            response.sendRedirect(request.getContextPath() + "/basket-servlet");
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }
}