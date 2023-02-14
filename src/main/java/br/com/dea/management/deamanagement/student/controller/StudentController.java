package br.com.dea.management.deamanagement.student.controller;

import br.com.dea.management.deamanagement.student.domain.Student;
import br.com.dea.management.deamanagement.student.dto.StudentDto;
import br.com.dea.management.deamanagement.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/student/all", method = RequestMethod.GET)
    public List<Student> getStudentsAllRaw(){
        return this.studentService.findAllStudents();
    }

    @RequestMapping(value = "/student/without-pagination", method = RequestMethod.GET)
    public List<StudentDto> getStudentsWithoutPagination(){
        List<Student> students = this.studentService.findAllStudents();
        return StudentDto.fromStudents(students);
    }

    @GetMapping("/student")
    public Page<StudentDto> getStudents(@RequestParam Integer page,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String name){
        Page<Student> studentsPaged = this.studentService.findAllStudentsPaginated(page, pageSize);
        Page<StudentDto> students = studentsPaged.map(student -> StudentDto.fromStudent(student));
        return students;
    }

}
