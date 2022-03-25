package mc.bape.command;

//import com.google.common.collect.ComparisonChain;
//import com.google.common.collect.Ordering;
//import io.space.chat.SpaceChat;
//import io.space.chat.packet.client.CSpacePacketChat;
//import mc.bape.Manager.ModuleManager;
//import mc.bape.util.Helper;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.network.NetworkPlayerInfo;
//import net.minecraft.command.CommandBase;
//import net.minecraft.command.CommandException;
//import net.minecraft.command.ICommand;
//import net.minecraft.command.ICommandSender;
//import net.minecraft.scoreboard.ScorePlayerTeam;
//import net.minecraft.util.BlockPos;
//import net.minecraft.world.WorldSettings;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//public class Chat implements ICommand {
//    @Override
//    public String getCommandName() {
//        return "chat";
//    }
//
//    @Override
//    public String getCommandUsage(ICommandSender iCommandSender) {
//        return "chat <messages>";
//    }
//
//    @Override
//    public List<String> getCommandAliases() {
//        return Collections.<String>emptyList();
//    }
//
//    @Override
//    public void processCommand(ICommandSender iCommandSender, String[] strings) throws CommandException {
//        if (ModuleManager.getModule("NoCommand").getState()) {
//            return;
//        }
//        if (strings.length == 0) {
//            Helper.sendMessage("-chat <message>");
//            return;
//        }
//
//        final StringBuilder builder = new StringBuilder();
//
//        for (int i = 0; i < strings.length; i++) {
//            builder.append(strings[i]);
//
//            if (i != strings.length - 1) {
//                builder.append(" ");
//            }
//        }
//
//        if (SpaceChat.Instance != null)
//            if (SpaceChat.Instance.serverOnline())
//                SpaceChat.Instance.sendPacket(new CSpacePacketChat(builder.toString()));
//            else
//                Helper.sendMessage("Server not online");
//        else
//            Helper.sendMessage("Server not connect");
//    }
//
//    @Override
//    public boolean canCommandSenderUseCommand(ICommandSender iCommandSender) {
//        return true;
//    }
//
//    @Override
//    public List<String> addTabCompletionOptions(ICommandSender iCommandSender, String[] strings, BlockPos blockPos) {
//        List<String> list = new ArrayList<String>();
//        try {
//            //this.exampleMod.log(MinecraftServer.getServer().getConfigurationManager().toString());
//
//            Ordering<NetworkPlayerInfo> field_175252_a = Ordering.from(new Comparator<NetworkPlayerInfo>()
//            {
//                private void PlayerComparator()
//                {
//                }
//
//                public int compare(NetworkPlayerInfo p_compare_1_, NetworkPlayerInfo p_compare_2_)
//                {
//                    ScorePlayerTeam scoreplayerteam = p_compare_1_.getPlayerTeam();
//                    ScorePlayerTeam scoreplayerteam1 = p_compare_2_.getPlayerTeam();
//                    return ComparisonChain.start().compareTrueFirst(p_compare_1_.getGameType() != WorldSettings.GameType.SPECTATOR, p_compare_2_.getGameType() != WorldSettings.GameType.SPECTATOR).compare(scoreplayerteam != null ? scoreplayerteam.getRegisteredName() : "", scoreplayerteam1 != null ? scoreplayerteam1.getRegisteredName() : "").compare(p_compare_1_.getGameProfile().getName(), p_compare_2_.getGameProfile().getName()).result();
//                }
//            });
//            String last_s = "";
//            for (NetworkPlayerInfo networkPlayerInfoIn : field_175252_a.sortedCopy(Minecraft.getMinecraft().thePlayer.sendQueue.getPlayerInfoMap())) {
//                String s = networkPlayerInfoIn.getDisplayName() != null && false ? networkPlayerInfoIn.getDisplayName().getFormattedText() : networkPlayerInfoIn.getGameProfile().getName();
//                if (!s.equals(last_s))
//                    list.add(s);
//                last_s = s;
//            }
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//        String[] astring = new String[list.size()];
//
//        for (int i = 0; i < list.size(); ++i)
//        {
//            astring[i] = list.get(i);
//        }
//
//        return CommandBase.getListOfStringsMatchingLastWord(strings, astring);
//    }
//
//    @Override
//    public boolean isUsernameIndex(String[] strings, int i) {
//        return false;
//    }
//
//    @Override
//    public int compareTo(ICommand o) {
//        return this.getCommandName().compareTo(o.getCommandName());
//    }
//}
