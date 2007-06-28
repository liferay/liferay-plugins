/*
 * JBoss, Home of Professional Open Source
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

//$Id: RegisterAction.java,v 1.19 2007/02/25 19:54:56 gavin Exp $
package org.jboss.seam.example.registration;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;

@Stateless
@Name("register")
public class RegisterAction implements Register
{

   @In
   private User user;
   
   @PersistenceContext
   private EntityManager em;
   
   @Logger
   private static Log log;
   
   public String register()
   {
      List existing = em.createQuery("select u.username from User u where u.username=#{user.username}")
         .getResultList();

      if ( existing.size()==0 )
      {
         em.persist(user);
         log.info("Registered new user #{user.username}");
         return "/registered.jspx";
      }
      else
      {
         FacesMessages.instance().add("User #{user.username} already exists");
         return null;
      }
   }

}
