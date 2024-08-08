package com.example.crud;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    private  StudentService service;
    //student 한번 생성 된다
    public StudentController(StudentService service){
        this.service = service;
    }
    @GetMapping("create-view")
    public String createView(){
        return  "create.html";
    }


    @PostMapping("create")
    public String create(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            Model model
     ){
       service.createStudent(name,email);
        //post - redirect -  get
        return "redirect:/create-view";
        // double post problem
//        return "create.html";
    }

    @GetMapping("home")
    public String home(Model model){
        model.addAttribute("studentList",service.readAllStudents());
        return "home.html";
    }

    @GetMapping("search")
    public String search(){
        return  "search.html";
    }

    @PostMapping("result")
    public String result(
            @RequestParam("info") String info,
            Model model
    ){
        model.addAttribute("student",
                service.readStudent(info)
                );
        return "result";
    }

    //url 변수로 지급한다 (경로 변수)
    @GetMapping("{id}")
    public String readOne(@PathVariable("id") Long id,
                    Model model
    ){
        StudentDto studentDto =service.readOnetudent(id);
        model.addAttribute("student",studentDto);
        return "result";
    }

    // http://124.0.0.1:8080/1/update-view
    @GetMapping("{id}/update-view")
    public String update(@PathVariable("id") Long id, Model model){
        StudentDto dto = service.readOnetudent(id);
        model.addAttribute("student",dto);
        return "update";
    }

    @PostMapping("{id}/update")
    public String update(
            @PathVariable("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ){
        service.update(id,name,email);
        return  String.format("redirect:/%s",id);
    }

    //Delete
    @GetMapping("{id}/delete-view")
    public String delete(@PathVariable("id") Long id, Model model){
        StudentDto student = service.readOnetudent(id);
        model.addAttribute("student",student);
        return  "delete";
    }
    @PostMapping("{id}/delete")
    public String delete(@PathVariable("id") Long id){
        service.delete(id);
        return  "redirect:/home";
    }


}
