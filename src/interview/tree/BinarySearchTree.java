package interview.tree;

class BinaryNode {
		private BinaryNode leftNode;
		private BinaryNode rightNode;
		private int value;
		
		public BinaryNode(int value) {
			this.value = value;
			this.leftNode = null;
			this.rightNode = null;
			
		}	
		
		public BinaryNode(int value, BinaryNode leftNode, BinaryNode rightNode) {
			this.value = value;
			this.leftNode = leftNode;
			this.rightNode = rightNode;
			
		}		
		
		public BinaryNode getLeftNode() {
			return leftNode;
		}

		public void setLeftNode(BinaryNode leftNode) {
			this.leftNode = leftNode;
		}

		public BinaryNode getRightNode() {
			return rightNode;
		}

		public void setRightNode(BinaryNode rightNode) {
			this.rightNode = rightNode;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}	
		
	}	

class BinaryTree {
	
	private BinaryNode root;	
	
	
	public void add(BinaryNode currentnode) {
		if(root == null) {
			root = currentnode;
			return;
		}
		
		addCurrentnode(root, currentnode);
	}
	
	private void addCurrentnode(BinaryNode rootNode, BinaryNode currentnode) {
		
		if(currentnode.getValue() < rootNode.getValue()) {
			if(rootNode.getLeftNode() == null) {
				rootNode.setLeftNode(currentnode);
			} else {
				addCurrentnode(rootNode.getLeftNode(), currentnode);
			}
		} else {
			if(rootNode.getRightNode() == null) {
				rootNode.setRightNode(currentnode);
			} else {
				addCurrentnode(rootNode.getRightNode(), currentnode);
			}
		}
	}
	
	public int getMaximum () {
		if(root == null) {
			return 0;
		} else {
			BinaryNode headNode = root;
			while(headNode.getRightNode() != null) {
				headNode = headNode.getRightNode();
			}
			return headNode.getValue();
		}
	}
	
	public int getMinimum () {
		if(root == null) {
			return 0;
		} else {
			BinaryNode headNode = root;
			while(headNode.getLeftNode() != null) {
				headNode = headNode.getLeftNode();
			}
			return headNode.getValue();
		}
	}
	
	public int getHeight() {
		System.out.println("Total Nodes:" + getTotalNodes(root));
		return getHeightVal(root);
		
	}
	
	private int getHeightVal(BinaryNode currentHead) {
		if(currentHead == null) {
			return 0;
		}
		
		int left = getHeightVal(currentHead.getLeftNode());
		int right = getHeightVal(currentHead.getRightNode());
		return 1 + Math.max(left, right);
	}
	
	private int getTotalNodes(BinaryNode currentHead) {
		if(currentHead == null) {
			return 0;
		}
		
		return (1 + getTotalNodes(currentHead.getLeftNode()) + getTotalNodes(currentHead.getRightNode()));
	}

