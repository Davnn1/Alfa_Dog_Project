package com.example.alfa.project.dao.impl;

import com.example.alfa.project.dao.DogBreedSyncDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DogBreedSyncDaoImpl implements DogBreedSyncDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DogBreedSyncDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void syncDogBreeds(List<String> dogBreeds) {
        String clearSubSql = "DELETE FROM SUB_BREED WHERE create_by = 'system'";

        jdbcTemplate.update(clearSubSql);

        String clearSql = "DELETE FROM BREED WHERE create_by = 'system'";

        jdbcTemplate.update(clearSql);

        String insertSql = "INSERT INTO BREED (name, create_by) VALUES (?, 'system')";

        jdbcTemplate.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, dogBreeds.get(i));
            }

            @Override
            public int getBatchSize() {
                return dogBreeds.size();
            }
        });
    }

    @Override
    public void syncDogSubBreeds(Map<String, List<String>> dogSubBreeds) {
        String insertSql = "INSERT INTO SUB_BREED (name, breed_id, create_by) VALUES (?, ?, 'system')";

        List<Object[]> batchArgs = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : dogSubBreeds.entrySet()) {
            String breedName = entry.getKey();
            List<String> subBreeds = entry.getValue();

            String breedIdSql = "SELECT id FROM BREED WHERE name = ?";
            Integer breedId = jdbcTemplate.queryForObject(breedIdSql, Integer.class, breedName);

            if (breedId != null) {
                for (String subBreed : subBreeds) {
                    batchArgs.add(new Object[]{subBreed, breedId});
                }
            }
        }

        jdbcTemplate.batchUpdate(insertSql, batchArgs);
    }

}
