package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.Enum.GenderType;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.futuresubject.common.entity.Student}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class StudentDto implements Serializable {
    String studentId;
    String name;
    String dateOfBirth;
    String gender;
    String address;
    String phone;
    String classFullName;
    List<GenderType> listOfGender;
    List<String> listOfClassroom;
}