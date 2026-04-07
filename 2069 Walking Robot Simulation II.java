// PROBLEM : (2069) Walking Robot Simulation II

// SOLUTOIN :

class Robot {
    private int width;
    private int height;
    private int pos;        // Current position along the unrolled perimeter
    private int perimeter;  // Total steps to complete one full loop
    private boolean moved;  // Flag to track if we've made at least one move

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.pos = 0;
        this.moved = false;
        // Total cells on the perimeter
        this.perimeter = 2 * (width - 1) + 2 * (height - 1);
    }
    
    public void step(int num) {
        // Fast-forward the position using modulo arithmetic
        pos = (pos + num) % perimeter;
        moved = true;
    }
    
    public int[] getPos() {
        int w = width - 1;
        int h = height - 1;
        
        // Bottom edge (moving East)
        if (pos <= w) {
            return new int[]{pos, 0};
        } 
        // Right edge (moving North)
        else if (pos <= w + h) {
            return new int[]{w, pos - w};
        } 
        // Top edge (moving West)
        else if (pos <= 2 * w + h) {
            return new int[]{w - (pos - w - h), h};
        } 
        // Left edge (moving South)
        else {
            return new int[]{0, h - (pos - 2 * w - h)};
        }
    }
    
    public String getDir() {
        // Handle the (0, 0) corner case
        if (!moved) return "East";
        if (pos == 0) return "South";
        
        int w = width - 1;
        int h = height - 1;
        
        // Determine direction based on the segment of the perimeter
        if (pos <= w) return "East";
        if (pos <= w + h) return "North";
        if (pos <= 2 * w + h) return "West";
        
        return "South";
    }
}
