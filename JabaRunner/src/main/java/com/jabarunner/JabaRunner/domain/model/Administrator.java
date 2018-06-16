package com.jabarunner.JabaRunner.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jabarunner.JabaRunner.domain.utils.Capitalize;
import com.jabarunner.JabaRunner.domain.utils.contracts.ModelValidation;
import com.jabarunner.JabaRunner.domain.utils.exceptions.BeforeDateException;
import com.jabarunner.JabaRunner.domain.utils.exceptions.CpfException;
import com.jabarunner.JabaRunner.domain.utils.exceptions.EmailException;
import com.jabarunner.JabaRunner.domain.utils.exceptions.PasswordException;
import com.jabarunner.JabaRunner.domain.utils.validations.RegexFilters;
import com.jabarunner.JabaRunner.domain.utils.validations.Validations;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "administrador")
public class Administrator implements ModelValidation<Administrator> {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "login", nullable = false, length = 64)
    private String login;
    @Basic
    @Column(name = "autenticacao_secundaria", nullable = false, length = 64)
    private String secondaryAuth;
    @Basic
    @Column(name = "nome_completo", nullable = false, length = 128)
    private String name;
    @Basic
    @Column(name = "email", nullable = false, length = 128)
    private String email;
    @Basic
    @Column(name = "rg", nullable = false, length = 18)
    private String rg;
    @Basic
    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;
    @Basic
    @Column(name = "sexo", nullable = false, length = 20)
    private String sex;
    @Basic
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Basic
    @Column(name = "data_nascimento", nullable = false, length = 20)
    private String birthDate;
    @Column(insertable = false, updatable = false)
    @NotNull
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;

    public Administrator validate() throws Exception {
        if (!Validations.passwordEquals(this.getPassword(), this.getConfirmPassword())) throw new PasswordException();
        if (Validations.beforeThanToday(this.getBirthDate())) throw new BeforeDateException();
        if (!RegexFilters.isValidEmail(this.getEmail())) throw new EmailException();
        if (!Validations.isValidCpf(this.getCpf())) throw new CpfException();
        return new Administrator()
                .setEmail(this.getEmail())
                .setName(Capitalize.brazilianCapitalize(this.getName()))
                .setBirthDate(this.getBirthDate())
                .setPassword(this.getPassword())
                .setConfirmPassword(this.getConfirmPassword())
                .setCpf(this.getCpf())
                .setRg(this.getRg())
                .setLogin(this.getLogin())
                .setSecondaryAuth(this.getSecondaryAuth())
                .setSex(this.getSex());
    }
}
