package com.JavaRunner.JavaRunner.domain.model;

import com.JavaRunner.JavaRunner.utils.Capitalize;
import com.JavaRunner.JavaRunner.utils.validations.RegexFilters;
import com.JavaRunner.JavaRunner.utils.validations.Validations;
import com.JavaRunner.JavaRunner.utils.validations.Validator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "runner")
public class Runner extends DatabaseCommons implements Serializable, Validator<Runner> {
    @NotNull
    @Column(name = "apelido", length = 256)
    private String nickname;
    @NotNull
    @Column(name = "avatar", length = 256)
    private String avatar;
    @NotNull
    @Column(name = "nome_completo", length = 192)
    private String name;
    @NotNull
    @Column(name = "email", length = 256)
    private String email;
    @NotNull
    @Column(name = "rg", length = 20)
    private String rg;
    @NotNull
    @Column(name = "cpf", length = 20)
    private String cpf;
    @NotNull
    @Column(name = "password", length = 60)
    private String password;
    @NotNull
    @Column(name = "data_nascimento", length = 20)
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private String birthDate;
    @NotNull
    @Column(name = "sexo", length = 21)
    private String sex;

    @Override
    public HashMap<String, String> findErrors() throws Exception {
        HashMap<String, String> errors = new HashMap<>();
        if (Validations.beforeThanToday(this.getBirthDate())) {
            errors.put("birthdate", "Birth date must before then today");
        }
        if (!RegexFilters.isValidEmail(this.getEmail())) {
            errors.put("email", "Wrong email pattern");
        }
        if (!Validations.isValidCpf(this.getCpf())) {
            errors.put("password", "Wrong cpf pattern or value");
        }
        return errors;
    }

    @Override
    public Runner beautify() {
        return new Runner()
                .setAvatar(getAvatar())
                .setBirthDate(getBirthDate())
                .setName(Capitalize.brazilianCapitalize(getName()))
                .setEmail(getEmail())
                .setRg(getRg())
                .setCpf(getCpf())
                .setNickname(getNickname())
                .setPassword(getPassword())
                .setSex(Capitalize.capitalizeByWords(sex));
    }
}
