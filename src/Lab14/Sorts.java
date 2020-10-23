package Lab14;

import Lab13.Student;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorts {
    Comparator<Student> comparator = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return (-1) * Double.compare(o1.getiDNumber(),o2.getiDNumber());
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj;
        }
    };

    public void quickSort(ArrayList<Student> array, int low, int high) {
        if (array.size() == 0) return;
        if (low >= high) return;
        int middle = low + (high - low) / 2;
        Student supportItem =new Student();
        supportItem = array.get(middle);
        Student temp=new Student();
        int i = low, j = high;
        while (i <= j) {
            while (this.comparator.compare(array.get(i),supportItem) > 0)
                i++;
            while (this.comparator.compare(array.get(j),supportItem) < 0)
                j--;
            if (i <= j) {
                temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, low, j);
        if (high > i)
            quickSort(array, i, high);
    }

    public ArrayList<Student> mergeSort(ArrayList<Student> students){
        if (students == null)
            return null;
        if (students.size() < 2)
            return students;
        ArrayList<Student> left = new ArrayList<Student>();
        ArrayList<Student> right = new ArrayList<Student>();
        for (int i = 0; i < students.size() / 2; i++)
            left.add(students.get(i));
        for (int i = students.size()/2; i < students.size(); i++)
            right.add(students.get(i));
        left = mergeSort(left);
        right = mergeSort(right);
        return mergeArray(left, right);
    }

    private ArrayList<Student> mergeArray(ArrayList<Student> left, ArrayList<Student> right) {
        ArrayList<Student> merged = new ArrayList<Student>();
        for (int i = 0; i < left.size()+right.size(); i++)
            merged.add(new Student());
        int lengthLeft = left.size();
        int lengthRight = right.size();
        while (lengthLeft > 0 && lengthRight > 0){
            if (this.comparator.compare(left.get(left.size() - lengthLeft),right.get(right.size() - lengthRight)) > 0){
                merged.set(merged.size() - lengthLeft - lengthRight, left.get(left.size() - lengthLeft));
                lengthLeft--;
            }
            else{
                merged.set(merged.size() - lengthLeft-lengthRight, right.get(right.size() - lengthRight));
                lengthRight--;
            }
        }
        while (lengthLeft > 0){
            merged.set(merged.size() - lengthLeft, left.get(left.size()-lengthLeft));
            lengthLeft--;
        }
        while (lengthRight > 0){
            merged.set(merged.size() - lengthRight, right.get(right.size()-lengthRight));
            lengthRight--;
        }
//        ArrayList<Student> students = new ArrayList<>();
//        for (int i = merged.size()/2; i < merged.size(); i++)
//            students
        return merged;
    }
}