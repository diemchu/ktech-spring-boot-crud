package com.example.crud;

// 학생의 정보를 담는 용도
//DTO: transfer object : 데이터를 주고 받기 위한 클래스
public class StudentDto {
    private Long id;
    private String name;
    private String email;

    public StudentDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public StudentDto(String name, String email) {
        this.id = null;
        this.name = name;
        this.email = email;
    }

    public StudentDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
