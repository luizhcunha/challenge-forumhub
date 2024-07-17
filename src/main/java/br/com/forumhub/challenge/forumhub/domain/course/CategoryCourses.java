package br.com.forumhub.challenge.forumhub.domain.course;

import java.util.ArrayList;
import java.util.List;

public enum CategoryCourses {
    LOGIC,
    PYTHON,
    NODE_JS,
    PHP,
    JAVASCRIPT,
    JAVA,
    DOT_NET,
    GAMES,
    COMPUTATION,
    QUALITY_ASSURANCE,
    ELIXIR,
    KOTLIN,
    EMBEDDED_ROBOTICS,
    CLOJURE,
    GOLANG,
    C,
    C_PLUS_PLUS,
    HTML_CSS,
    SVELTE,
    VUEJS,
    REACT,
    NEXTJS,
    ANGULAR,
    AUTOMATION_PERFORMANCE,
    WORDPRESS,
    SQL_DATABASES,
    DATA_ENGINEERING,
    DATA_SCIENCE,
    DATA_VISUALIZATION,
    MACHINE_LEARNING,
    EXCEL_BUSINESS_INTELLIGENCE,
    NOSQL,
    STATISTICS;

    public static CategoryCourses getByName (String name) {
        try {
            return CategoryCourses.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        for (CategoryCourses category : values()) {
            categories.add(category.name().toLowerCase());
        }
        return categories;
    }
}
