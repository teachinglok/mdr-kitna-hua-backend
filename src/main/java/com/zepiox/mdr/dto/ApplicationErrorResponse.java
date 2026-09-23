package com.zepiox.mdr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationErrorResponse {
    private String errorCode;
    private String errorDescription;
}
