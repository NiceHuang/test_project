package cn.hnx;

/**
 * Created by viruser on 2019/7/30.
 */
public class Test {

    public static void main(String[] args) {
        Node node = new Node(10);
        Node node1 = new Node(9);
        Node node2 = new Node(8);
        Node node3 = new Node(7);
        Node node4 = new Node(5);
        Node node5 = new Node(4);
        Node node6 = new Node(3);
        node.setLeft(node1);
        node.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
//        System.out.print(getTreeHeight(node));
//        getTreeMin(node);

        TestInterface.test();

    }
    public static int getTreeHeight(Node root) {
        if (root == null){
            return 0;
        }
        return Math.max(getTreeHeight(root.left), getTreeHeight(root.right)) + 1;
    }

    public static void getTreeMin(Node root) {

        Node left = root.left;
        Node rigth = root.right;
        if(left != null && rigth != null){
            System.out.println(Math.min(root.left.getVal(), root.right.getVal()));
            getTreeMin(root.left);
            getTreeMin(root.right);
        }
    }

    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(){}

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
