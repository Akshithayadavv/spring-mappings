package com.luv2code.hibernate.demo1;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//import javax.security.auth.login.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //Create session
        Session session=factory.getCurrentSession();

        try{
            /*
           //create the objects
            Instructor tempInstructor= new Instructor("Chad","Darby","darby@luv2code.com");

            InstructorDetail tempInstructorDetail= new InstructorDetail("http://www.luv2code.com/youtube"," Luv 2 code!!");*/


            Instructor tempInstructor= new Instructor("Susan","Public","susan.public@luv2code.com");

            InstructorDetail tempInstructorDetail= new InstructorDetail("http://www.youtube.com","Video games");
            //associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);


            //start a transaction
            session.beginTransaction();

            //save the instructor
            //this will also save the details objects because of CascadeType.all
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

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
