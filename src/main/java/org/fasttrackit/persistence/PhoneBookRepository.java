package org.fasttrackit.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.fasttrackit.domain.PhoneBook;
import org.fasttrackit.transfer.SavePhoneBookRequest;
import org.fasttrackit.transfer.UpdatePhoneBookRequest;
import org.omg.CORBA.PolicyHolder;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookRepository {

    public void createPhoneBook(SavePhoneBookRequest request) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String insertSql = "INSERT INTO phone_books (`name`, surname, phoneNumber, age) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, request.getName());
            preparedStatement.setString(2, request.getSurname());
            preparedStatement.setString(3, request.getPhoneNumber());
            preparedStatement.setString(4, request.getAge());


            preparedStatement.executeUpdate();
        }
    }

    public List<PhoneBook> getPhoneBooks() throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String query = "SELECT id, `name`, surname, phoneNumber, age FROM phone_books ORDER BY id DESC"; //tilda (`) reserved keyword for name

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<PhoneBook> response = new ArrayList<>();

            while (resultSet.next()) {
                PhoneBook phoneBook = new PhoneBook();
                phoneBook.setId(resultSet.getLong("id"));
                phoneBook.setName(resultSet.getString("name"));
                phoneBook.setSurname(resultSet.getString("surname"));
                phoneBook.setPhoneNumber(resultSet.getString("phoneNumber"));
                phoneBook.setAge(resultSet.getString("age"));

                response.add(phoneBook);
            }
            return response;
        }
    }

    public void updatePhoneBook(UpdatePhoneBookRequest request) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String updateSql = "UPDATE phone_books SET `name` = ?, surname = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, request.getName());
            preparedStatement.setString(2, request.getSurname());
            preparedStatement.setLong(3, request.getId());

            preparedStatement.executeUpdate();
        }
    }

    public void deletePhoneBook(long id) throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = DatabaseConfiguration.getConnection()) {

            String deleteSql = "DELETE FROM phone_books WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        }
    }
}