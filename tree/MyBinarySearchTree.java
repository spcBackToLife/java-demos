public class MyBinarySearchTree<AnyType extends Compareable<? super AnyType>> {
  private static class BinaryNode<AnyType> {
    BinaryNode(AnyType element) {
      this(element, null, null);
    }
    BinaryNode(AnyType element, AnyType left, AnyType right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }
    AnyType element;
    BinaryNode<AnyType> left;
    BinaryNode<AnyType> right;
  }
  private BinaryNode<AnyType> root;
  public MyBinarySearchTree() {
    this.makeEmpty();
  }

  public void makeEmpty() {
    this.root = null;
  }
  public boolean isEmpty() {
    return this.root == null;
  }

  public AnyType findMin() {
    return findMin(this.root).element;
  }

  private BinaryNode<AnyType> findMin(BinaryNode<AnyType> node) {
    if (node == null) {
      return null;
    }
    if (node.left == null) {
      return node;
    }
    return findMin(node.left);
  }

  public AnyType insert(AnyType x) {
    return this.insert(x, this.root).element;
  }

  public BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> node) {
    if (node == null) {
      node = new BinaryNode<AnyType>(x);
      return node;
    }
    int compareResult = x.compareTo(node.element);
    if (compareResult < 0) {
      node.left = this.insert(x, node.left);
    } else if (compareResult > 0) {
      node.right = this.insert(x, node.right);
    } else {
      // do nothing
    }
    return node;
  }

  public AnyType remove(AnyType x) {
    return this.remove(x, this.root);
  }

  private AnyType remove(AnyType x, BinaryNode<AnyType> node) {
    // 树为空，返回null
    if (this.isEmpty()) {
      return null;
    }
    int compareResult = x.compareTo(node.element);
    if (compareResult > 0) {
      // 在node右侧
      return this.remove(x, node.right);
    } else if (compareResult < 0) {
      return this.remove(x, node.left);
    } else if(node.left != null && node.right != null) {
      BinaryNode<AnyType> minNode = this.findMin(node.right);
      node.element = minNode.element;
      this.remove(minNode.element, node.right);
    } else {
      node = node.left != null ? node.left : node.right;
    }
  }

  public AnyType findMax() {
    if (node == null) {
      return null;
    }

    return this.findMax(this.root).element;
  }
  private BinaryNode<AnyType> findMax(BinaryNode<AnyType> node) {
    while(node.right != null) {
      node = node.right;
    }
    return node;
  }

  public boolean contains(AnyType x) {
    return contains(x, this.root);
  }

  private boolean contains(AnyType x, BinaryNode<AnyType> root) {
    if (root == null) {
      return false;
    }
    int compareResult = x.compareTo(root.element);
    if (compareResult == 0) {
      return true;
    } else if (compareResult > 0) {
      return contains(x, root.right);
    } else {
      return contains(x, root.left);
    }
  }
}