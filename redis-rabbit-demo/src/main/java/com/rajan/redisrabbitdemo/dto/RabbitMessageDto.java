package com.rajan.redisrabbitdemo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RabbitMessageDto {
    Long id;
    String name;
    String message;
}
