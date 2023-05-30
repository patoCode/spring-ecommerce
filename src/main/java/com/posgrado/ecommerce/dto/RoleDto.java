package com.posgrado.ecommerce.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@NoArgsConstructor
public class RoleDto {

    @NotBlank(message = "{role.name.blank}")
    @UniqueElements(message = "{role.name.duplicate}")
    @Size(min = 3, max = 255, message = "{role.name.size}")
    private String name;
    private String description;
}