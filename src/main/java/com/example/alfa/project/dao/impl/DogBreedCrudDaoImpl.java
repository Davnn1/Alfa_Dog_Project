package com.example.alfa.project.dao.impl;

import com.example.alfa.project.dao.DogBreedCrudDao;
import com.example.alfa.project.util.exception.jdbc.CreateFailedException;
import com.example.alfa.project.util.exception.jdbc.DeleteFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DogBreedCrudDaoImpl implements DogBreedCrudDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DogBreedCrudDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, List<String>> getBreedList() {
        String sql = "SELECT b.NAME as BREED_NAME, sb.NAME as SUB_BREED_NAME " +
                "FROM BREED b " +
                "LEFT JOIN SUB_BREED sb ON b.ID = sb.BREED_ID";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        return getStringListMap(rows);
    }

    @Override
    public Map<String, List<String>> getSubBreedListByBreed(String breed) {
        String sql = "SELECT b.NAME as BREED_NAME, sb.NAME as SUB_BREED_NAME " +
                "FROM BREED b " +
                "LEFT JOIN SUB_BREED sb ON b.ID = sb.BREED_ID " +
                "WHERE b.NAME = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, breed);

        return getStringListMap(rows);
    }

    @Override
    public void createNewBreed(String breed) {
        String sql = "INSERT INTO BREED (NAME, CREATE_BY) VALUES (?, 'human')";

        jdbcTemplate.update(sql, breed);
    }

    @Override
    public void createNewSubBreed(String subBreed, String breed) {
        String sql = "INSERT INTO SUB_BREED (NAME, BREED_ID, CREATE_BY) VALUES (?, (SELECT ID FROM BREED WHERE NAME = ?), 'human')";
        try {
            jdbcTemplate.update(sql, subBreed, breed);
        } catch (Exception e) {
            throw new CreateFailedException("Error creating sub breed" + e.getMessage());
        }
    }

    @Override
    public void deleteBreed(String breed) {
        try {
            String sqlSubBreed = "DELETE FROM SUB_BREED WHERE BREED_ID = (SELECT ID FROM BREED WHERE NAME = ?)";
            jdbcTemplate.update(sqlSubBreed, breed);
            String sqlBreed = "DELETE FROM BREED WHERE NAME = ?";
            jdbcTemplate.update(sqlBreed, breed);
        } catch (Exception e) {
            throw new DeleteFailedException("Error deleting breed" + e.getMessage());
        }
    }

    @Override
    public void deleteSubBreed(String subBreed, String breed) {
        try {
            String sql = "DELETE FROM SUB_BREED WHERE NAME = ? AND BREED_ID = (SELECT ID FROM BREED WHERE NAME = ?)";
            jdbcTemplate.update(sql, subBreed, breed);
        } catch (Exception e) {
            throw new DeleteFailedException("Error deleting sub breed" + e.getMessage());
        }
    }

    @Override
    public void updateBreed(String breed, String newBreed) {
        String sql = "UPDATE BREED SET NAME = ? WHERE NAME = ?";
        jdbcTemplate.update(sql, newBreed, breed);
    }

    @Override
    public void updateSubBreed(String subBreed, String newSubBreed, String breed) {
        String sql = "UPDATE SUB_BREED SET NAME = ? WHERE NAME = ? AND BREED_ID = (SELECT ID FROM BREED WHERE NAME = ?)";
        jdbcTemplate.update(sql, newSubBreed, subBreed, breed);
    }

    private Map<String, List<String>> getStringListMap(List<Map<String, Object>> rows) {
        Map<String, List<String>> result = new HashMap<>();

        for (Map<String, Object> row : rows) {
            String breedName = (String) row.get("BREED_NAME");
            String subBreedName = (String) row.get("SUB_BREED_NAME");

            result.putIfAbsent(breedName, new ArrayList<>());
            if (subBreedName != null) {
                result.get(breedName).add(subBreedName);
            }
        }

        return result;
    }
}
