package com.JavaRunner.JavaRunner.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.JavaRunner.JavaRunner.utils.Capitalize;
import com.JavaRunner.JavaRunner.utils.contracts.ModelValidation;
import com.JavaRunner.JavaRunner.utils.exceptions.BeforeDateException;
import com.JavaRunner.JavaRunner.utils.exceptions.CpfException;
import com.JavaRunner.JavaRunner.utils.exceptions.EmailException;
import com.JavaRunner.JavaRunner.utils.exceptions.PasswordException;
import com.JavaRunner.JavaRunner.utils.validations.RegexFilters;
import com.JavaRunner.JavaRunner.utils.validations.Validations;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "corredor")
public class Runner implements Serializable, ModelValidation<Runner> {
    @Id
    @GeneratedValue
    private Long id;
    @Basic
    @NotNull
    @Column(name = "apelido", nullable = false, length = 256)
    private String nickname;
    @Basic
    @NotNull
    @Column(name = "avatar", nullable = true, length = 256)
    private String avatar;
    @Basic
    @NotNull
    @Column(name = "nome_completo", nullable = false, length = 128)
    private String name;
    @Basic
    @NotNull
    @Column(name = "email", nullable = false, length = 128)
    private String email;
    @Basic
    @NotNull
    @Column(name = "rg", nullable = false, length = 20)
    private String rg;
    @Basic
    @NotNull
    @Column(name = "cpf", nullable = false, length = 20)
    private String cpf;
    @Basic
    @NotNull
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String confirmPassword;
    @Basic
    @NotNull
    @Column(name = "data_nascimento", nullable = false, length = 20)
    private String birthDate;
    @NotNull
    @Column(name = "sexo", nullable = false, length = 21)
    private String sex;

    @Override
    public Runner validate() throws Exception {
        if (!Validations.passwordEquals(this.getPassword(), this.getConfirmPassword())) throw new PasswordException();
        if (Validations.beforeThanToday(this.getBirthDate())) throw new BeforeDateException();
        if (!RegexFilters.isValidEmail(this.getEmail())) throw new EmailException();
        if (!Validations.isValidCpf(this.getCpf())) throw new CpfException();

        return new Runner()
                .setAvatar(getAvatar())
                .setBirthDate(getBirthDate())
                .setName(Capitalize.brazilianCapitalize(getName()))
                .setEmail(getEmail())
                .setRg(getRg())
                .setCpf(getCpf())
                .setNickname(getNickname())
                .setPassword(getPassword())
                .setConfirmPassword(getConfirmPassword())
                .setSex(getSex());
    }
}
