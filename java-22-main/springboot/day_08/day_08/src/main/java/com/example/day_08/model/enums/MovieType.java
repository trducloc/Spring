package com.example.day_08.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum MovieType {
    PHIM_LE("Phim le"),
    PHIM_BO("Phim bo"),
    PHIM_CHIEU_RAP("Phim chieu rap");
    private final String value;

}
