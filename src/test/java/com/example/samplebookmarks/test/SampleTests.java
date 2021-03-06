package com.example.addressbook.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.jdbc.Work;
import org.junit.BeforeClass;
import org.junit.Test;

import com.britesnow.snow.testsupport.SnowTestSupport;
import com.britesnow.snow.util.MapUtil;
import com.britesnow.snow.web.db.hibernate.HibernateDaoHelper;
import com.britesnow.snow.web.db.hibernate.HibernateSessionInViewHandler;
import com.example.addressbook.entity.Item;
import com.example.addressbook.entity.User;

/**
 * This is a simple test class, with no asserts, just ot show how to use the SnowTestSupport
 * 
 * @author jeremychone
 *
 */
public class SampleTests extends SnowTestSupport {

    @BeforeClass
    public static void initTestClass() throws Exception {
        // Here we override one property to use the in memory DB
        Map props = MapUtil.mapIt("hibernate.connection.url","jdbc:hsqldb:mem:testdb");
        SnowTestSupport.initWebApplication("src/main/webapp",props);
    }

    @Test
    public void quickHSQLTest() throws SQLException {
        HibernateSessionInViewHandler inView = appInjector.getInstance(HibernateSessionInViewHandler.class);
        inView.openSessionInView();
        HibernateDaoHelper daoHelper = appInjector.getInstance(HibernateDaoHelper.class);
        User user = new User();
        user.setFirstName("Mike");
        user.setLastName("Doe");
        user = daoHelper.save(user);

        user = new User();
        user.setFirstName("Alice");
        user.setLastName("Donavan");
        user = daoHelper.save(user);

        Item item = new Item();
        item.setUrl("http://britesnow.com/brite");
        item.setNote("Lightweight brite.js");
        item.setUser_id(user.getId());
        daoHelper.save(item);

        inView.afterActionProcessing();
        daoHelper.flushAndClear();

        List list = daoHelper.find(0, 10, "from Item where user_id = '" + user.getId() + "'");
        System.out.println("SampleTests.quickHSQLTest: item count for new user " + list.size());
        
        inView.closeSessionInView();

    }

    //@AfterClass
    public static void after() {

        try {
            HibernateSessionInViewHandler inView = appInjector.getInstance(HibernateSessionInViewHandler.class);
            inView.openSessionInView();
            HibernateDaoHelper daoHelper = appInjector.getInstance(HibernateDaoHelper.class);
            daoHelper.getSession().doWork(new Work() {
                public void execute(Connection con) throws SQLException {
                    con.prepareStatement("shutdown compact").execute();
                }
            });
            inView.closeSessionInView();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
