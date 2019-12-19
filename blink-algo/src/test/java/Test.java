import java.util.PriorityQueue;

public class Test {


    public static void main(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(2);
        q.offer(5);
        q.offer(8);
        q.offer(1);
        q.offer(2);
        q.offer(7);

        System.out.println(q.peek());
        System.out.println(q.size());
    }
}
