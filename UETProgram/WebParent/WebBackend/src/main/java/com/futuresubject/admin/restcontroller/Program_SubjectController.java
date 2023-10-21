package com.futuresubject.admin.restcontroller;

import com.futuresubject.admin.dto.Program_SubjectDto;
import com.futuresubject.admin.service.ProgramService;
import com.futuresubject.admin.service.Program_SubjectService;
import com.futuresubject.admin.service.SubjectService;
import com.futuresubject.common.entity.Program_Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class Program_SubjectController {
    @Autowired
    private Program_SubjectService programSubjectService;
    @Autowired
    private ProgramService programService;
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/programsubjects/new")
    public Program_SubjectDto createProgramSubject() {
        Program_SubjectDto programSubjectDto = new Program_SubjectDto();
        programSubjectDto.setListOfSubjectId(subjectService.listOfSubjectId());
        programSubjectDto.setListOfProgramFullCode(programService.listOfProgramFullCode());
        return programSubjectDto;
    }

    @PostMapping("/programsubjects/new/created")
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CREATED)
    public Program_Subject saveProgramSubject(@RequestBody Program_SubjectDto programSubjectDto) {
         return programSubjectService.save(programSubjectDto);

    }
}