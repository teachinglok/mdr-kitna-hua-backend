package com.zepiox.mdr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSuccessResponse {
    private Object data; // Can be JSON array or JSON object
    private String message;
    private Object metadata;
}
