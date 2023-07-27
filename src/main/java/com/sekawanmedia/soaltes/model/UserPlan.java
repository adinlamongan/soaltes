package com.sekawanmedia.soaltes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_plans")
@NoArgsConstructor
public class UserPlan {
    @Id
    private int id;
    private int userId;
    private int planId;
    private Boolean isActive;
}
