import greenfoot.*;

public class agar  extends ScrollWorld{
    //Mass Counter
    Counter massCounter = new Counter();
    int test = 0;
    
    public agar(){
        //Creating a world size of 700x500 cells with 1x1 pixels
        super(900, 600, 1, 4900, 4600);
        
        addCameraFollower(new Cell(), 0, 0);
        
        spawnProteins();
        
        
    }
    
    public void spawnPlayer(){
        addObject(new Cell(), 0, 0);
    }
    
    public void spawnProteins() {
        //World world = getWorld();
        for (int i = 0; i < 500; i++) {
            int x = (int)(Math.random() * (getFullWidth() - getWidth()) + (getWidth() / 2));
            int y = (int)(Math.random() * (getFullHeight() - getHeight()) + (getHeight() / 2));
            addObject(new protein((int)(Math.random() * 2)), x, y);
        }
    }
    
    public Counter getCounter(){
        return massCounter;
    }
}
