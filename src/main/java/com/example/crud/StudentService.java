package com.example.crud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    //마지막 사용된 아이디를 보관 변수
    private Long nextId = 1L;
    private final List<StudentDto> studentDtoList = new ArrayList<>();
    private static  final Logger log = LoggerFactory.getLogger(StudentService.class);
    private  StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public  StudentDto createStudent(
            String name,String email
    ){
        StudentDto newStudent= new StudentDto(nextId,name,email);
        nextId++;
        studentDtoList.add(newStudent);
        repository.create(newStudent);
        return  newStudent;
    }
    public List<StudentDto> readAllStudents(){
        return studentDtoList;
    }
    public  StudentDto  readStudent(String info){
        for(StudentDto s:studentDtoList){
            if( info.equals(String.valueOf(s.getId())) ||
                info.equals(s.getName()) ||
                    info.equals(s.getEmail())
            ) return s;

        }
        return null;
    }
    public  StudentDto  readOnetudent(Long id){
        for(StudentDto s:studentDtoList){
            if(id.equals(s.getId())) return s;
        }
        return null;
    }

    // user 정보를 업그레이드
    public  StudentDto  update(Long id,String name,String email){
        //찾고 있는 학생의 id
        int targetIdx= -1;
        for(int i =  0; i<studentDtoList.size() ; i++){
            StudentDto dto = studentDtoList.get(i);
            if(dto.getId().equals(id)){
                targetIdx = i ;
                break;
            }
        }
        if(targetIdx != -1){
            studentDtoList.get(targetIdx).setName(name);
            studentDtoList.get(targetIdx).setEmail(email);
        }
        return studentDtoList.get(targetIdx);
    }
    //user delete
    public StudentDto delete(Long id){
        int targetIdx = -1;
        for(int i =0 ; i < studentDtoList.size() ; i ++){
            if(studentDtoList.get(i).getId().equals(id))
            {
                targetIdx = i;
                break;
            }
        }
        return  targetIdx == -1 ? null : studentDtoList.remove(targetIdx);
    }
}
