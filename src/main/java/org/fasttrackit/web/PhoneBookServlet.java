package org.fasttrackit.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.domain.PhoneBook;
import org.fasttrackit.persistence.PhoneBookRepository;
import org.fasttrackit.service.PhoneBookService;
import org.fasttrackit.transfer.SavePhoneBookRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/phone-books")
public class PhoneBookServlet extends HttpServlet {

    private PhoneBookService phoneBookService = new PhoneBookService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SavePhoneBookRequest savePhoneBookRequest = objectMapper.readValue(req.getReader(), SavePhoneBookRequest.class);

        try {
            phoneBookService.createPhoneBook(savePhoneBookRequest);
        } catch (Exception e) {
            resp.sendError(500, "Internal error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<PhoneBook> phoneBooks = phoneBookService.getPhoneBooks();

            //serialising or marshalling - transformarea unui obiect intr-un String
            ObjectMapper objectMapper = new ObjectMapper();
            String responseJson = objectMapper.writeValueAsString(phoneBooks);

            //content type or mime type
            resp.setContentType("application/json");
            resp.getWriter().print(responseJson);
            resp.getWriter().flush();

        } catch (Exception e) {
            resp.sendError(500,"There was an error processing your request. " + e.getMessage()); //status code = 404 not found
        }
    }
}
