package com.itschool.springapp.service;

import java.time.LocalDateTime;

public record Account(int id,
                      String firstName,
                      String lastName,
                      String email,
                      LocalDateTime date) {
}
