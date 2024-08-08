package com.example.crud;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private final Connection connection;
    private final String dbUrl = "jdbc:sqlite:db.sqlite";
    private List<StudentDto> studentDtoList;
    //생성자에서 db 연결한다
    public StudentRepository() {
        studentDtoList  = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(StudentDto student) {
        String insertQuery = """
                INSERT INTO STUDENT(NAME,EMAIL)
                VALUES(?,?);
                """;
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        )
        {
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.executeUpdate();
            // 몇개의 줄의 영향을 받았느냐 바환한다
        }catch(SQLException e)
        {
                throw new RuntimeException(e);
        }
    }



}
