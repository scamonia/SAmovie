package com.SAmovie.util.cmd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.stereotype.Component;

@Component
public class RunCmdUtil {
    public RunCmdUtil() {
    }

    public boolean executeMR(String command) {
        Runtime runtime = Runtime.getRuntime();

        try {
            Process e = runtime.exec(command);
            InputStream inputStream = e.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String string = "";

            while((string = bufferedReader.readLine()) != null && !"Congratulations!".equals(string)) {
                ;
            }

            e.waitFor();
            bufferedReader.close();
            inputStream.close();
            e.destroy();
            return "Congratulations!".equals(string);
        } catch (Exception var7) {
            var7.printStackTrace();
            return false;
        }
    }

    public boolean executeCommand(String command) {
        Runtime runtime = Runtime.getRuntime();

        try {
            Process e = runtime.exec(command);
            e.waitFor();
            e.destroy();
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }
}