	// TODO: Refer Tree Traversals class which is a duplicate of this one.
/*	*//**
	 * Inorder (Left, Root, Right)
	 *//*
	public void printInorderTraverse() {
		printInorder(root);
		System.out.println();
	}
	
	private void printInorder(BinaryNode currentRoot) {
		if(currentRoot == null) {
			return;
		}
		printInorder(currentRoot.getLeftNode());
		System.out.print(" "+currentRoot.getValue());
		printInorder(currentRoot.getRightNode());		
	}
	
	*//**
	 * 	1. Start from the root, let's it is current.
		2. If current is not NULL, push the node on to stack.
		3. Move to left child of current and go to step 2.
		4. If current is NULL, and stack is not empty, pop node from the stack.
		5. Print the node value and change current to right child of current.
		6. Go to step 2. 
	 *//*
	public void inOrderTraverseIterativeWay() {
		BinaryNode currentRoot = root;
		Stack<BinaryNode> nodes = new Stack<BinaryNode>();
		
		while(!nodes.isEmpty() || currentRoot != null) {
			if(currentRoot != null) {
				nodes.push(currentRoot);
				currentRoot = currentRoot.getLeftNode();
			} else {	
				currentRoot = nodes.pop();				
				System.out.print(" " +currentRoot.getValue());
				currentRoot = currentRoot.getRightNode();
			}
		}
		System.out.println();
	}

	*//**
	 * Preorder (Root, Left, Right).
	 *//*
	public void printPreorderTraverse() {
		printPreorder(root);
		System.out.println();
		printPreOderIterative();
		System.out.println();
	}
	
	private void printPreorder(BinaryNode currentRoot) {
		if(currentRoot == null) {
			return;
		}		
		System.out.print(" "+currentRoot.getValue());
		printPreorder(currentRoot.getLeftNode());
		printPreorder(currentRoot.getRightNode());		
	}
	
	*//*
	 * 1. Start from root.
	   2. Print the node.
	   3. Push right child onto to stack.
	   4. Push left child onto to stack.
	   5. Pop node from the stack.
	   6. Repeat Step 2 to 5 till stack is not empty.
	 *//*
	private void printPreOderIterative() {
		Stack<BinaryNode> nodes = new Stack<BinaryNode>();
		BinaryNode rootNode = root;
		nodes.push(rootNode);
		while(!nodes.isEmpty()) {
			rootNode = nodes.pop();
			System.out.print(" "+ rootNode.getValue());
			if(rootNode.getRightNode() != null) {
				nodes.push(rootNode.getRightNode());
			}
			
			if(rootNode.getLeftNode() != null) {
				nodes.push(rootNode.getLeftNode());
			}
		}
	}

	*//**
	 * Postorder (Left, Right, Root).
	 *//*
	public void printPostorderTraverse() {
		printPostorder(root);
		System.out.println();
		postOrderTraversalIterative(root);
		System.out.println();
	}
	
	private void printPostorder(BinaryNode currentRoot) {
		if(currentRoot == null) {
			return;
		}
		printPostorder(currentRoot.getLeftNode());
		printPostorder(currentRoot.getRightNode());		
		System.out.print(" "+currentRoot.getValue());
	}
	
	*//**
	 * 
	 *  1. Start with the root node.
	    2. Push the node onto stack.
		In case 1. When we are moving down the tree :
		3. If left child is present, push the left child onto stack.
		3. If right child is present, push the right child onto stack.
		4. If left and right child are not present, we are at the leaf node and hence print the node.

		In case 2. When we are moving up after visiting left node:
		5. If right child is not present, print the current node.
		6. If right child is present, push that node to stack.

		In case 3. When we are moving up after visiting right child :
		7. Print the node.
		8. Pop the node from top of stack.
		9. Repeat step 2 to 8 till stack is empty.
	 *//*
	public void postOrderTraversalIterative(BinaryNode currentRoot) {
		if(currentRoot == null) {
			return;
		}
		Stack<BinaryNode> nodeColl = new Stack<BinaryNode>();
		nodeColl.push(currentRoot);
		BinaryNode prevNode = null;
		
		while(!nodeColl.isEmpty()) {
			BinaryNode currNode = nodeColl.peek();
			
			// we are traversing down the tree
		    if (prevNode == null || prevNode.getLeftNode() == currNode  || prevNode.getRightNode() == currNode ) {
		      if (currNode.getLeftNode() != null) {
		    	  nodeColl.push(currNode.getLeftNode());
		      } else if (currNode.getRightNode() != null) {
		    	  nodeColl.push(currNode.getRightNode());
		      } else {
		        System.out.print(" "+ currNode.getValue());
		        nodeColl.pop();
		      }
		    } 
		    
		    *//* case 2. We are moving up the tree from left child *//*
	         if(prevNode != null && currNode.getLeftNode() == prevNode){
	            if(currNode.getRightNode() != null) {
	            	nodeColl.push(currNode.getRightNode());
	            }
	            else {
	                System.out.print(":"+currNode.getValue());
	                nodeColl.pop();
	            }
	         }
	 
	        *//* case 3. We are moving up the tree from right child *//*
	         if(prevNode != null && currNode.getRightNode() == prevNode){
	        	 System.out.print(".."+currNode.getValue());
	             nodeColl.pop();
	         }
	         prevNode = currNode;
		}
	}*/

