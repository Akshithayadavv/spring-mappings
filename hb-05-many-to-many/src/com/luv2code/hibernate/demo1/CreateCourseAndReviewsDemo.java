package com.luv2code.hibernate.demo1;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//import javax.security.auth.login.Configuration;

public class CreateCourseAndReviewsDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        //Create session
        Session session=factory.getCurrentSession();

        try{

            //start a transaction
            session.beginTransaction();

            //create a course
            Course tempCourse=new Course("Pacman - How to score one million points");

            //add some reviews
            tempCourse.addReview(new Review("Great course... love it!"));
            tempCourse.addReview(new Review("GCool course job well done!"));
            tempCourse.addReview(new Review("what a dumb course, you are an idiot"));

            //save the course.. and leverage the cascade all
            System.out.println("Saving the course");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());
            session.save(tempCourse);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            {
                session.close();
                factory.close();
            }
        }
    }
}
