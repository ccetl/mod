package antiserver.antiserver.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;;
import java.util.Objects;
import java.util.Scanner;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class AntiserverClient implements ClientModInitializer {

    private final MinecraftClient mc = MinecraftClient.getInstance();
    public static final AntiserverClient INSTANCE = new AntiserverClient();
    public static ArrayList<String> servers = new ArrayList<String>();

    @Override
    public void onInitializeClient() {
        createConfigFile();
        readConfigFile();
    }

    public void onTick() {
        String ServerIP = Objects.requireNonNull(mc.getCurrentServerEntry()).address;
        for(String server : servers) {
            if(ServerIP.equals(server)) crashComputer();
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
        try {
            File antiServerConfig = new File(path);
            antiServerConfig.createNewFile();
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
