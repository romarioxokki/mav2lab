import service.Controller;
import service.Dispatcher;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Controller.start();
        Dispatcher.start();


    }

}
