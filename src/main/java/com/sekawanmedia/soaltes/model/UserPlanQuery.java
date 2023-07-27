package com.sekawanmedia.soaltes.model;

import lombok.Data;

@Data
public class UserPlanQuery {
    private int id;
    private int userId;
    private int planId;
    private String name;
    private int limitPerHour;
}
