/*
 * JBoss, Home of Professional Open Source
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */

//$Id: User.java,v 1.10 2006/10/25 20:17:46 gavin Exp $
package org.jboss.seam.example.registration;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("user")
@Scope(SESSION)
@Table(name="users")
public class User implements Serializable
{
   private static final long serialVersionUID = 1881413500711441951L;
   
   private String username;
   private String password;
   private String name;
   
   public User(String name, String password, String username)
   {
      this.name = name;
      this.password = password;
      this.username = username;
   }
   
   public User() {}
   
   @NotNull
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
   
   @NotNull @Length(min=5, max=15)
   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }
   
   @Id @NotNull @Length(min=5, max=15)
   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }
   
   @Override
   public String toString() 
   {
      return "User(" + username + ")";
   }
}
