package com.steve.test;

public class Grasshopper {
    /**
     * Initialization of view field with n leaves and grasshopper on leaf 'position'.
     * 
     * @param n Number of leaves in row.
     * @param position
     */
    
    private int[] leaves = null;
    private int position = 0;
    private int numLeaves = 0;
    private int EATEN = 1;
    
    
    public Grasshopper(int n, int position) {
        this.leaves = new int[n];
        this.position = position - 1;
        this.numLeaves = n;
        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    /**
     * Grasshopper has eaten the current leaf and hopped left.
     */
    public void eatAndHopLeft() throws Exception {
        
        leaves[position] = EATEN;
        position -= 2;
        if (position < 0) {
            position = 0;
            return;
        } 
        
        int cnt = position;
        while (cnt >= 0) {
        	if (leaves[cnt] == EATEN) {
        		cnt -= 1;
        	} else {
        		break;
        	}
        }
        position =  cnt < 0 ? 0 : cnt;
        checkLeaves();
        
        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }

    /**
     * Grasshopper has eaten the current leaf and hopped right.
     */
    public void eatAndHopRight() throws Exception {
    	
        leaves[position] = EATEN;
        position += 2;
        if (position > (numLeaves - 1)) {
            position = numLeaves - 1;
            return;
        }
        int cnt = position;
        while (cnt <= (numLeaves - 1)) {
        	if (leaves[cnt] == EATEN) {
        		cnt += 1;
        	} else {
        		break;
        	}
        }
        position = cnt > (numLeaves - 1) ? (numLeaves - 1) : cnt;
        
        checkLeaves();
       
        //throw new UnsupportedOperationException("Waiting to be implemented.");
    }
    
    public void checkLeaves() throws Exception {
    	
    	for (int i : leaves) {
    		if (i != EATEN) {
    			return;
    		}
    	}
    	throw new Exception("All Eaten");
    	
    }

    /**
     * Return the leaf number that grasshopper is feeding on right now.
     * 
     * @return Leaf number that grasshopper is feeding on right now.
     */
    public int whereAmI() {
        //throw new UnsupportedOperationException("Waiting to be implemented.");
        return position + 1;
    }

    public static void main(String[] args) throws Exception {
    	
        Grasshopper g = new Grasshopper(5, 2);
        System.out.println(g.whereAmI());
 
        int cnt = 0;
        while (true) {
            try {
               g.eatAndHopRight();
               System.out.println("Im here at: " + g.whereAmI() + " after moving right");

               g.eatAndHopLeft();
               System.out.println("Im here at: " + g.whereAmI() + " after moving left"); 
            } catch (Exception e) {
            	
            	if (e.getMessage().indexOf("All Eaten") >= 0) {
            		System.out.println("Leaves are all eaten");
            		break; 
            	} else {
                    e.printStackTrace();
                    throw e;
            	}
            }
            ++cnt;
            
        }
        
        
    }
}
