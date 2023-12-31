package com.futuresubject.admin.dto;

import com.futuresubject.common.entity.Enum.RoleType;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.futuresubject.common.entity.Program_Subject}
 */
@Getter
@AllArgsConstructor
@Setter
@Jacksonized
@Builder
@NoArgsConstructor
public class Program_SubjectDto implements Serializable {
    String programFullCode;
    String subjectId;
    RoleType roleType;
    List<String> listOfProgramFullCode;
    List<String> listOfSubjectId;
    List<RoleType> listRoleType;
}