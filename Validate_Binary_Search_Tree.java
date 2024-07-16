public class Validate_Binary_Search_Tree {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        int last = 0 ;

        public TreeNode(int item) {
            this.data = item;
            this.left = null;
            this.right = null;
        }
    }

    public static void buildTree(TreeNode root) {
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
    }
    
    public static boolean check(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.data <= min || node.data >= max) {
            return false;
        }
        return check(node.left, min, node.data) && check(node.right, node.data, max);
    }

    public static boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static void main(String[] args) {
        TreeNode tr = new TreeNode(2);
        buildTree(tr);
        System.out.println(isValidBST(tr));
    }
}
