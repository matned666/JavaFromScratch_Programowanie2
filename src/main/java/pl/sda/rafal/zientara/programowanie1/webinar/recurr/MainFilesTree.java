package pl.sda.rafal.zientara.programowanie1.webinar.recurr;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainFilesTree {

    public static void main(String[] args) {
        File file = new File("C:\\DATA\\data-wyszukiwanie plikow");

        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
        System.out.println(file.isDirectory());

        TreeWindow window = new TreeWindow();

    }


}
