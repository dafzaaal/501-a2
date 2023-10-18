import java.util.*;
import java.lang.reflect.*;

class Inspector {
    public void inspect(Object obj, boolean recursive) {

        // Get name of declaring class using getDeclaringClass
        System.out.println(obj.getClass().getDeclaringClass());

        // Get the name of the immediate superclass
        
        printSuperClass(obj);
        

        // Call helper method to print all interfaces used by a class
        printInterfaces(obj);

        // Helper method to print all methods the class decarles as well as...
        /* 
            1. Exceptions thrown
            2. Parameter types
            3. Return type
            4. The modifiers
         */

         printMethodsAndMethodInfo(obj);

         /*
            Helper method to print out constructor info a class declares as well as...
            
                1. the parameter types
                2. the modifiers
          */

          printConstructorAndInfo(obj);

          /*
              Helper method which prints out all fields as well as...

                1. their type
                2. their modifier

           */

          printFields(obj);





          /*
            Helper method which gets the current value of each field, if the field is a ref
            to another object AND the recursive flag is set to FALSE, just print the reference value
          */

          

    }


    public void printFieldValues(Object obj, boolean recursive) {
        System.out.println("\n");
        System.out.println("---- PRINTING FIELD VALUES ---- \n");
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        for(Field f : fields) {
            f.setAccessible(true);
            try {
                Object fieldValue = f.get(obj);
                if(fieldValue != null && !(f.getType().isPrimitive())) {
                    if(recursive == true) {
                        //If its an object do some recursive stuff IF the flag is true
                    }
                    else {
                        String referenceValue = fieldValue.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(fieldValue));
                        System.out.println("Field Name: " + f.getName() + " - Field Value (Reference): " + referenceValue);
                    }
                }
                else {
                    System.out.println("Field Name: " + f.getName() + "\n");
                    System.out.println("Field Value: " + fieldValue + "\n");
                }
            }
            catch(IllegalAccessException e) {
                System.out.println("Error:  " + e);
            }
        }

    }



    public void printFields(Object obj) {
        Class<?> objClass = obj.getClass();
        for(Field f : objClass.getDeclaredFields()) {
            f.setAccessible(true);
            System.out.println("Field Name: " + f.getName());
            System.out.println("Field Type: " + f.getType().getName());
            System.out.println("Field Modifier: " + Modifier.toString(f.getModifiers()));
        }
    }
    


    public void printConstructorAndInfo(Object obj) {
        Class<?> objClass = obj.getClass();
        System.out.println("---- PRINT CONSTRUCTOR AND CONSTRUCTOR INFORMATION ---- \n \n");

        for(Constructor<?> constructor : objClass.getDeclaredConstructors()) {
            System.out.println("Constructor Name: " + constructor.getName());
            System.out.println("Modifiers: " + Modifier.toString(constructor.getModifiers()));
            System.out.println("Parameter Types: " + Arrays.toString(constructor.getParameterTypes()));
        }
        System.out.println("\n \n");
        System.out.println("---- END OF PRINTING CONSTRUCTOR AND CONSTRUCTOR INFORMATION ----");
    }


    public void printMethodsAndMethodInfo(Object obj) {
        Class<?> objClass = obj.getClass();
        System.out.println("---- PRINT ALL METHODS ---- \n \n");
        for (Method method : objClass.getDeclaredMethods()) {
            System.out.println(method.getName());
            System.out.println("Exceptions Thrown: " + method.getExceptionTypes());
            System.out.println("Parameter Types: " + method.getParameterTypes());
            System.out.println("Return Types: " + method.getReturnType());
            System.out.println("Modifiers: " + method.getModifiers());
        }
        System.out.println("\n \n");
        System.out.println("---- END OF PRINTING ALL METHODS ----");

    }


    public void printSuperClass(Object obj) {
        System.out.println("---- PRINTING SUPER CLASS ---- \n \n");
        Class<?> superClass = obj.getClass().getSuperclass();
        if(superClass != null) {
            System.out.println("Superclass Name:" + superClass.getName());
        }
        else {
            System.out.println("No Superclass Found.");
        }
        System.out.println("\n \n");
        System.out.println("---- END OF PRINTING SUPERCLASS ----");
    }

    // helper method that prints out all interfaces of a given object
    public void printInterfaces(Object obj) {
        Class<?> objClass = obj.getClass();
        System.out.println("---- PRINT INTERFACES ---- \n \n");
        Class[] interfaces = objClass.getInterfaces();

        for(int i = 0; i < interfaces.length; i++) {
            String interfaceName = interfaces[i].getName();
            System.out.println("Interface Name: " + interfaceName);
        }
        System.out.println("\n \n");
        System.out.println("---- END OF PRINTING ALL INTERFACES ----");
    }
}