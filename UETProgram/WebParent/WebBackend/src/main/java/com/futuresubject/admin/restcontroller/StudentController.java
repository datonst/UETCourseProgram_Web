package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.NotFoundDataExeption;
import com.futuresubject.admin.dto.StudentDto;
import com.futuresubject.admin.repository.StudentNotFoundException;
import com.futuresubject.admin.service.ClassroomService;
import com.futuresubject.admin.service.StudentService;
import com.futuresubject.common.entity.Enum.GenderType;
import com.futuresubject.common.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    ClassroomService classroomService;

    @GetMapping("/students")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDto> listStudents() {
        return studentService.listAll();
    }

    @GetMapping("/students/edit/{mssv}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public StudentDto studentAccount(@PathVariable(name = "mssv") String mssv) {
        StudentDto studentDto = null;
        try {
            studentDto = studentService.get(mssv);
            studentDto.setListOfClassroom(classroomService.listOfClassroom());
            studentDto.setListOfGender(Arrays.asList(GenderType.values()));

//            Set<Course> listCourse = student.getListCourse();
//            model.addAttribute("listCourse", listCourse);
//            model.addAttribute("student", student);
            return studentDto;
        } catch (StudentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/students/edit/save")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public void putEditStudent(@RequestBody StudentDto studentDto) {
         studentService.updateFromDto(studentDto);
    }

    @DeleteMapping("/students/delete/{subjectid}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEditStudent(@PathVariable(name = "subjectid") String studentid) {
        try {
            studentService.deleteByStudentid(studentid);
        } catch (NotFoundDataExeption e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/student/{mssv}/{nameCourse}")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public String studySubjectList(@PathVariable(name = "mssv") String mssv,
                                   Model model,
                                   @PathVariable(name = "nameCourse") String nameCourse) {
        return "";

    }

    @GetMapping("/student/{mssv}/{nameCourse}?=")

    public String getAllThisTypeSubject(@PathVariable(name = "mssv") String mssv,
                                        Model model,
                                        @PathVariable(name = "nameCourse") String nameCourse,
                                        @Param("typeSubject") String typeSubject) {
        return "";
    }

    @GetMapping("/students/new")
    public StudentDto createStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setListOfClassroom(classroomService.listOfClassroom());
        studentDto.setListOfGender(Arrays.asList(GenderType.values()));
        return studentDto;
    }

    @PostMapping("/students/new")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Student createSubject(@RequestBody StudentDto studentDto) throws NotFoundDataExeption {
//        if (studentDto.getStudentId() == null) {
//            throw new NotFoundDataExeption("Not found - student contain Null");
//        }
        return studentService.insert(studentDto);
    }
}
