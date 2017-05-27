package Java;

/**
 * Created by User on 5/27/2017.
 */
public class OuterClass {

    void display() {
        int x = 5;
        class Local {
            int x = 20;

            void print() {
                System.out.print(this.x);
            }
        }
        new Local().print();
    }
    public static void main(String []args){
        OuterClass outerClass = new OuterClass();
        outerClass.display();
        
    }
}
