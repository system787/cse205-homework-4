package h05_3;//**************************************************************************************************
// CLASS: EmptyTreeException
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
//
// (c) 2018 Kevin R Burger
//**************************************************************************************************
import java.io.Serializable;

/**
 * EmptyTreeException is thrown by certain BinaryTree methods which are invoked on an empty tree.
 */
public class EmptyTreeException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
}
