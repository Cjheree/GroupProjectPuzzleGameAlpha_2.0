package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class object_Barrel_Closed_1 extends SuperObject {

    public object_Barrel_Closed_1() {

        name = "Barrel_Closed_1";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Barrel_Closed.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        collision = false; // Door will be solid until it is unlocked ** GOING TO CHANGE **

    }

}
