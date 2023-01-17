package ua.omenyuk.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.omenyuk.spring.models.Project;

import java.util.List;

/**
 * @author Vycheslav Omenyuk
 */


@Component
public class ProjectDAO {

    private final JdbcTemplate jdbcTemplate;
    private static int PROJECT_COUNT;

    @Autowired
    public ProjectDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> indexProject() {
        return jdbcTemplate.query("SELECT * FROM Project", new ProjectMapper());
    }

    public Project showProject(int project_id) {
        return jdbcTemplate.query("SELECT * FROM Project WHERE project_id=?", new Object[]{project_id}, new BeanPropertyRowMapper<>(Project.class))
                .stream().findAny().orElse(null);
    }
}



