package org.fasttrackit.persistence;

import org.fasttrackit.domain.PhoneBook;
import org.fasttrackit.transfer.SavePhoneBookRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookRepository {

    public void createPhoneBook(SavePhoneBookRequest request) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "INSERT INTO phone_books (`name`, surname, phoneNumber) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getName());
            preparedStatement.setString(2, request.getSurname());
            preparedStatement.setString(3, request.getPhoneNumber());

            preparedStatement.executeUpdate();
        }
    }
    public List<PhoneBook> getPhoneBooks() throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String query = "SELECT id, `name`, surname, phoneNumber FROM phone_books ORDER BY id DESC"; //tilda (`) reserved keyword for name

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<PhoneBook> response = new ArrayList<>();

            while (resultSet.next()) {
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setId(resultSet.getLong("id"));
                phoneBook.setName(resultSet.getString("name"));
                phoneBook.setSurname(resultSet.getString("surname"));
                phoneBook.setPhoneNumber(resultSet.getString("phoneNumber"));

                response.add(phoneBook);
            }
            return response;
        }
    }
}