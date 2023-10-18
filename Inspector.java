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

        Class<?>objClass = obj.getClass();

        System.out.println("\n");
        System.out.println("---- PRINTING FIELD VALUES ---- \n");
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


        for(Field f : objClass.getDeclaredFields()) {
            f.setAccessible(true);
            System.out.println("Field Name: " + f.getName());
            System.out.println("Field Type: " + f.getType().getName());
            System.out.println("Field Modifier: " + Modifier.toString(f.getModifiers()));
        }

        System.out.println("---- PRINT CONSTRUCTOR AND CONSTRUCTOR INFORMATION ---- \n \n");

        for(Constructor<?> constructor : objClass.getDeclaredConstructors()) {
            System.out.println("Constructor Name: " + constructor.getName());
            System.out.println("Modifiers: " + Modifier.toString(constructor.getModifiers()));
            System.out.println("Parameter Types: " + Arrays.toString(constructor.getParameterTypes()));
        }
        System.out.println("\n \n");
        System.out.println("---- END OF PRINTING CONSTRUCTOR AND CONSTRUCTOR INFORMATION ----");




        System.out.println("---- PRINT INTERFACES ---- \n \n");
        Class[] interfaces = objClass.getInterfaces();

        for(int i = 0; i < interfaces.length; i++) {
            String interfaceName = interfaces[i].getName();
            System.out.println("Interface Name: " + interfaceName);
        }
        System.out.println("\n \n");
        System.out.println("---- END OF PRINTING ALL INTERFACES ----");

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