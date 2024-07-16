public class Tree_Traversal {


    static class Node {
        int data;
        Node left;
        Node right;
        int last = 0 ;

        public Node(int item) {
            this.data = item;
            this.left = null;
            this.right = null;
        }
    }

    public static void buildTree(Node root) {
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
    }

    public static void pre_order(Node t){
        if(t == null){
            return;
        }

        System.out.print(t.data + " ");
        pre_order(t.left);
        pre_order(t.right);
    }

    public static void in_order(Node t){
        if(t == null){
            return;
        }

        in_order(t.left);
        System.out.print(t.data + " ");
        in_order(t.right);
    }

    public static void post_order(Node t){
        if(t == null){
            return;
        }

        post_order(t.left);
        post_order(t.right);
        System.out.print(t.data + " ");
    }
    public static void main(String[] args) {
        Node tr = new Node(1);
        buildTree(tr);
        System.out.print("Pre Order Traversal:   ");
        pre_order(tr);
        System.out.println();
        System.out.print("In Order Traversal:    ");
        in_order(tr);
        System.out.println();
        System.out.print("Post Order Traversal:  ");
        post_order(tr);
    }
}
