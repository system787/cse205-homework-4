The BinaryTree, DList, Stack, and Queue classes in this zip archive are generic classes,
i.e., they require a type parameter to be specified when an object of the
class is instantiated. For example, to instantiate a DList where each element
stores a Shape:

    DList<Shape> listOfShapes = new DList<>();

or to create a DList where every element is a Student:

    DList<Student> listOfStudents = new DList<>();

Generic classes are quite powerful because they allow us to write the code for
a data structure class independent of the data type of the elements that will be
stored in the data structure. Before Java had generics, one would have had to
create two DList classes:

    DListShape listOfShapes = new DListShape();
    DListStudent listOfStudents = new DListStudent();

where the code in the two classes would be identical except for the data type
of the elements.
