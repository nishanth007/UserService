package com.scaler.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "Tokens")
public class Token extends BaseModel {
    private String value;

    private Date expires;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Boolean deleted = false;
}
