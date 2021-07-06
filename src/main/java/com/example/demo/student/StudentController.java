package com.example.demo.student;

import com.example.demo.student.Student;
import com.example.demo.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "students/info", method = RequestMethod.GET)
    public String info(){
        return "The application is up...";
    }

    @RequestMapping(path = "students/createstudent", method = RequestMethod.POST)
    public String createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @RequestMapping(path = "students/readstudents", method = RequestMethod.GET)
    public List<Student> readStudents(){
        return studentService.readStudents();
    }

    @RequestMapping(path = "students/updatestudent", method = RequestMethod.PUT)
    public String updateStudet(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @RequestMapping(path = "students/deletestudent", method = RequestMethod.DELETE)
    public String deleteStudent(@RequestBody Student student){
        return studentService.deleteStudent(student);
    }
}
