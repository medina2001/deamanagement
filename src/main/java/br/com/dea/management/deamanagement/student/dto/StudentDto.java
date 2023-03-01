package br.com.dea.management.deamanagement.student.dto;


import br.com.dea.management.deamanagement.student.domain.Student;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class StudentDto {

    private String name;
    private String email;
    private String university;
    private String graduation;
    private String linkedin;
    private LocalDate finishDate;

    public static List<StudentDto> fromStudents(List<Student> students){
        return students.stream().map(student -> {
            StudentDto studentDto = StudentDto.fromStudent(student);
            return studentDto;
        }).collect(Collectors.toList());
    }

    public static StudentDto fromStudent(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(student.getUser().getName());
        studentDto.setEmail(student.getUser().getEmail());
        studentDto.setLinkedin(student.getUser().getLinkedin());
        studentDto.setGraduation(student.getGraduation());
        studentDto.setUniversity(student.getUniversity());
        studentDto.setFinishDate(student.getFinishDate());

        return studentDto;
    }

}
