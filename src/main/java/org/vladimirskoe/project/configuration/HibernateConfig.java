package org.vladimirskoe.project.configuration;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.util.Assert;

@Configuration
public class HibernateConfig {

    private static final String DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String SHOW_SQL = "hibernate.show_sql";
    private static final String DIALECT = "hibernate.dialect";

    private DataSource dataSource;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.jpa.show-sql}")
    private String showSql;

    @Value("${spring.jpa.hibernate.scan-packages}")
    private String packagesToScan;

    @Value("${spring.jpa.hibernate.dialect}")
    private String dialect;


    @Autowired
    public HibernateConfig(DataSource dataSource) {
        this.dataSource = dataSource;

        Assert.notNull(this.dataSource, "Datasource cannot be null");
    }

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(packagesToScan);
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.afterPropertiesSet();
        return sessionFactory.getObject();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(DDL_AUTO, ddlAuto);
        properties.put(SHOW_SQL, showSql);
        properties.put(DIALECT, dialect);
        return properties;
    }
}
