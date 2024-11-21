package kz.timka.springshop.controllers;

import kz.timka.springshop.exceptions.ResourceNotFoundException;
import kz.timka.springshop.models.Student;
import kz.timka.springshop.repositories.StudentRepository;
import kz.timka.springshop.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;
    private StudentRepository studentRepository;
    @Autowired
    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }




    @GetMapping("/students")
    public List<Student> getAllStudent() {
        return studentService.findAll();
    }


    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found by id: " + id));
    }


    @GetMapping("/students/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

    @GetMapping("/students/change_score")
    public void changeScore(@RequestParam Long studentId, @RequestParam Integer delta) {
        studentService.changeScore(studentId, delta);
    }

    @GetMapping("/students/score_between")
    public List<Student> findStudentsByScoreBetween(
            @RequestParam(defaultValue = "0") Integer min,
            @RequestParam(defaultValue = "100") Integer max) {
        return studentService.findStudentsByScoreBetween(min, max);
    }


    @GetMapping("/demo")
    public Object demo(@RequestParam String name) {
        return studentRepository.sqlGetScoreByName(name);
    }

    @PostMapping("/students")
    public Student save(@RequestBody Student student) {
        return studentService.save(student);
    }





}
