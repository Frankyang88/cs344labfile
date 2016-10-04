package cosc344.utils;

public class SceneGenerator extends BaseClass {
  /**
   * prints the game name at the start
   */
    public static void beginGame() {
        printBlankSpace();
        print(" ________  ___  ________  ________  ___       ________          ________");
        print("|\\   ___ \\|\\  \\|\\   __  \\|\\   __  \\|\\  \\     |\\   __  \\        |\\_____  \\");
        print("\\ \\  \\_|\\ \\ \\  \\ \\  \\|\\  \\ \\  \\|\\ /\\ \\  \\    \\ \\  \\|\\  \\       \\|____|\\ /");
        print(" \\ \\  \\ \\\\ \\ \\  \\ \\   __  \\ \\   __  \\ \\  \\    \\ \\  \\\\\\  \\            \\|\\  \\");
        print("  \\ \\  \\_\\\\ \\ \\  \\ \\  \\ \\  \\ \\  \\|\\  \\ \\  \\____\\ \\  \\\\\\  \\          __\\_\\  \\");
        print("   \\ \\_______\\ \\__\\ \\__\\ \\__\\ \\_______\\ \\_______\\ \\_______\\        |\\_______\\");
        print("    \\|_______|\\|__|\\|__|\\|__|\\|_______|\\|_______|\\|_______|        \\|_______|");
        printBlankSpace();
    }
    
    /**
     * prints the visual representation of an axe
     */
    public static void showAxe() {
        printBlankSpace();
        print("  ,:\\      /:.");
        print(" //  \\_()_/  \\\\");
        print("||   |    |   ||");
        print("||   |    |   ||");
        print("||   |____|   ||");
        print(" \\\\  / || \\  //");
        print("  `:/  ||  \\;'");
        print("      ||");
        print("      ||");
        print("      XX");
        print("      XX");
        print("      XX");
        print("      XX");
        print("      OO");
        print("      `'");
        printBlankSpace();
    }

    /**
     * prints the visual representation of a sword
     */
    public static void showSword(){
        printBlankSpace();
        print("                          ___");
        print("                          ( ((");
        print("                           ) ))");
        print("  .::.                    / /(");
        print(" 'M .-;-.-.-.-.-.-.-.-.-/| ((::::::::::::::::::::::::::::::::::::::::::::::.._");
        print("(J ( ( ( ( ( ( ( ( ( ( ( |  ))   -====================================-      _.>");
        print(" `P `-;-`-`-`-`-`-`-`-`-\\| ((::::::::::::::::::::::::::::::::::::::::::::::''");
        print("  `::'                    \\ \\(");
        print("                           ) ))");
        print("                          (_((");
        printBlankSpace();
    }
    
    /**
     * prints the visual representation of a bow
     */
    public static void showBow(){
        printBlankSpace();
        print("          4$$-.");
        print("           4   \".");
        print("           4    ^.");
        print("           4     $");
        print("           4     'b");
        print("           4      \"b.");
        print("           4        $");
        print("           4        $r");
        print("           4        $F");
        print("-$b========4========$b====*P=-");
        print("           4       *$$F");
        print("           4        $$\"");
        print("           4       .$F");
        print("           4       dP");
        print("           4      F");
        print("           4     @");
        print("           4    .");
        print("           J.");
        print("          '$$");
        printBlankSpace();
    }
    
