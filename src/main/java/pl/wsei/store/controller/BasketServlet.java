package pl.wsei.store.controller;

import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import pl.wsei.store.model.Basket;
import pl.wsei.store.service.BasketService;

@WebServlet(name = "BasketServlet", value = "/basket-servlet")
public class BasketServlet extends HttpServlet {

    private BasketService basketService = new BasketService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        List<Basket> items = basketService.getAllItems();

        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Oto aktualny stan Twojego koszyka:</h1>");

        if (items != null && !items.isEmpty()) {
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Item</th><th>Quantity</th><th>Akcje</th></tr>");
            for (Basket item : items) {
                out.println("<tr>");
                out.println("<td>" + item.getId() + "</td>");
                out.println("<td>" + item.getItem() + "</td>");
                out.println("<td>" + item.getQuantity() + "</td>");
                out.println("<td>");
                out.println("<form action='" + request.getContextPath() + "/sell-from-basket' method='post' style='display:inline;'>");
                out.println("<input type='hidden' name='item' value='" + item.getItem() + "'>");
                out.println("<input type='number' name='quantity' value='1' min='1' style='width:50px;'>");
                out.println("<input type='submit' value='Usuń'>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } else {
            out.println("<p>Koszyk jest pusty.</p>");
        }

        out.println("<br><br>");
        out.println("<form action='" + request.getContextPath() + "/index.jsp' method='get'>");
        out.println("<input type='submit' value='Powrót do strony głównej'>");
        out.println("</form>");

        out.println("<form action='" + request.getContextPath() + "/clear-basket' method='post'>");
        out.println("<input type='submit' value='Wyczyść koszyk'>");
        out.println("</form>");

        out.println("</body></html>");
    }
}