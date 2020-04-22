package com.patternpedia.auth.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "pg-uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @Type(type = "pgsql_enum")
    private List<UserRole> role = new ArrayList<>(Arrays.asList(UserRole.MEMBER));

    private String mail;

    private String name;

    @JsonIgnore
    private String password;

    public UserEntity(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
    }

}
