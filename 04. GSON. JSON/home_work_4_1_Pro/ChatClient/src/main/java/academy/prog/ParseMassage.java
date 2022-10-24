package academy.prog;

public class ParseMassage {
    static String to;
    public static String parseTo(String massage){
        if(massage.charAt(0) == '@'){
            String[] world = massage.split(" ");
            to = world[0].replaceFirst("@", "");
        } else {
            to = "All";
        }
        return to;
    }

    public static String parseText(String massage){
        String text = "";
        if(massage.charAt(0) == '@'){
            String[] worlds = massage.split(" ");
            for (int i = 1; i < worlds.length; i ++){
                text = text + worlds[i] + " ";
            }
        } else {
            text = massage;
        }

        return text;
    }
}
