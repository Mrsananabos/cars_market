package ru.job4j.Bank;


public class User implements Comparable<User>{

   private String name;
   private String passport;

   public User(String name, String passport)  {
       this.name = name;
       this.passport = passport;
   }

   public String getName() { return this.name;}


   public String getPassport() {
       return this.passport;
   }

    @Override
    public String toString() {
        return "User{" + "name: " + name + ", passport: " + passport + "}";
    }

    @Override
    public int hashCode() {
        return this.passport.hashCode();
    }




    @Override
    public boolean equals(java.lang.Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof User))
            return false;
        User user = (User) obj;
        return (user.getName().equals(this.getName()))
                && (user.getPassport().equals(this.getPassport()));
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }
}