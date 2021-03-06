package com.luv2code.hibernate.demo1;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourse {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass((InstructorDetail.class))
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        //create session
        Session session = factory.getCurrentSession();

        try {
            //start transaction
            session.beginTransaction();
            //get course from db
            int courseId =10;
            Course tempCourse = session.get(Course.class,courseId);

            //delete course
            System.out.println("Deleting Course :"+tempCourse);
            session.delete(tempCourse);
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done");
        }
        finally {{
            session.close();
            factory.close();
        }
        }
    }
}

