package com.company.sequrity.SEQ.config;

import com.company.sequrity.SEQ.service.AuthorService;
import com.company.sequrity.SEQ.service.AuthorServiceImpl;
import com.company.sequrity.SEQ.service.BookService;
import com.company.sequrity.SEQ.service.BookServiceImpl;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

public class HibernateConfig {
    @Autowired
    private DataSource dataSource;
    @Resource
    private Environment environment;
    private EntityManagerFactory factory;


   /* @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPackagesToScan(new String[]{"com.zadanie.nadanieweb.model"});
        bean.setHibernateProperties(getProperties());
        bean.afterPropertiesSet();

        return bean.getObject();
    }
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }*/
     @Bean
     @Autowired
     public FactoryBean<EntityManagerFactory> entityManagerFactory() {

         LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
         entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
         entityManagerFactoryBean.setDataSource(dataSource);
         entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
         entityManagerFactoryBean.setPackagesToScan(new String[]{"com.company.seq3"});
         entityManagerFactoryBean.setJpaProperties(getProperties());
         this.factory = entityManagerFactoryBean.getObject();
         return entityManagerFactoryBean;
     }
    @Bean
    public TransactionManager transactionManager() throws Exception {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource);
        jpaTransactionManager.setEntityManagerFactory(factory);
        return jpaTransactionManager;
    }

    private Properties getProperties(){

        Properties prop = new Properties();
        prop.setProperty("hibernate.hbm2ddl.auto", environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
        prop.setProperty("hibernate.dialect",environment.getRequiredProperty("spring.jpa.database-platform"));
        prop.setProperty("hibernate.USE_LEGACY_LIMIT_HANDLERS", environment.getRequiredProperty("spring.jpa.properties.hibernate.USE_LEGACY_LIMIT_HANDLERS"));
        return prop;
    }
    @Bean
    private AuthorService authorService(){
         return new AuthorServiceImpl();
    }
    @Bean
    private BookService bookService(){
         return new BookServiceImpl();
    }
}
