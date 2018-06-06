package com.steve.test;

class TreeNode {
	
	
    private TreeNode leftChild = null;
    private TreeNode rightChild = null;
    
    public TreeNode(TreeNode leftChild, TreeNode rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
   
    }
    
    public TreeNode getLeftChild() {
        return this.leftChild;
    }
    
    public TreeNode getRightChild() {
        return this.rightChild;
    }
    
   
}

public class TreeHeight {
	
	
    public static int calculateHeight(TreeNode root) {
    	
    	
    	if (root == null) {
    		return 0;
    	}
    	
    	TreeNode nodeLeft = root.getLeftChild();
    	TreeNode nodeRight = root.getRightChild();
    	
    	int totCnt = 0;
    	
    	while (true) {
    		
    		nodeLeft = root.getLeftChild();
    		nodeRight = root.getRightChild();
    		
    		// We have reached the bottom leaf
    		if (nodeLeft == null && nodeRight == null) {
    			if (totCnt == 0) totCnt = 1;
    			break; // Terminate loop and return
    		}
    		
    		++totCnt;
    		
    		// We have nodes on just the left so switch to the left
    		if (nodeLeft != null && nodeRight == null) {
    			root = nodeLeft;
    		}
    		
    		// We have nodes on just the right so switch to the right
    		if (nodeRight != null && nodeLeft == null) {
    			root = nodeRight;
    		}
    		
    		// We have a node on both left and right so figure out which is deeper.
    		if (nodeRight != null && nodeLeft != null) {
    			
    			while (nodeRight != null && nodeLeft != null) {
    				
    			    nodeRight = nodeRight.getRightChild();
    			    nodeLeft = nodeLeft.getLeftChild();
    			    if (nodeRight != null && nodeLeft != null) {
    			    	++totCnt;
    			    }
    			}
    		}
    		
    		// We have exhausted both sides equally, so we are at the bottom leaf
    		if (nodeLeft == null && nodeRight == null) {
    			break; // Terminate loop and return
    		}
    		
    		// We have nodes on just the left so switch to the left
    		if (nodeLeft != null && nodeRight == null) {
    			root = nodeLeft;
    		}
    		
    		// We have nodes on just the right so switch to the right
    		if (nodeRight != null && nodeLeft == null) {
    			root = nodeRight;
    		}
    		
	    	
    	}
    	
    	return totCnt;
    	
    	
    	
        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    public static void main(String[] args) {
    	
    	
        TreeNode leaf1 = new TreeNode(null, null);
        TreeNode leaf2 = new TreeNode(null, leaf1);
        TreeNode node = new TreeNode(leaf1, null);
        TreeNode node1 = new TreeNode(leaf2, node);
        TreeNode node2 = new TreeNode(node1, node1);
        TreeNode node3 = new TreeNode(node2, node2);
        TreeNode node4 = new TreeNode(node3, node2);
        TreeNode node5 = new TreeNode(node4, node3);
        
        TreeNode root = node5;
        
    	
    	// Create a balanced tree of 100 nodes
    	TreeNode tn = null;
    	for (int i = 0 ; i <= 100; i++) {
    		if ((i % 2) == 0) {
    		   
    		       tn = new TreeNode(null, tn);
    		   
    		} else {
    			
    			    tn = new TreeNode(tn, null);
    			
    		}
    	}
    	
    	System.out.println("Tree Height: " + TreeHeight.calculateHeight(tn));
    	
    }
}
