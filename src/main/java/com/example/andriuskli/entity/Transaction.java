package com.example.andriuskli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private Long transactionId;

    @NotNull
    private Long previousOwnerId;

    @NotNull
    private Long newOwnerId;

    @NotNull
    @DecimalMin("0.01")
    @DecimalMin("1.0")
    private Double percentageTransferred;

    @CreatedDate
    private LocalDateTime transactionTime;

}
