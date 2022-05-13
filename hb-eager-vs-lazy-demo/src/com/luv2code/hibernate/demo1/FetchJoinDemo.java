package com.luv2code.hibernate.demo1;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

//import javax.security.auth.login.Configuration;

public class FetchJoinDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //Create session
        Session session = factory.getCurrentSession();

        try {

            //start a transaction
            session.beginTransaction();

            //option2: Hibernate query with HQL

            //get the instructor from db
            int theId = 1;
            //Instructor tempInstructor=session.get(Instructor.class,theId);

            Query<Instructor> query = session.createQuery(" select i from Instructor i "
                            +  " JOIN FETCH i.courses "
                            +  " where i.id=:theInstructorId ",
                    Instructor.class);

            //set parameter on query
            query.setParameter("theInstructorId", theId);

            //execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            System.out.println("luv2code: Instructor: " + tempInstructor);

            //get course from instructor
            // System.out.println("luv2code: Courses: " + tempInstructor.getCourses());

            //commit transaction
            session.getTransaction().commit();

            //close the session
            session.close();

            System.out.println("luv2code: The session is closed\n");

            //option1: call getter method while session is open


            //get course from instructor
            System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
            System.out.println("Done!");
        } finally {
            {
                session.close();
                factory.close();
            }
        }
    }
}