package br.com.dea.management.deamanagement.student.controller;

import br.com.dea.management.deamanagement.student.domain.Student;
import br.com.dea.management.deamanagement.student.dto.StudentDto;
import br.com.dea.management.deamanagement.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Student> getStudentsAllRaw(){
        return this.studentService.findAllStudents();
    }

    @RequestMapping(value = "/without-pagination", method = RequestMethod.GET)
    public List<StudentDto> getStudentsWithoutPagination(){
        List<Student> students = this.studentService.findAllStudents();
        return StudentDto.fromStudents(students);
    }

    @GetMapping("")
    public Page<StudentDto> getStudents(@RequestParam Integer page,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String name){
        Page<Student> studentsPaged = this.studentService.findAllStudentsPaginated(page, pageSize);
        Page<StudentDto> students = studentsPaged.map(student -> StudentDto.fromStudent(student));
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> optionalStudent = this.studentService.findStudentById(id);
        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(optionalStudent.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
