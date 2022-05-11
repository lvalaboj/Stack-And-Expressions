import com.sun.source.tree.Tree;

/**
 * CS 251: Data Structures and Algorithms
 * Project 1
 * <p>
 * TODO: Complete Expression.
 *
 * @author , TODO: add your name here
 * @username , TODO: add your Purdue username here
 * @sources TODO: list your sources here
 */


public class Expression {

    /**
     * The expression to validate and evaluate
     */
    private final String expression;
    /**
     * Used for testing please do not modify
     */
    StringBuilder treeString;

    /**
     * Constructor to initialize the expression
     *
     * @param expression - the expression to evaluate
     */
    public Expression(String expression) {
        this.expression = expression;
        treeString = null;
    }

    /**
     * Checks whether the expression is valid or not
     *
     * @return true if the expression is valid
     */
    public boolean isValid() {
        MyStack operators = new MyStack();
        MyStack operand = new MyStack();
        char[] ch_expression = expression.toCharArray();
        for (int i = 0; i < ch_expression.length; i++) {
            if (Character.isLowerCase(ch_expression[i])) {
                operand.push(ch_expression[i]);
            }
            else if (ch_expression[i] == ')') {
                while (true) {
                    char var = 0;
                    try {
                        var = (char) operators.pop();
                        if (var == '(') {
                            break;
                        }
                        operand.pop();
                    } catch (EmptyStackException e) {
                        return false;
                    }

                }
            }
            else if (ch_expression[i] != ' ') {
                if (ch_expression[i] == '+' || ch_expression[i] == '-' || ch_expression[i] == '*'|| ch_expression[i] == '/' || ch_expression[i] == '%'|| ch_expression[i] == '(') {
                    operators.push(ch_expression[i]);
                }
            }
        }
        while (operators.isEmpty() == false) {
            try {
                operators.pop();
                operand.pop();
            } catch (EmptyStackException e) {
                return false;
            }
        }

        if (operators.isEmpty() && operand.getSize() == 1) {
            return true;
        }
        return false;
    }

    /**
     * Makes an expression tree of the expression
     *
     * @return the root of the expression tree
     */
    public TreeNode makeTree() {
        MyStack operators = new MyStack();
        MyStack operand = new MyStack();
        char[] ch_expression = expression.toCharArray();
        for (int i = 0; i < ch_expression.length; i++) {
            if (Character.isLowerCase(ch_expression[i])) {
                TreeNode element = new TreeNode();
                element.value = ch_expression[i];
                operand.push(element);

            } else if (ch_expression[i] == ')') {
                while (true) {
                    TreeNode var = null;
                    try {
                        var = (TreeNode) operators.pop();
                        if (var.value == '(') {
                            break;
                        }
                        var.right = (TreeNode) operand.pop();
                        TreeNode new_element = (TreeNode) operand.pop();
                        var.left = new_element;
                        operand.push(var);
                    } catch (EmptyStackException e) {
                        e.printStackTrace();
                    }

                }
            } else if (ch_expression[i] != ' ') {
                if (ch_expression[i] == '+' || ch_expression[i] == '-' || ch_expression[i] == '*'|| ch_expression[i] == '/' || ch_expression[i] == '%' || ch_expression[i] == '(') {
                    TreeNode elements = new TreeNode();
                    elements.value = ch_expression[i];
                    operators.push(elements);
                }
            }

        }
        while (operators.isEmpty() == false) {
            try {
                TreeNode oper = (TreeNode) operators.pop();
                oper.right = (TreeNode) operand.pop();
                TreeNode new_element = (TreeNode) operand.pop();
                oper.left = new_element;
                operand.push(oper);
            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
        }

        TreeNode root = null;
        try {
            root = (TreeNode) operand.pop();
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
        return root;
    }

    /**
     * Evaluate the expression tree
     *
     * @param root   of the expression tree
     * @param values of all the variables the values is an int array of size 26.
     *               values[0] represent the value of ‘a’ and values[1] represent the value of ‘b’ and so on
     * @return the value of the evaluated expression
     */
    public int evaluate(TreeNode root, int[] values) {
        int solution = -1;
        int result_l = -1;
        int result_r = -1;
        int yes_oper = 0;
        char operation = 0;
        if (root != null) {
            if (root.left != null) {
                result_l = evaluate(root.left, values);
            }
            if (root.right != null) {
                result_r = evaluate(root.right, values);
            }

            if (Character.isLowerCase(root.value)) {
                int var_index = root.value - 'a';
                return values[var_index];
            } else {
                yes_oper = 1;
                operation = root.value;
            }
            if (yes_oper == 1 && result_l != -1 && result_r != -1) {
                yes_oper = 0;
                if (operation == '+') {
                    solution = result_l + result_r;
                }
                if (operation == '*') {
                    solution = result_l * result_r;
                }
                if (operation == '-') {
                    solution = result_l - result_r;
                }
                if (operation == '%') {
                    solution = result_l % result_r;
                }
                if (operation == '/') {
                    solution = result_l / result_r;
                }
                result_l = -1;
                result_r = -1;
            }
        }
        return solution;
    }




    /**
     * DO NOT MODIFY
     * Used to print the tree and for testing
     * source = https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java/42449385#42449385
     *
     * @param root
     * @return
     */
    public String print(TreeNode root) {
        treeString = new StringBuilder();
        print("", root, false);
        return treeString.toString();
    }

    /**
     * DO NOT MODIFY
     * Used to print the tree and for testing
     * source = https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java/42449385#42449385
     *
     * @param prefix
     * @param n
     * @param isLeft
     */
    public void print(String prefix, TreeNode n, boolean isLeft) {
        if (n != null) {
            treeString.append(prefix + (isLeft ? "|-- " : "\\-- ") + n.value + "\n");
            print(prefix + (isLeft ? "|   " : "    "), n.left, true);
            print(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }


    /* /**
     * Main Can be used for manual testing
     *
     * @param args
     */
 /*public static void main(String[] args) {
      Expression expression = new Expression("b + (a + b * z)");
       System.out.println(expression.isValid());
       TreeNode root = expression.makeTree();
      System.out.println(expression.print(root));
       int[] chars = new int[26];
      for (int i = 0; i < 26; i++) {
          chars[i] = i + i;
      }
      System.out.println(chars[1] + "h");

      System.out.println(expression.evaluate(root, chars));

   }*/


}
