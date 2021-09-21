package com.ocean.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {

    Boolean success;

    String message;

    Object data;

}