    /**
     * prints the visual representation of the intro to the area
     */
    public static void showArea() {
        printBlankSpace();
        print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
  print("@@@@@@@@@@@@@@@@@@@@@@@'~~~     ~~~`@@@@@@@@@@@@@@@@@@@@@@@@@");
  print("@@@@@@@@@@@@@@@@@@'                     `@@@@@@@@@@@@@@@@@@@@");
  print("@@@@@@@@@@@@@@@'                           `@@@@@@@@@@@@@@@@@");
  print("@@@@@@@@@@@@@'                               `@@@@@@@@@@@@@@@");
  print("@@@@@@@@@@@'                                   `@@@@@@@@@@@@@");
  print("@@@@@@@@@@'                                     `@@@@@@@@@@@@");
  print("@@@@@@@@@'                                       `@@@@@@@@@@@");
  print("@@@@@@@@@                                         @@@@@@@@@@@");
  print("@@@@@@@@'                      n,                 `@@@@@@@@@@");
  print("@@@@@@@@                     _/ | _                @@@@@@@@@@");
  print("@@@@@@@@                    /'  `'/                @@@@@@@@@@");
  print("@@@@@@@@a                 <~    .'                a@@@@@@@@@@");
  print("@@@@@@@@@                 .'    |                 @@@@@@@@@@@");
  print("@@@@@@@@@a              _/      |                a@@@@@@@@@@@");
  print("@@@@@@@@@@a           _/      `.`.              a@@@@@@@@@@@@");
  print("@@@@@@@@@@@a     ____/ '   \\__ | |______       a@@@@@@@@@@@@@");
  print("@@@@@@@@@@@@@a__/___/      /__\\ \\ \\     \\___.a@@@@@@@@@@@@@@@");
  print("@@@@@@@@@@@@@/  (___.'\\_______)\\_|_|        \\@@@@@@@@@@@@@@@@");
  print("@@@@@@@@@@@@|\\________                       ~~~~~\\@@@@@@@@@@");
  print("---------------->Into the adventure of Diablo<----------------");
    }
    
 /**
  * prints the visual representation of area in the game
  */
 public static void showBigArea() {
        print("                             _______________________________________");
        print("                            /  ___________________________________  \\");
        print("                           /  /_/_/_/_/_|_|_|_|_|_|_|_|_|_\\_\\_\\_\\_\\  \\");
        print("                          /  /_/_/_/_J__L_L_L_|_|_|_J_J_J__L_\\_\\_\\_\\  \\");
        print("                         /  /_/_/_J__L_J__L_L_|_|_|_J_J__L_J__L_\\_\\_\\  \\");
        print("                        /  /_/_J__L_J__L_J_J__L_|_J__L_L_J__L_J__L_\\_\\  \\");
        print("                       /  /_/__L_/__L_J__L_J__L_|_J__L_J__L_J__\\_J__\\_\\  \\");
        print("                      /  /_J__/_J__/__L_J__|__L_|_J__|__L_J__\\__L_\\__L_\\  \\");
        print("                     /  /  F /  F J  J  |  F J  |  F J  |  F  F J  \\ J  \\  \\");
        print("                    /  /--/-J--/--L--|--L-J--J--|--L--L-J--|--J--\\--L-\\--\\  \\");
        print("                   /  /__/__L_J__J___L_J__J__|__|__|__L__L_J___L__L_J__\\__\\  \\");
        print("                  /  /  /  /  F  F  J  J  |  |  |  |  |  F  F  J  J  \\  \\  \\  \\");
        print("                 /  /--/--/--/--J---L--|--|--|--o--|--|--|--J---L--\\--\\--\\--\\  \\");
        print("                /  /__/__J__J___L__J___L__L__L__|__J__J__J___L__J___L__L__\\__\\  \\");
        print("               /  /  /   F  F  J   F  J  J   F  |  J   F  F  J   F  J  J   \\  \\  \\");
        print("              /  /--/---/--J---L--J---L--|--J---|---L--|--J---L--J---L--\\---\\--\\  \\");
        print("             /  /__J___/___L__/___L__J___L__J___|___L__J___L__J___\\__J___\\___L__\\  \\");
        print("            /  /   F  J   /  J   J   |  J   J   |   F   F  |   F   F  \\   F  J   \\  \\");
        print("           /  /---/---L--J---L---L---L--|---|---|---|---|--J---J---J---L--J---\\---\\  \\");
        print("          /  /___/___/___L__J___J___J___|___|___|___|___|___L___L___L__J___\\___\\___\\  \\");
        print("         /  /   /   /   /   F   F   F   F   F   |   J   J   J   J   J   \\   \\   \\   \\  \\");
        print("        /  /___/___J___J___J___J___J____L___L___|___J___J____L___L___L___L___L___\\___\\  \\");
        print("       /  /   /    F   F   F   |   |   J    F   |   J    F   |   |   J   J   J    \\   \\  \\");
        print("      /  /___J____/___/___J____L___L___|___J____|____L___|___J___J____L___\\___\\____L___\\  \\");
        print("     /  /    F   /   J    F   J   J    |   J    |    F   |    F   F   J    F   \\   J    \\  \\");
        print("    /  /____/___J____L___/____L___|____L___|____|____|___J____|___J____\\___J____L___\\____\\  \\");
        print("   /  /    /    F   /   J    J    F   J    F    |    J    F   J    F    F   \\   J    \\    \\  \\");
        print("  /  /____/____/___J____L____|____L___J____L____|____J____L___J____|____J____L___\\____\\____\\  \\");
        print(" /                                                                                             \\");
        print("/_______________________________________________________________________________________________\\");
        print("|                                                                                               |");
        print("|                         Into the labyrinth of Diablo !                                        |");
        print("|_______________________________________________________________________________________________|");
        printBlankSpace();
    }
    
