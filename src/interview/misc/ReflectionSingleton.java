package interview.misc;

/*
 1. https://dzone.com/articles/prevent-breaking-a-singleton-class-pattern?utm_medium=email&utm_source=topic%20optin
 &utm_campaign=awareness&utm_content=20190907%20prog%20nl&mkt_tok
 =eyJpIjoiWVRreE1qUXdOVFZpWW1VMiIsInQiOiJRXC9HT1pLc2ZBVVNUTk5yRDV0ZWlIT1JzNUs2UGxPN1lYbFFUN1ZmZmlxZTB1KzkxRWFDOXdQUndkQlhjeVZFYjZrRVJwVnFUOUx6Z044V3lcL2JRVGN6U2RSM3NIZ1pnRkZnMWNaY2VVcEJZU2wyQnh2cUdhRmNrbTBCWXFPTmpLIn0%3D
 2. https://www.geeksforgeeks.org/prevent-singleton-pattern-reflection-serialization-cloning/
 */

import java.lang.reflect.Constructor;

class Singleton implements Cloneable {

  private static volatile Singleton instance = null;

  private Singleton() throws IllegalAccessException {
    if (instance != null) {
      throw new IllegalAccessException("Instance has already created");
    }
  }

  public static Singleton getInstance() throws IllegalAccessException {
    if (instance == null) {
      synchronized (Singleton.class) {
        if (instance == null) {
          instance = new Singleton();
        }
      }
    }
    return instance;
  }

  protected Object readResolve() {
    return instance;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException
  {
    return instance;
  }
}

public class ReflectionSingleton {

  public static void main(String[] args) {
    Singleton objOne = null;
    Singleton objTwo = null;
    try {
      objOne = Singleton.getInstance();
      Constructor constructor = Singleton.class.getDeclaredConstructor();
      constructor.setAccessible(true);
      objTwo = (Singleton) constructor.newInstance();
    } catch (Exception ex) {
      System.out.println(ex);
    }
    System.out.println("Hashcode of Object 1 - " + objOne.hashCode());
    System.out.println("Hashcode of Object 2 - " + objTwo.hashCode());
  }
}
