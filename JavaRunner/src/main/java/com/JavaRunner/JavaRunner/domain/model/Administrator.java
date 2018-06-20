package com.JavaRunner.JavaRunner.domain.model;

import com.JavaRunner.JavaRunner.utils.Capitalize;
import com.JavaRunner.JavaRunner.utils.validations.RegexFilters;
import com.JavaRunner.JavaRunner.utils.validations.Validations;
import com.JavaRunner.JavaRunner.utils.validations.Validator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "administrator")
public class Administrator extends DatabaseCommons implements Validator<Administrator> {
    @Column(nullable = false, length = 64)
    private String login;
    @Column(nullable = false, length = 64)
    private String callback;
    @Column(nullable = false, length = 192)
    private String name;
    @Column(nullable = false, length = 256)
    private String email;
    @Column(nullable = false, length = 18)
    private String rg;
    @Column(nullable = false, length = 14)
    private String cpf;
    @Column(nullable = false, length = 11)
    private String sex;
    @Column(nullable = false, length = 64)
    @JsonIgnore
    private String password;
    @Column(nullable = false, length = 20)
    private String birthDate;

    @Override
    public HashMap<String, String> findErrors() throws Exception {
        HashMap<String, String> errors = new HashMap<>();
        if (Validations.beforeThanToday(this.getBirthDate())) {
            errors.put("nascimento", "A data de nascimento deve ser anterior a hoje");
        }
        if (!RegexFilters.isValidEmail(this.getEmail())) {
            errors.put("email", "Erro no padrão do email");
        }
        if (!Validations.isValidCpf(this.getCpf())) {
            errors.put("cpf", "CPF inválido");
        }
        return errors;
    }

    @Override
    public Administrator beautify() {
        return new Administrator()
                .setCpf(cpf)
                .setEmail(email)
                .setLogin(login)
                .setSex(Capitalize.capitalizeByWords(sex))
                .setBirthDate(birthDate)
                .setRg(rg)
                .setCallback(callback)
                .setPassword(password)
                .setName(Capitalize.brazilianCapitalize(name));
    }
}