	/**
	 * https://www.youtube.com/watch?v=MILxfAbIhrE
	 * @return
	 */
	public boolean isBinarySearchTree() {
		return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isBinarySearchTree(BinaryNode rootNode, int low, int high) {
		if(rootNode == null) {
			return true;
		}
		
		if(low < rootNode.getValue() && high > rootNode.getValue()) {
			return isBinarySearchTree(rootNode.getLeftNode(), low, rootNode.getValue()) &&
				isBinarySearchTree(rootNode.getRightNode(), rootNode.getValue(), high);
		} else {
			return false;
		}		
		
	}
	// TODO: Refer Tree Traversals class which is a duplicate of this one.
	/**
	 * Print Level order nodes using BFS (Breadth First Search)
	 *//*
	public void printLevelOrderUsing2Queues() {
		
		if(root == null) {
			return;
		}
		Queue<BinaryNode> currentLevel = new LinkedList<BinaryNode> ();
		Queue<BinaryNode> nextLevel = new LinkedList<BinaryNode> ();
		currentLevel.add(root);
		
		while(!currentLevel.isEmpty()) {
			BinaryNode currNode = currentLevel.poll();
			    if (currNode != null) {
			      System.out.print(" "+currNode.getValue());
			      if(currNode.getLeftNode() != null) {
			    	  nextLevel.add(currNode.getLeftNode());
			      }
			      if(currNode.getRightNode() != null) {
			      nextLevel.add(currNode.getRightNode());
			      }
			    }
			    if (currentLevel.isEmpty()) {
			      System.out.println();
			      currentLevel = nextLevel;
			      nextLevel = new LinkedList<BinaryNode>();
			     		     
			    }
		}
		
	}
	
	*//**
	 * Print Level order nodes using BFS (Breadth First Search)
	 *//*
	public void printLevelOrderUsingsingleQueue() {
		if(root == null) {
			return;
		}
		
		Queue<BinaryNode> queueList = new LinkedList<BinaryNode> ();
		int currentLevel = 1;
		int nextLevel = 0;
		queueList.add(root);
		while(!queueList.isEmpty()) {
			BinaryNode currentNode = queueList.poll();
			currentLevel--;
			
			if(currentNode != null) {
				System.out.print(" "+currentNode.getValue());
				if(currentNode.getLeftNode() != null) {
					queueList.add(currentNode.getLeftNode());
					nextLevel++;
				}
				if(currentNode.getRightNode() != null) {
					queueList.add(currentNode.getRightNode());
					nextLevel++;
				}				
			}
			
			if(currentLevel==0) {
				System.out.println();
				currentLevel = nextLevel;
				nextLevel--;
			}
		}
	}
	*/
	public void printLevelOLrderUsingDFS() {
		if(root == null) {
			return;
		}
		
		int maxHeight = getHeight();
		for(int level=1; level<=maxHeight; level++) {
			printLevel(root, level);
			System.out.println();
		}
	}
	
	private void printLevel(BinaryNode currentRoot, int level) {
		if(currentRoot == null) {
			return;
		}
		if (level == 1) {
		    System.out.print(currentRoot.getValue() + " ");
		  } else {
		    printLevel(currentRoot.getLeftNode(), level-1);
		    printLevel(currentRoot.getRightNode(), level-1);
		  }
	}


	public boolean isSameTree(BinaryTree tree1, BinaryTree tree2) {
		return isSameTree(tree1.root, tree2.root);
	}
	public boolean isSameTree(BinaryNode tree1, BinaryNode tree2) {
		if (tree1 == null && tree2 == null) {
			return true;
		}

		if (tree1 == null || tree2 == null) {
			return false;
		}

		return tree1.getValue() == tree2.getValue() &&
			isSameTree(tree1.getLeftNode(), tree2.getLeftNode()) &&
			isSameTree(tree1.getRightNode(), tree2.getRightNode());
	}
	
	/***
	 * This Method is for Binary Search Tree
	 * https://www.youtube.com/watch?v=TIoCCStdiFo
	 * @param node1
	 * @param node2
	 */
	public void findLeastCommonAncestor(BinaryNode node1, BinaryNode node2) {		
		System.out.println("LCA:" + findLCA(root, node1, node2).getValue());
	}
	
	private BinaryNode findLCA(BinaryNode currentRoot, BinaryNode node1, BinaryNode node2) {
		 if(currentRoot == null || node1 == null || node2 == null) {
			 return null;
		 }
		 
		 if(Math.max(node1.getValue(), node2.getValue()) < currentRoot.getValue()) {
			 return findLCA(currentRoot.getLeftNode(), node1, node2);
		 } else if(Math.min(node1.getValue(), node2.getValue()) > currentRoot.getValue()) {
			 return findLCA(currentRoot.getRightNode(), node1, node2);
		 } else {
			 return currentRoot;
		 }
		}
	
	/**
	 * This method is for Binary Tree
	 * https://www.youtube.com/watch?v=13m9ZCB8gjw
	 * @param node1
	 * @param node2
	 */
	public void findLowestCommonAncestor(BinaryNode node1, BinaryNode node2) {
		System.out.println(
			"LCA of Binary Tree:" + findLowestCommonAncestor(root, node1, node2).getValue() + " for nodes " + node1
				.getValue() + ":" + node2.getValue());
	}
	
	 private static BinaryNode findLowestCommonAncestor(BinaryNode root, BinaryNode node1, BinaryNode node2) {

		 if (root == null) {
			 return null;
		 }

		 /**
		  * If Node 'node1' or Node 'node2' is also the root, then the root itself is lowest common ancestor
		  */
		 if (root == node1 || root == node2) {
			 return root;
		 }

		 BinaryNode left = findLowestCommonAncestor(root.getLeftNode(), node1, node2);
		 BinaryNode right = findLowestCommonAncestor(root.getRightNode(), node1, node2);

		 /**
		  * If Node 'node1' and Node 'node2' lie in the left, their Lowest Common Ancestor is in the left.
		  * If Node 'node1' and Node 'node2' lie in the right,their Lowest Common Ancestor is in the right.
		  *
		  * Otherwise, root is the Lowest common ancestor.
		  */
		 if (left != null && right != null) {
			 return root;
		 }

		 /**
		  * We couldn't find the nodes
		  */
		 if (left == null && right == null) {
			 return null;
		 }

		 return (left != null) ? left : right;
	 }
	
}


public class BinarySearchTree {
	public static void main(String args[]) {
		BinaryNode node = new BinaryNode(20);
		BinaryTree b = new BinaryTree();
		b.add(node);
		System.out.println("Maximum:" + b.getMaximum() + ":Minimum:"+ b.getMinimum() + ":Height:" + b.getHeight());
		/*System.out.println("InOrder Traverse");
		b.printInorderTraverse();
		System.out.println("PreOrder Traverse");
		b.printPreorderTraverse();
		System.out.println("PostOrder Traverse");
		b.printPostorderTraverse();*/
		
		BinaryNode b6 = new BinaryNode(80);
		BinaryNode b7 = new BinaryNode(90);
		BinaryNode b8 = new BinaryNode(60, b6, b7);
		b.add(b8);
		System.out.println("Is Binary Tree:" + b.isBinarySearchTree());
		/*System.out.println("InOrder Traverse");
		b.printInorderTraverse();
		System.out.println("InOrder Traverse Iterative way");
		b.inOrderTraverseIterativeWay();
		System.out.println("PreOrder Traverse");
		b.printPreorderTraverse();
		System.out.println("PostOrder Traverse");
		b.printPostorderTraverse();
		System.out.println("Print Level Order using 2 Queues / BFS");
		b.printLevelOrderUsing2Queues();
		System.out.println("Print Level Order using single Queue / BFS");
		b.printLevelOrderUsingsingleQueue();
		System.out.println("Print Level Order using single DFS");
		b.printLevelOLrderUsingDFS();*/
		
		BinaryNode b1 = new BinaryNode(85);
		BinaryNode b2 = new BinaryNode(65);
		BinaryNode b3 = new BinaryNode(70, b2, b1);
		b.add(b3);
		
		b.findLeastCommonAncestor(b2, b1);
		b.findLowestCommonAncestor(b2, b1);
		b.findLowestCommonAncestor(b2, new BinaryNode(1000));
		
		BinaryNode node1 = new BinaryNode(25);
		b.add(node1);
		System.out.println("Maximum:" + b.getMaximum() + ":Minimum:"+ b.getMinimum()+ ":Height:" + b.getHeight());
		/*b.printInorderTraverse();
		b.printPreorderTraverse();
		b.printPostorderTraverse();*/
		
		//System.out.println(b.isBinarySearchTree());
		
		BinaryNode node2 = new BinaryNode(15);
		b.add(node2);
		System.out.println("Maximum:" + b.getMaximum() + ":Minimum:"+ b.getMinimum()+ ":Height:" + b.getHeight());
		/*b.printInorderTraverse();
		b.printPreorderTraverse();
		b.printPostorderTraverse();*/
		
		System.out.println(b.isBinarySearchTree());
		
		b.add(new BinaryNode(78));
		System.out.println("Maximum:" + b.getMaximum() + ":Minimum:"+ b.getMinimum()+ ":Height:" + b.getHeight());
		/*b.printInorderTraverse();
		b.printPreorderTraverse();
		b.printPostorderTraverse();*/
		System.out.println("isSameTree:" + b.isSameTree(b, b));
		System.out.println(b.isBinarySearchTree());
		
		b.add(new BinaryNode(10));
		System.out.println("Maximum:" + b.getMaximum() + ":Minimum:"+ b.getMinimum()+ ":Height:" + b.getHeight());
		/*b.printInorderTraverse();
		b.printPreorderTraverse();
		b.printPostorderTraverse();*/
		
		System.out.println(b.isBinarySearchTree());
		
		b.add(new BinaryNode(32));
		System.out.println("Maximum:" + b.getMaximum() + ":Minimum:"+ b.getMinimum()+ ":Height:" + b.getHeight());
		/*b.printInorderTraverse();
		b.printPreorderTraverse();
		b.printPostorderTraverse();*/
		
		System.out.println(b.isBinarySearchTree());
		
		b3 = new BinaryNode(45);
		BinaryNode b4 = new BinaryNode(60);
		BinaryNode b5 = new BinaryNode(50, b4, b3);
		
		b.add(b5);
		System.out.println("Maximum:" + b.getMaximum() + ":Minimum:"+ b.getMinimum()+ ":Height:" + b.getHeight());
		/*b.printInorderTraverse();
		b.printPreorderTraverse();
		b.printPostorderTraverse();*/
		System.out.println(b.isBinarySearchTree());
		
		
		b.add(new BinaryNode(70));
		System.out.println("Maximum:" + b.getMaximum() + ":Minimum:"+ b.getMinimum());
		/*b.printInorderTraverse();
		b.printPreorderTraverse();
		b.printPostorderTraverse();*/
		
		b.add(new BinaryNode(30));
		System.out.println("Maximum:" + b.getMaximum() + ":Minimum:"+ b.getMinimum());
		/*b.printInorderTraverse();
		b.printPreorderTraverse();
		b.printPostorderTraverse();*/
		
		
	}
}
