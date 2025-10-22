package com.filazero.demo.delivery.dtos;

import java.time.LocalDateTime;

public record DeliveryRequestDTO(

   String status,
   LocalDateTime creatAt,
   String confirmationCode




) {}
