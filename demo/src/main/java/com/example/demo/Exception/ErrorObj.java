package com.example.demo.Exception;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObj {
    private String error;
    private Integer statusCode;

}
