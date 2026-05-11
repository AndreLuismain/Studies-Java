class A {
    protected int m;
    protected int n;
    public A(int mIn, int nIn) {
        m = mIn;
        n = nIn;
    }
    public void m1() {
        m = m + n;
    }

    //Added toString() to match the required output format
    @Override
    public String toString() {
        return "A = (" + m + ", " + n + ")";
    }
    //Implementation of equals()
    @Override
    public boolean equals(Object obj) {
        //Check if comparing to itself
        if (this == obj) {
            return true;
        }
        //Check for null and ensure strict class equality
        //(Using getClass() instead of instanceof ensures A(3,2) != B(3,2))
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        //Cast safely and compare field values
        A other = (A) obj;
        return this.m == other.m && this.n == other.n;
    }
}

public class B extends A {
    //Constructor matching the superclass
    public B(int mIn, int nIn) {
        super(mIn, nIn);
    }

    //Overrides m1() to subtract instead of add
    @Override
    public void m1() {
        m = m - n;
    }

    //Overrides toString() to output "B = ..." instead of "A = ..."
    @Override
    public String toString() {
        return "B = (" + m + ", " + n + ")";
    }

    //Implementation of equals() for B
    @Override
    public boolean equals(Object obj) {
        //Since super.equals() already performs strict getClass() checking
        //and compares m and n, we can delegate directly to it.
        return super.equals(obj);
    }

    public static void main(String[] args) {
        //Overriding and Output
        System.out.println("Overriding Output");
        A a = new A(1, 2);
        A b = new B(1, 2);
        System.out.println(a + " " + b);
        a.m1();
        b.m1();
        System.out.println(a + " " + b);

        //Equals Verification
        System.out.println("\nEquals Output");
        A a1 = new A(3, 2);
        A a2 = new A(3, 2);
        A b1 = new B(3, 2);
        A b2 = new B(3, 2);
        A c1 = new B(3, 4);
        //Outputs
        System.out.println("a1.equals(a2): " + a1.equals(a2));     // true
        System.out.println("a1.equals(b1): " + a1.equals(b1));     // false
        System.out.println("b1.equals(b2): " + b1.equals(b2));     // true
        System.out.println("b1.equals(c1): " + b1.equals(c1));     // false
        System.out.println("a1.equals(null): " + a1.equals(null)); // false
    }
}