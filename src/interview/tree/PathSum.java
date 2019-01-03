package interview.tree;

public class PathSum {

    public static void main(String[] args) {
        /**
         *             4
         *           /   \
         *          2     7
         *         / \   / \
         *        1  3  5  8
         *              \
         *               6
         */

        int[] path = new int[256];
        TreeNode treeNode = TreeNode.dummyTree();

        System.out.println("******* Calculate path for the given sum *******");
        calcPaths(treeNode, path, 0, 19, 0);

        System.out.println("******* populate path *******");
        populatePath(treeNode,path,0);
    }

    public static void calcPaths(TreeNode node, int[] path, int pathLen, int sum, int actualSum) {
        if (node == null) {
            return;
        }
        path[pathLen] = node.getData();
        pathLen++;
        actualSum = actualSum + node.getData();

        if (node.getLeft() == null && node.getRight() == null) {
            if (sum == actualSum) {
                printPath(path, pathLen);
            }
        } else {
            if (node.getLeft() != null) {
                calcPaths(node.getLeft(), path, pathLen, sum, actualSum);
            }

            if (node.getRight() != null) {
                calcPaths(node.getRight(), path, pathLen, sum, actualSum);
            }
        }
    }

    public static void printPath(int[] path, int pathLen) {
        for (int i = pathLen-1; i >=0; i--) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
    }

    public static void populatePath(TreeNode node, int[] path,int pathLen){
        if(node == null){
            return;
        }

        path[pathLen] = node.data;
        pathLen++;

        if(node.left == null && node.right == null){
            printPath(path,pathLen);
        }else{
            if(node.left != null){
                populatePath(node.left,path,pathLen);
            }
            if(node.right !=null){
                populatePath(node.right,path,pathLen);
            }
        }
    }
}
