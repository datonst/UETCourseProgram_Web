package com.futuresubject.common.entity;

import com.futuresubject.common.entity.Enum.GenderType;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass

public class Person {
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    private String address;
    private String phone;

    public Person(String name, Integer age, GenderType gender, String address, String phone) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
    }
}
