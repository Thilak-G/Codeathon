import java.util.*;

class TreeNode {
    boolean locked;
    int lockedBy;
    int descendantLockedCount;
    TreeNode parent;
    List<TreeNode> children;

    TreeNode() {
        this.locked = false;
        this.lockedBy = -1;
        this.descendantLockedCount = 0;
        this.parent = null;
        this.children = new ArrayList<>();
    }
}


public class Tree_Of_Space {
    private TreeNode[] nodes;
    private int M;

    public Tree_Of_Space(int M, int n) {
        this.M = M;
        nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new TreeNode();
        }
        buildTree();
    }

    private void buildTree() {
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 1; j <= M; j++) {
                int childIndex = M * i + j;
                if (childIndex < nodes.length) {
                    nodes[i].children.add(nodes[childIndex]);
                    nodes[childIndex].parent = nodes[i];
                }
            }
        }
    }

    public boolean lock(int v, int id) {
        TreeNode node = nodes[v];
        if (canLockOrUnlock(node)) {
            node.locked = true;
            node.lockedBy = id;
            updateAncestorsOnLock(node);
            return true;
        }
        return false;
    }

    public boolean unlock(int v, int id) {
        TreeNode node = nodes[v];
        if (node.locked && node.lockedBy == id) {
            node.locked = false;
            node.lockedBy = -1;
            updateAncestorsOnUnlock(node);
            return true;
        }
        return false;
    }

    public boolean upgrade(int v, int id) {
        TreeNode node = nodes[v];
        if (!node.locked && canLockOrUnlock(node) && hasLockedDescendants(node, id)) {
            unlockDescendants(node);
            node.locked = true;
            node.lockedBy = id;
            updateAncestorsOnLock(node);
            return true;
        }
        return false;
    }

    private boolean canLockOrUnlock(TreeNode node) {
        while (node != null) {
            if (node.locked) {
                return false;
            }
            node = node.parent;
        }
        return true;
    }

    private boolean hasLockedDescendants(TreeNode node, int id) {
        if (node.locked && node.lockedBy != id) {
            return false;
        }
        for (TreeNode child : node.children) {
            if (hasLockedDescendants(child, id)) {
                return true;
            }
        }
        return node.descendantLockedCount > 0;
    }

    private void unlockDescendants(TreeNode node) {
        for (TreeNode child : node.children) {
            if (child.locked) {
                child.locked = false;
                child.lockedBy = -1;
            }
            unlockDescendants(child);
        }
    }

    private void updateAncestorsOnLock(TreeNode node) {
        while (node.parent != null) {
            node.parent.descendantLockedCount++;
            node = node.parent;
        }
    }

    private void updateAncestorsOnUnlock(TreeNode node) {
        while (node.parent != null) {
            node.parent.descendantLockedCount--;
            node = node.parent;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int n = scanner.nextInt();
        Tree_Of_Space tree = new Tree_Of_Space(M, n);

        int Q = scanner.nextInt();
        for (int i = 0; i < Q; i++) {
            String queryType = scanner.next();
            int v = scanner.nextInt();
            int id = scanner.nextInt();
            boolean result = false;
            switch (queryType) {
                case "lock":
                    result = tree.lock(v, id);
                    break;
                case "unlock":
                    result = tree.unlock(v, id);
                    break;
                case "upgrade":
                    result = tree.upgrade(v, id);
                    break;
            }
            System.out.println(result);
        }

        scanner.close();
    }
}