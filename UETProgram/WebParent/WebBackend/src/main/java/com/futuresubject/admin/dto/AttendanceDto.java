package com.futuresubject.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.extern.jackson.Jacksonized;


import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.futuresubject.common.entity.Attendance}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class AttendanceDto implements Serializable {
    String studentId;
    String programFullCode;
    String startDate;
    String endDate;
    List<String> listOfStudentId;
    List<String> listOfProgramFullCode;
}