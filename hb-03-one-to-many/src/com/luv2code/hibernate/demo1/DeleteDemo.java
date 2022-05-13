package com.luv2code.hibernate.demo1;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//import javax.security.auth.login.Configuration;

public class DeleteDemo {
    public static void main(String[] args){

        //create session factory
        SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //Create session
        Session session=factory.getCurrentSession();

        try{



            //start a transaction
            session.beginTransaction();

            //get instructor by primary key
            int theId=1;
            Instructor tempInstructor=session.get(Instructor.class,theId);
            System.out.println("Found instructor: " +tempInstructor);

            //delete instructor
            if(tempInstructor!=null){
                System.out.println("Deleting: " + tempInstructor);

                //Will also delete associated "details" object because of cascade
                session.delete(tempInstructor);
            }

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
