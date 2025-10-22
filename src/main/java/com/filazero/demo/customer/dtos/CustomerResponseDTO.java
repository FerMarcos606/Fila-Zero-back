package com.filazero.demo.customer.dtos;

import java.util.Set;

import com.filazero.demo.profile.dtos.ProfileResponseDTO;
import com.filazero.demo.role.dtos.RoleResponseDTO;

public record CustomerResponseDTO(

Long id,
String username,
ProfileResponseDTO profile,
Set<RoleResponseDTO> roles

) {} 