package com.example.demo;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@Validated
public class StudentController {

    @Autowired
    private final MainStudentService studentService;

    public StudentController(MainStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String getPage() {
        return "Welcome to the page";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudent();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/save")
    public ResponseEntity<String> createStudent(@Valid @RequestBody Student student) {
        /*Student createdStudent =*/ studentService.createStudent(student);
        return ResponseEntity.ok("User is valid");
    }

//    @PostMapping("/save")
//    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()){
//            // جمع‌آوری پیام‌های خطا
//            Map<String, String> errors = bindingResult.getFieldErrors().stream()
//                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
//            return ResponseEntity.badRequest().body(errors);
//        }
//    
//        Student createdStudent = studentService.createStudent(student);
//        return ResponseEntity.ok(createdStudent);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student updateStudent) {
        Student student = studentService.updateStudent(id, updateStudent);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}