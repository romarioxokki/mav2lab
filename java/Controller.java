import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Controller {

    private static final  File FOLDER = new File("src/main/command");
    private static final String PATTERN1 = "create Teacher [A-Z][a-z]* \\d{2}\\.\\d{2}\\.\\d{4} \\d{11} (RUSSIAN|MATH|PHYSICS|BIOLOGY|ENGLISH) \\d{2}:\\d{2}-\\d{2}:\\d{2}( )*";
    private static final String PATTERN2 = "create Student [A-Z][a-z]* \\d{2}\\.\\d{2}\\.\\d{4} \\d{11} (RUSSIAN|MATH|PHYSICS|BIOLOGY|ENGLISH) \\d{1}\\.\\d{1}( )*";
    private static final String PATTERN3 = "update Person \\d{1,4} [A-Z][a-z]* \\d{2}\\.\\d{2}\\.\\d{4} (RUSSIAN|MATH|PHYSICS|BIOLOGY|ENGLISH) 11:00-12:00 ";
    private static final String PATTERN4 = "delete \\d{1,4}( )*";
    public static Queue<Map<String, Object>> queue = new LinkedList<>();
    public static void start() {
        new Thread()  {
            public void run() {
                while (true) {
                    try {
                        read();
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }


        }.start();

    }
    public static void read() {
        File[] files = FOLDER.listFiles();

        for (int i = 0; i < files.length; i++) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(files[i]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Map<String, Object> params = new HashMap<String, Object>();
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append(" ");
            }
            scanner.close();
            String lines = stringBuilder.toString();
            String[] splitedLines = lines.split(" ");



            if (Pattern.matches(PATTERN1, lines) || Pattern.matches(PATTERN2, lines)) {
                params.put("command", splitedLines[0]);
                params.put("type", splitedLines[1]);
                params.put("name", splitedLines[2]);
                params.put("birthdate", splitedLines[3]);
                params.put("phone", splitedLines[4]);


            }



            if (Pattern.matches(PATTERN3, lines)) {
                params.put("command", splitedLines[0]);
                params.put("type", splitedLines[1]);
                params.put("id", Integer.valueOf(splitedLines[2]));
                params.put("name", splitedLines[3]);
                params.put("birthdate", splitedLines[4]);
                params.put("phone",splitedLines[5]);
                System.out.println("Yes");

            }
                if (Pattern.matches(PATTERN4, lines)){
                    params.put("command", splitedLines[0]);
                    params.put("id", Integer.valueOf(splitedLines[1]));
                }

                files[i].delete();
                if (params.size() > 0)  queue.offer(params);


            }


        }

    }
