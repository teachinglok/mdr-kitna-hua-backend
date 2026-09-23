package com.zepiox.mdr.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmailRequest {

    private Sender sender;
    private List<Recipient> to;
    private String subject;
    private String htmlContent;

    @Data
    public static class Sender {
        private String name;
        private String email;
    }

    @Data
    public static class Recipient {
        private String email;
        private String name;
    }
}
