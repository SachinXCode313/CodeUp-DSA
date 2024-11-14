import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    public Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        }
        return node;
    }

    public void printTree() {
        if (root == null) return;

        int height = getHeight(root);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int maxWidth = (int) Math.pow(2, height + 1) - 1;

        for (int level = 0; level <= height; level++) {
            int levelSize = queue.size();
            int nodeSpacing = maxWidth / (int) Math.pow(2, level + 1);

            printSpaces(nodeSpacing);

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                if (currentNode != null) {
                    System.out.print(currentNode.data);
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);
                } else {
                    System.out.print(" ");
                    queue.add(null);
                    queue.add(null);
                }
                printSpaces(nodeSpacing * 2);
            }
            System.out.println();

            if (level < height) {
                printBranches(levelSize, nodeSpacing);
            }
        }
    }

    private void printBranches(int levelSize, int nodeSpacing) {
        int branchSpacing = nodeSpacing;
        for (int i = 0; i < levelSize; i++) {
            printSpaces(branchSpacing - 1);
            System.out.print("/");
            printSpaces(branchSpacing * 2 - 1);
            System.out.print("\\");
            printSpaces(branchSpacing - 1);
        }
        System.out.println();
    }

    private int getHeight(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("Enter the number of nodes you want to insert into the BST:");
        int n = scanner.nextInt();

        System.out.println("Enter the values to insert into the BST:");
        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            bst.root = bst.insert(bst.root, value);
        }

        System.out.println("Binary Search Tree Structure:");
        bst.printTree();

        System.out.println("Do you want to change the root of the tree? (yes/no):");
        String response = scanner.next();

        if (response.equalsIgnoreCase("yes")) {
            System.out.println("Enter the new root value:");
            int newRootValue = scanner.nextInt();

            bst.root.data = newRootValue;

            System.out.println("Binary Search Tree Structure after changing root:");
            bst.printTree();
        }

        scanner.close();
    }
}
