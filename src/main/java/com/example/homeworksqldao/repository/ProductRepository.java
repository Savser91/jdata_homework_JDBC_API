package com.example.homeworksqldao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final String SCRIPT_NAME = "select_order_by_customer.sql";

    private final String query;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public ProductRepository() {
        this.query = read(SCRIPT_NAME);
    }

    public String getProductName(String name) {
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(query, Map.of("CUSTOMERNAME", name));
        StringBuilder stringBuilder = new StringBuilder();
        while (sqlRowSet.next()) {
            if (stringBuilder.length() > 0) stringBuilder.append(", ");
            stringBuilder.append(sqlRowSet.getString("product_name"));
        }
        return stringBuilder.toString();
    }

    public static String read(String scriptName) {
        try (InputStream is = new ClassPathResource(scriptName).getInputStream()) {
            return String.join("\n", readLines(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> readLines(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().collect(Collectors.toList());
    }
}
