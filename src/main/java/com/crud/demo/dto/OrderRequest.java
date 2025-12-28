package com.crud.demo.dto;

import java.util.List;

public record OrderRequest(String customerEmail, List<Long> productIds) {
}
