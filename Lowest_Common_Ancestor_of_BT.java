public class Lowest_Common_Ancestor_of_BT {

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
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null)
            return root;

        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode tr = new TreeNode(3);
        buildTree(tr);
        TreeNode ans = lowestCommonAncestor(tr , tr.left , tr.right);
        System.out.println(ans.data);
    }
}
