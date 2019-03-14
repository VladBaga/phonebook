package org.fasttrackit.service;

import org.fasttrackit.domain.PhoneBook;
import org.fasttrackit.persistence.PhoneBookRepository;
import org.fasttrackit.transfer.SavePhoneBookRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhoneBookService {

    private PhoneBookRepository phoneBookRepository = new PhoneBookRepository();

    public void createPhoneBook(SavePhoneBookRequest request) throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Creating phone-book log : " + request);
        phoneBookRepository.createPhoneBook(request);
    }

    public List<PhoneBook> getPhoneBooks() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Getting phone-books...");
        return phoneBookRepository.getPhoneBooks();
    }
}
