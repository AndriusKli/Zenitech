package com.example.andriuskli.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    public Transaction(Long previousOwnerId, @NotNull Long newOwner) {
        this.previousOwnerId = previousOwnerId;
        this.newOwner = newOwner;
    }

    @Id
    @GeneratedValue
    private Long transactionId;

    private Long previousOwnerId;

    @NotNull
    private Long newOwner;

//    @NotNull
//    @DecimalMin("0.01")
//    @DecimalMax("1.0")
//    private Double percentageOwned;

    @CreatedDate
    private LocalDateTime validFrom;

}
