package ua.omenyuk.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import ua.omenyuk.spring.models.Project;
import ua.omenyuk.spring.models.Project;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Project project = new Project();

        project.setProjectId(resultSet.getInt("project_id"));
        project.setProjectName(resultSet.getString("project_name"));
        project.setStartDate(resultSet.getInt("start_date"));
        project.setEndDate(resultSet.getInt("end_date"));

        return project;

    }
}
