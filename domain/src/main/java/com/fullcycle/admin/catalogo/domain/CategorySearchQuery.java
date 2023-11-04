package com.fullcycle.admin.catalogo.domain;

public record CategorySearchQuery(
    int page, int perPage, String terms, String sort, String direction) {}