    /**
     * prints the visual representation of the hero in the game
     */
    public static void showHero() {
        printBlankSpace();
        print("             |\\_|\\");
        print("             | a_a\\");
        print("             | | \"]");
        print("         ____| '-\\___");
        print("        /.----.___.-'\\");
        print("       //        _    \\");
        print("      //   .-. (~v~) /|");
        print("     |'|  /\\:  .--  / \\");
        print("    // |-/  \\_/____/\\/~|");
        print("   |/  \\ |  []_|_|_] \\ |");
        print("   | \\  | \\ |___   _\\ ]_}");
        print("   | |  '-' /   '.'  |");
        print("   | |     /    /|:  |");
        print("   | |     |   / |:  /\\");
        print("   | |     /  /  |  /  \\");
        print("   | |    |  /  /  |    \\");
        print("   \\ |    |/\\/  |/|/\\    \\");
        print("    \\|\\ |\\|  |  | / /\\/\\__\\");
        print("     \\ \\| | /   | |__");
        print("          / |   |____)");
        print("          |_/");
        printBlankSpace();
    }
    
    /**
     * prints the visual representation of a monster in the game
     */
    public static void showMonster() {
        printBlankSpace();
        print("              ,   .-'\"'=;_  ,");
        print("              |\\.'-~`-.`-`;/|");
        print("              \\.` '.'~-.` './");
        print("              (\\`,__=-'__,'/)");
        print("           _.-'-.( d\\_/b ).-'-._");
        print("         /'.-'   ' .---. '   '-.`\\");
        print("       /'  .' (=    (_)    =) '.  `\\");
        print("      /'  .',  `-.__.-.__.-'  ,'.  `\\");
        print("     (     .'.   V       V  ; '.     )");
        print("     (    |::  `-,__.-.__,-'  ::|    )");
        print("     |   /|`:.               .:'|\\   |");
        print("     |  / | `:.              :' |`\\  |");
        print("     | |  (  :.             .:  )  | |");
        print("     | |   ( `:.            :' )   | |");
        print("     | |    \\ :.           .: /    | |");
        print("     | |     \\`:.         .:'/     | |");
        print("     ) (      `\\`:.     .:'/'      ) (");
        print("     (  `)_     ) `:._.:' (     _(`  )");
        print("     \\  ' _)  .'           `.  (_ `  /");
        print("      \\  '_) /   .'\"```\"'.   \\ (_`  /");
        print("       `'\"`  \\  (         )  /  `\"'`");
        print("   ___   jgs  `.`.       .'.'        ___");
        print(" .`   ``\"\"\"'''--`_)     (_'--'''\"\"\"``   `.");
        print("(_(_(___...--'\"'`         `'\"'--...___)_)_)");
        printBlankSpace();
    }
}